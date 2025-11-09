
// ----------------------------------------------------------------------------------------------------
//          Authors: 
//          Antony Oviedo Alfaro ID: 207640246
//          Esteban Francisco Sánchez Sánchez  ID: 402640418
// ----------------------------------------------------------------------------------------------------
package com.diezam04.expresso.core.transpiler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ParseTree;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.BinaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Call;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.CommentStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.ConstructorPattern;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.ExprStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.DataConstructor;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.DataConstructorCall;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.DataDeclaration;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.DataField;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.FunStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Lambda;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.LambdaParam;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Match;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.MatchCase;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.LetStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Num;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Oper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Operation;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Parameter;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.PrintStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Program;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Statement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Ternary;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Text;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.UnaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.ValueType;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.VarRef;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Real;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.WildcardPattern;

public final class AstBuilder extends ExprBaseVisitor<Object> {

    public Program build(ParseTree tree) {
        @SuppressWarnings("unchecked")
        Program program = (Program) visit(tree);
        return program;
    }

    @Override
    public Object visitProg(ExprParser.ProgContext ctx) {
        List<Statement> statements = new ArrayList<>();
        for (ExprParser.StatContext statCtx : ctx.stat()) {
            statements.add((Statement) visit(statCtx));
        }
        return new Program(statements);
    }

    @Override
    public Object visitFunStat(ExprParser.FunStatContext ctx) {
        List<Parameter> parameters = ctx.paramDeclList() == null
            ? List.of()
            : ctx.paramDeclList().paramDecl().stream()
                .map(AstBuilder::toParameter)
                .collect(Collectors.toList());
        String comment = ctx.comment() != null ? ctx.comment().getText() : null;
        return new FunStatement(
            ctx.ID().getText(),
            parameters,
            parseValueType(ctx.typeRef()),
            visitOperation(ctx.expr()),
            comment);
    }

    @Override
    public Object visitLetStat(ExprParser.LetStatContext ctx) {
        Operation value = visitOperation(ctx.expr());
        ValueType declaredType = ctx.typeRef() == null ? null : parseValueType(ctx.typeRef());
        String comment = ctx.comment() != null ? ctx.comment().getText() : null;
        String typeLiteral = ctx.typeRef() == null ? null : ctx.typeRef().getText();
        return new LetStatement(ctx.ID().getText(), declaredType, typeLiteral, value, comment);
    }

    @Override
    public Object visitPrintStat(ExprParser.PrintStatContext ctx) {
        Operation expr = visitOperation(ctx.expr());
        String comment = ctx.comment() != null ? ctx.comment().getText() : null;
        return new PrintStatement(expr, comment);
    }

    @Override
    public Object visitDataStat(ExprParser.DataStatContext ctx) {
        List<ConstructorSpec> specs = new ArrayList<>();
        ExprParser.DataBlockContext block = ctx.dataBlock();
        if (block != null && block.constructorList() != null) {
            for (ExprParser.DataConstructorContext ctorCtx : block.constructorList().dataConstructor()) {
                specs.add(toConstructorSpec(ctorCtx));
            }
        }
        List<DataConstructor> constructors = buildConstructors(ctx.ID().getText(), specs);
        String comment = ctx.comment() != null ? ctx.comment().getText() : null;
        return new DataDeclaration(ctx.ID().getText(), constructors, comment);
    }

    @Override
    public Object visitExprStat(ExprParser.ExprStatContext ctx) {
        Operation expr = visitOperation(ctx.expr());
        String comment = ctx.comment() != null ? ctx.comment().getText() : null;
        return new ExprStatement(expr, comment);
    }

    @Override
    public Object visitCommentStat(ExprParser.CommentStatContext ctx) {
        return new CommentStatement(ctx.comment().getText());
    }

