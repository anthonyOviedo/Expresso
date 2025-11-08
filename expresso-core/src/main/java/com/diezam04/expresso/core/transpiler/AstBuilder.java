
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
import org.antlr.v4.runtime.tree.TerminalNode;

import com.diezam04.expresso.core.transpiler.src.ast.Ast.BinaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Call;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.CommentStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.ExprStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.FunStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Lambda;
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
            parseType(ctx.type()),
            visitOperation(ctx.expr()),
            comment);
    }

    @Override
    public Object visitLetStat(ExprParser.LetStatContext ctx) {
        Operation value = visitOperation(ctx.expr());
        String comment = ctx.comment() != null ? ctx.comment().getText() : null;
        return new LetStatement(ctx.ID().getText(), value, comment);
    }

    @Override
    public Object visitPrintStat(ExprParser.PrintStatContext ctx) {
        Operation expr = visitOperation(ctx.expr());
        String comment = ctx.comment() != null ? ctx.comment().getText() : null;
        return new PrintStatement(expr, comment);
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
        List<String> params = extractParams(ctx.params());
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

    private Operation visitOperation(ParseTree tree) {
        return (Operation) visit(tree);
    }

    private static List<String> extractParams(ExprParser.ParamsContext ctx) {
        List<String> params = new ArrayList<>();
        if (ctx != null) {
            for (TerminalNode id : ctx.ID()) {
                params.add(id.getText());
            }
        }
        return params;
    }

    private static Parameter toParameter(ExprParser.ParamDeclContext ctx) {
        return new Parameter(ctx.ID().getText(), parseType(ctx.type()));
    }

    private static ValueType parseType(ExprParser.TypeContext ctx) {
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