    @Override
    public Object visitInt(ExprParser.IntContext ctx) {
        return new Num(Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public Object visitEulerFloat(ExprParser.EulerFloatContext ctx) {
        return new Real(Double.parseDouble(ctx.FLOAT_E().getText()));
    }

    @Override
    public Object visitFloat(ExprParser.FloatContext ctx) {
        return new Real(Double.parseDouble(ctx.FLOAT().getText()));
    }

    @Override
    public Object visitStringLit(ExprParser.StringLitContext ctx) {
        return new Text(decodeString(ctx.STRING().getText()));
    }

    @Override
    public Object visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Object visitAddSub(ExprParser.AddSubContext ctx) {
        return new BinaryOper(new Oper(ctx.op.getText()), visitOperation(ctx.expr(0)), visitOperation(ctx.expr(1)));
    }

    @Override
    public Object visitRelation(ExprParser.RelationContext ctx) {
        return new BinaryOper(new Oper(ctx.op.getText()), visitOperation(ctx.expr(0)), visitOperation(ctx.expr(1)));
    }

    @Override
    public Object visitEquality(ExprParser.EqualityContext ctx) {
        return new BinaryOper(new Oper(ctx.op.getText()), visitOperation(ctx.expr(0)), visitOperation(ctx.expr(1)));
    }

    @Override
    public Object visitPower(ExprParser.PowerContext ctx) {
        return new BinaryOper(new Oper("**"), visitOperation(ctx.expr(0)), visitOperation(ctx.expr(1)));
    }

    @Override
    public Object visitMulDiv(ExprParser.MulDivContext ctx) {
        return new BinaryOper(new Oper(ctx.op.getText()), visitOperation(ctx.expr(0)), visitOperation(ctx.expr(1)));
    }

    @Override
    public Object visitUnaryMinus(ExprParser.UnaryMinusContext ctx) {
        return new UnaryOper(new Oper("-"), visitOperation(ctx.expr()));
    }

    @Override
    public Object visitLambda(ExprParser.LambdaContext ctx) {
        List<LambdaParam> params = extractLambdaParams(ctx.params());
        Operation body = visitOperation(ctx.expr());
        return new Lambda(params, body);
    }

    @Override
    public Object visitCall(ExprParser.CallContext ctx) {
        Operation callee = visitOperation(ctx.expr());
        List<Operation> args = ctx.argumentList() == null
            ? List.of()
            : ctx.argumentList().expr().stream()
                .map(this::visitOperation)
                .collect(Collectors.toList());
        return new Call(callee, args);
    }

    @Override
    public Object visitIdRef(ExprParser.IdRefContext ctx) {
        return new VarRef(ctx.ID().getText());
    }

    @Override
    public Object visitTernary(ExprParser.TernaryContext ctx) {
        return new Ternary(visitOperation(ctx.expr(0)), visitOperation(ctx.expr(1)), visitOperation(ctx.expr(2)));
    }

    @Override
    public Object visitCtorCall(ExprParser.CtorCallContext ctx) {
        List<Operation> args = ctx.argumentList() == null
            ? List.of()
            : ctx.argumentList().expr().stream()
                .map(this::visitOperation)
                .collect(Collectors.toList());
        return new DataConstructorCall(ctx.ID().getText(), args);
    }

    @Override
    public Object visitMatchExpr(ExprParser.MatchExprContext ctx) {
        Operation target = visitOperation(ctx.expr());
        List<MatchCase> cases = ctx.matchCase().stream()
            .map(this::toMatchCase)
            .collect(Collectors.toList());
        return new Match(target, cases);
    }

    @Override
    public Object visitTypeCast(ExprParser.TypeCastContext ctx) {
        Operation value = visitOperation(ctx.expr());
        ValueType targetType = parseValueType(ctx.typeRef());
        return new com.diezam04.expresso.core.transpiler.src.ast.Ast.TypeCast(value, targetType);
    }

    private Operation visitOperation(ParseTree tree) {
        return (Operation) visit(tree);
    }

    private static List<LambdaParam> extractLambdaParams(ExprParser.ParamsContext ctx) {
        List<LambdaParam> params = new ArrayList<>();
        if (ctx != null) {
            for (ExprParser.LambdaParamContext paramCtx : ctx.lambdaParam()) {
                params.add(toLambdaParam(paramCtx));
            }
        }
        return params;
    }

    private static LambdaParam toLambdaParam(ExprParser.LambdaParamContext ctx) {
        ValueType type = ctx.typeRef() == null ? null : parseValueType(ctx.typeRef());
        return new LambdaParam(ctx.ID().getText(), type);
    }

    private static Parameter toParameter(ExprParser.ParamDeclContext ctx) {
        return new Parameter(ctx.ID().getText(), parseValueType(ctx.typeRef()));
    }

    private static DataConstructor toDataConstructor(ExprParser.DataConstructorContext ctx) {
        List<DataField> fields = new ArrayList<>();
        if (ctx.dataFieldList() != null) {
            for (ExprParser.DataFieldContext fieldCtx : ctx.dataFieldList().dataField()) {
                fields.add(toDataField(fieldCtx));
            }
        }
        return new DataConstructor(ctx.ID().getText(), fields);
    }

    private static DataField toDataField(ExprParser.DataFieldContext ctx) {
        String typeLiteral = ctx.typeRef() == null ? null : ctx.typeRef().getText();
        return new DataField(ctx.ID().getText(), typeLiteral);
    }

    private MatchCase toMatchCase(ExprParser.MatchCaseContext ctx) {
        if (ctx.pattern() == null) {
            return new MatchCase(new WildcardPattern(), visitOperation(ctx.expr()));
        }
        return new MatchCase(toPattern(ctx.pattern()), visitOperation(ctx.expr()));
    }

    private static com.diezam04.expresso.core.transpiler.src.ast.Ast.Pattern toPattern(ExprParser.PatternContext ctx) {
        if (ctx instanceof ExprParser.WildcardPatternContext) {
            return new WildcardPattern();
        }
        ExprParser.ConstructorPatternContext ctor = (ExprParser.ConstructorPatternContext) ctx;
        List<String> bindings = new ArrayList<>();
        if (ctor.patternParamList() != null) {
            for (ExprParser.PatternParamContext param : ctor.patternParamList().patternParam()) {
                bindings.add(param.getText());
            }
        }
        return new ConstructorPattern(ctor.ID().getText(), bindings);
    }

    private static List<DataConstructor> buildConstructors(String typeName, List<ConstructorSpec> specs) {
        if (specs.isEmpty()) {
            return List.of();
        }
        boolean allFields = specs.stream().allMatch(ConstructorSpec::isRecordField);
        if (allFields) {
            List<DataField> fields = specs.stream()
                .map(spec -> new DataField(spec.name(), spec.fieldType()))
                .collect(Collectors.toList());
            return List.of(new DataConstructor(typeName, fields));
        }
        boolean hasFieldEntry = specs.stream().anyMatch(ConstructorSpec::isRecordField);
        if (hasFieldEntry) {
            throw new IllegalArgumentException("Cannot mix record-style fields with constructor variants in data type '" + typeName + "'");
        }
        return specs.stream()
            .map(spec -> new DataConstructor(spec.name(), spec.parameters()))
            .collect(Collectors.toList());
    }

    private static ConstructorSpec toConstructorSpec(ExprParser.DataConstructorContext ctx) {
        String name = ctx.ID().getText();
        boolean hasParentheses = ctx.getChildCount() >= 3
            && "(".equals(ctx.getChild(1).getText());
        if (ctx.dataFieldList() != null || hasParentheses) {
            List<DataField> params = ctx.dataFieldList() == null
                ? List.of()
                : ctx.dataFieldList().dataField().stream()
                    .map(AstBuilder::toDataField)
                    .collect(Collectors.toList());
            return ConstructorSpec.variant(name, params);
        }
        String typeLiteral = ctx.typeRef() == null ? null : ctx.typeRef().getText();
        return ConstructorSpec.recordField(name, typeLiteral);
    }

    private static final class ConstructorSpec {
        private final String name;
        private final List<DataField> parameters;
        private final String fieldType;

        private ConstructorSpec(String name, List<DataField> parameters, String fieldType) {
            this.name = name;
            this.parameters = parameters;
            this.fieldType = fieldType;
        }

        static ConstructorSpec variant(String name, List<DataField> params) {
            return new ConstructorSpec(name, List.copyOf(params), null);
        }

        static ConstructorSpec recordField(String name, String fieldType) {
            return new ConstructorSpec(name, null, fieldType);
        }

        boolean isRecordField() {
            return parameters == null;
        }

        String name() {
            return name;
        }

        List<DataField> parameters() {
            return parameters == null ? List.of() : parameters;
        }

        String fieldType() {
            return fieldType;
        }
    }

    private static ValueType parseValueType(ExprParser.TypeRefContext ctx) {
        return ValueType.fromLiteral(ctx.getText());
    }

    private static String decodeString(String literal) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < literal.length() - 1; i++) {
            char ch = literal.charAt(i);
            if (ch == '\\') {
                if (i + 1 >= literal.length() - 1) {
                    break;
                }
                char next = literal.charAt(++i);
                switch (next) {
                    case 'n' -> sb.append('\n');
                    case 't' -> sb.append('\t');
                    case 'r' -> sb.append('\r');
                    case 'b' -> sb.append('\b');
                    case '"' -> sb.append('"');
                    case '\\' -> sb.append('\\');
                    default -> sb.append(next);
                }
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
