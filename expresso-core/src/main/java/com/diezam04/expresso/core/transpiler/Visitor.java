// ----------------------------------------------------------------------------------------------------
//          Authors: 
//          Antony Oviedo Alfaro ID: 207640246
//          Esteban Francisco Sánchez Sánchez  ID: 402640418
// ----------------------------------------------------------------------------------------------------

package com.diezam04.expresso.core.transpiler;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.diezam04.expresso.core.transpiler.src.ast.Ast.BinaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Call;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.CommentStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.ConstructorPattern;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.DataConstructor;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.DataConstructorCall;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.DataDeclaration;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.DataField;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.ExprStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.FunStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Lambda;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.LambdaParam;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.LetStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Match;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.MatchCase;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Num;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Operation;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Parameter;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.PrintStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Program;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Statement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Ternary;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Text;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.TypeCast;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.UnaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.ValueType;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.VarRef;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Real;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.WildcardPattern;

public final class Visitor {

    private Visitor() {
        throw new AssertionError("Utility class");
    }

    public static String generate(Program program, String className) {
        Program optimizedProgram = AstOptimizer.optimize(program);
        GenerationContext context = new GenerationContext();
        String normalizedClassName = capitalize(className);

        List<String> statements = new ArrayList<>();
        for (Statement statement : optimizedProgram.statements()) {
            statements.addAll(genStatement(statement, context));
        }

        StringBuilder sb = new StringBuilder();
        for (String importLine : context.imports()) {
            sb.append("import ").append(importLine).append(";\n");
        }
        if (!context.imports().isEmpty()) {
            sb.append('\n');
        }
        sb.append("public class ").append(normalizedClassName).append(" {\n\n");
        sb.append("    public static void print(Object arg) { System.out.println(arg); }\n\n");
        renderDataTypes(sb, context);
        sb.append("    public static void main(String... args) {\n");
        for (String stmt : statements) {
            sb.append("        ").append(stmt).append('\n');
        }
        sb.append("    }\n");
        sb.append("}\n");
        return sb.toString();
    }

    private static String capitalize(String name) {
        if (name == null || name.isBlank()) {
            return "Main";
        }
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    private static List<String> genStatement(Statement stmt, GenerationContext context) {
        if (stmt instanceof LetStatement letStatement) {
            ResolvedType resolvedType = resolveType(letStatement.value(), context);
            if (letStatement.declaredType() != null) {
                resolvedType = ResolvedType.scalar(letStatement.declaredType().javaName(), letStatement.declaredType());
            }
            String sanitizedName = context.reserveLetName(letStatement.name());
            context.registerLet(letStatement.name(), sanitizedName, resolvedType);
            String base = resolvedType.declaration() + " " + sanitizedName + " = "
                + genExpr(letStatement.value(), context) + ";";
            return List.of(appendInlineComment(base, letStatement.comment()));
        }
        if (stmt instanceof FunStatement funStatement) {
            return genFunStatement(funStatement, context);
        }
        if (stmt instanceof PrintStatement printStatement) {
            String expr = genExpr(printStatement.value(), context);
            String base = "print(" + expr + ");";
            return List.of(appendInlineComment(base, printStatement.comment()));
        }
        if (stmt instanceof DataDeclaration dataDeclaration) {
            context.registerData(dataDeclaration);
            return List.of();
        }
        if (stmt instanceof ExprStatement exprStatement) {
            String base = genExpr(exprStatement.value(), context) + ";";
            return List.of(appendInlineComment(base, exprStatement.comment()));
        }
        if (stmt instanceof CommentStatement commentStatement) {
            return renderStandaloneComment(commentStatement.text());
        }
        return List.of();
    }

    private static List<String> genFunStatement(FunStatement funStatement, GenerationContext context) {
        ResolvedType resolvedType = buildFunctionalType(
            funStatement.parameters().stream().map(Parameter::type).collect(Collectors.toList()),
            funStatement.returnType(),
            context);
        String sanitizedName = context.reserveLetName(funStatement.name());
        context.registerLet(funStatement.name(), sanitizedName, resolvedType);
        String value = renderRecursiveFunctionValue(funStatement, context, resolvedType);
        String base = resolvedType.declaration() + " " + sanitizedName + " = " + value + ";";
        return List.of(appendInlineComment(base, funStatement.comment()));
    }

    private static ResolvedType buildFunctionalType(List<ValueType> parameterTypes, ValueType returnType, GenerationContext context) {
        List<ValueType> normalizedParams = new ArrayList<>(parameterTypes.size());
        for (ValueType param : parameterTypes) {
            normalizedParams.add(defaultType(param));
        }
        ValueType normalizedReturn = defaultType(returnType);
        FunctionalInterfaceDescriptor descriptor = selectDescriptor(normalizedParams, normalizedReturn);
        context.requireImport(descriptor.qualifiedName());
        String declaration = descriptor.renderDeclaration(normalizedParams, normalizedReturn);
        return new ResolvedType(declaration, descriptor, normalizedParams, normalizedReturn);
    }

    private static String renderRecursiveFunctionValue(FunStatement funStatement, GenerationContext context, ResolvedType resolvedType) {
        List<String> originalParams = funStatement.parameters().stream()
            .map(Parameter::name)
            .collect(Collectors.toList());

        context.pushScope();
        List<String> sanitizedParams = context.registerLambdaParams(originalParams);
        context.pushAlias(funStatement.name(), "this");
        String body = genExpr(funStatement.body(), context);
        context.popAlias(funStatement.name());
        context.popScope();

        List<String> signatureParts = new ArrayList<>();
        FunctionalInterfaceDescriptor descriptor = resolvedType.descriptor();
        List<ValueType> parameterTypes = resolvedType.parameterTypes();
        List<String> methodParamTypes = descriptor.methodParameterTypes(parameterTypes);
        for (int i = 0; i < sanitizedParams.size(); i++) {
            signatureParts.add(methodParamTypes.get(i) + " " + sanitizedParams.get(i));
        }

        StringBuilder builder = new StringBuilder();
        builder.append("new ").append(resolvedType.declaration()).append("() {\n");
        builder.append("            @Override public ")
            .append(descriptor.methodReturnType(resolvedType.returnType()))
            .append(" ")
            .append(descriptor.invocationMethod())
            .append("(")
            .append(String.join(", ", signatureParts))
            .append(") {\n");
        builder.append("                return ").append(body).append(";\n");
        builder.append("            }\n");
        builder.append("        }");
        return builder.toString();
    }

    private static String genExpr(Operation expr, GenerationContext context) {
        if (expr instanceof Num num) {
            return String.valueOf(num.value());
        }
        if (expr instanceof Real real) {
            return formatDouble(real.value());
        }
        if (expr instanceof Text text) {
            return "\"" + escapeJavaString(text.value()) + "\"";
        }
        if (expr instanceof VarRef var) {
            if (!context.hasBinding(var.name())) {
                GenerationContext.DataConstructorInfo ctorInfo = context.lookupConstructor(var.name());
                if (ctorInfo != null && ctorInfo.fields().isEmpty()) {
                    return "new " + ctorInfo.javaName() + "()";
                }
            }
            return context.resolveName(var.name());
        }
        if (expr instanceof UnaryOper unary) {
            String operand = genExpr(unary.operand(), context);
            return unary.operator().symbol() + "(" + operand + ")";
        }
        if (expr instanceof BinaryOper binary) {
            String left = genExpr(binary.left(), context);
            String right = genExpr(binary.right(), context);
            return switch (binary.operator().symbol()) {
                case "**" -> "(int) Math.pow(" + left + ", " + right + ")";
                case "=" -> {
                    if (binary.left() instanceof VarRef variable) {
                        String target = context.resolveName(variable.name());
                        String assignment = target + " = " + genExpr(binary.right(), context);
                        yield assignment;
                    }
                    yield "(" + left + " = " + right + ")";
                }
                default -> "(" + left + " " + binary.operator().symbol() + " " + right + ")";
            };
        }
        if (expr instanceof Ternary ternary) {
            String condition = renderCondition(ternary.cond(), context);
            return "(" + condition + " ? "
                + genExpr(ternary.thenExpr(), context) + " : "
                + genExpr(ternary.elseExpr(), context) + ")";
        }
        if (expr instanceof Lambda lambda) {
            context.pushScope();
            List<String> paramNames = lambdaParamNames(lambda.params());
            List<String> params = context.registerLambdaParams(paramNames);
            String renderedParams = params.isEmpty()
                ? "()"
                : "(" + String.join(", ", params) + ")";
            String body = genExpr(lambda.body(), context);
            context.popScope();
            return renderedParams + " -> " + body;
        }
        if (expr instanceof Call call) {
            String callee = genExpr(call.callee(), context);
            String method = call.arguments().isEmpty() ? "get" : "apply";
            if (call.callee() instanceof VarRef var) {
                ResolvedType info = context.lookupType(var.name());
                if (info != null && info.descriptor() != null) {
                    method = info.descriptor().invocationMethod();
                }
            }
            if (call.arguments().isEmpty()) {
                return callee + "." + method + "()";
            }
            String args = call.arguments().stream()
                .map(arg -> genExpr(arg, context))
                .collect(Collectors.joining(", "));
            return callee + "." + method + "(" + args + ")";
        }
        if (expr instanceof DataConstructorCall ctorCall) {
            return renderConstructorCall(ctorCall, context);
        }
        if (expr instanceof Match match) {
            return renderMatch(match, context);
        }
        if (expr instanceof com.diezam04.expresso.core.transpiler.src.ast.Ast.TypeCast cast) {
            return renderCast(cast, context);
        }
        return "";
    }

    private static String renderCondition(Operation cond, GenerationContext context) {
        String rendered = genExpr(cond, context);

        if (cond instanceof BinaryOper binary) {
            String op = binary.operator().symbol();
            if ("==".equals(op) || "!=".equals(op) || ">".equals(op) || "<".equals(op)
                    || ">=".equals(op) || "<=".equals(op)) {
                return rendered;
            }
        }

        if ("true".equals(rendered) || "false".equals(rendered)) {
            return rendered;
        }

        return "(" + rendered + " != 0)";
    }

    private static String renderConstructorCall(DataConstructorCall call, GenerationContext context) {
        GenerationContext.DataConstructorInfo info = context.lookupConstructor(call.name());
        if (info == null) {
            GenerationContext.DataTypeInfo dataType = context.lookupDataType(call.name());
            if (dataType != null) {
                if (call.arguments().isEmpty()) {
                    throw new IllegalArgumentException("Constructor for data type '" + call.name() + "' requires a variant name");
                }
                Operation variantExpr = call.arguments().get(0);
                if (variantExpr instanceof VarRef variantRef) {
                    GenerationContext.DataConstructorInfo variantInfo = context.lookupConstructor(variantRef.name());
                    if (variantInfo != null && variantInfo.ownerJavaType().equals(dataType.javaName())) {
                        List<Operation> remaining = new ArrayList<>(call.arguments().subList(1, call.arguments().size()));
                        return renderConstructorCall(new DataConstructorCall(variantInfo.originalName(), remaining), context);
                    }
                }
                throw new IllegalArgumentException("Unknown variant for data type '" + call.name() + "'");
            }
            throw new IllegalArgumentException("Unknown constructor: " + call.name());
        }
        String javaCtor = info.javaName();
        if (info.fields().isEmpty() && "Nil".equalsIgnoreCase(info.originalName())) {
            return "null";
        }
        String args = call.arguments().stream()
            .map(arg -> genExpr(arg, context))
            .collect(Collectors.joining(", "));
        return "new " + javaCtor + "(" + args + ")";
    }

    private static String renderCast(TypeCast cast, GenerationContext context) {
        ValueType targetType = cast.targetType();
        String value = genExpr(cast.value(), context);
        ValueType sourceType = guessValueType(cast.value(), context);
        return switch (targetType) {
            case STRING -> "String.valueOf(" + value + ")";
            case INT, FLOAT -> renderNumericCast(targetType, value, cast.value(), sourceType);
            case BOOLEAN -> {
                if (sourceType == ValueType.STRING) {
                    Double evaluated = evaluateLiteralExpression(cast.value());
                    if (evaluated != null) {
                        yield evaluated != 0.0 ? "true" : "false";
                    }
                    yield "Boolean.parseBoolean(String.valueOf(" + value + "))";
                }
                yield "((" + targetType.javaName() + ") (" + value + "))";
            }
            case ANY -> "((" + targetType.javaName() + ") (" + value + "))";
        };
    }

    private static String renderNumericCast(ValueType targetType, String valueExpr, Operation operand, ValueType sourceType) {
        if (sourceType == ValueType.STRING) {
            Double evaluated = evaluateLiteralExpression(operand);
            if (evaluated != null) {
                return "((" + targetType.javaName() + ") (" + formatLiteral(evaluated) + "))";
            }
            String parsed = "Double.parseDouble(String.valueOf(" + valueExpr + "))";
            return targetType == ValueType.INT
                ? "((" + targetType.javaName() + ") (" + parsed + "))"
                : parsed;
        }
        return "((" + targetType.javaName() + ") (" + valueExpr + "))";
    }

    private static String renderMatch(Match match, GenerationContext context) {
        String javaType = inferMatchJavaType(match, context);
        String targetExpr = genExpr(match.target(), context);
        StringBuilder builder = new StringBuilder();
        builder.append("((").append(javaType).append(") (switch (").append(targetExpr).append(") {\n");
        boolean hasWildcard = false;
        for (MatchCase matchCase : match.cases()) {
            if (matchCase.pattern() instanceof WildcardPattern) {
                hasWildcard = true;
            }
            builder.append(renderMatchCase(matchCase, context));
        }
        if (!hasWildcard) {
            builder.append("                default -> throw new IllegalStateException(\"Non-exhaustive match\");\n");
        }
        builder.append("            }))");
        return builder.toString();
    }

    private static String inferMatchJavaType(Match match, GenerationContext context) {
        String javaType = null;
        for (MatchCase matchCase : match.cases()) {
            String candidate = inferOperationJavaType(matchCase.body(), context);
            if (javaType == null) {
                javaType = candidate;
            } else if (!javaType.equals(candidate)) {
                return "Object";
            }
        }
        return javaType == null ? "Object" : javaType;
    }

    private static String inferOperationJavaType(Operation op, GenerationContext context) {
        if (op instanceof Num) {
            return "int";
        }
        if (op instanceof Real) {
            return "double";
        }
        if (op instanceof Text) {
            return "String";
        }
        if (op instanceof DataConstructorCall ctorCall) {
            GenerationContext.DataConstructorInfo info = context.lookupConstructor(ctorCall.name());
            if (info != null) {
                return info.ownerJavaType();
            }
            GenerationContext.DataTypeInfo dataType = context.lookupDataType(ctorCall.name());
            if (dataType != null) {
                return dataType.javaName();
            }
            return capitalize(ctorCall.name());
        }
        if (op instanceof VarRef var) {
            ResolvedType resolved = context.lookupType(var.name());
            if (resolved != null) {
                String declaration = resolved.declaration();
                if (!"var".equals(declaration)) {
                    return declaration;
                }
            }
        }
        ValueType inferred = inferOperationType(op, Collections.emptyMap(), context);
        if (inferred != null) {
            return inferred.javaName();
        }
        return "Object";
    }

    private static String renderMatchCase(MatchCase matchCase, GenerationContext context) {
        StringBuilder builder = new StringBuilder();
        if (matchCase.pattern() instanceof ConstructorPattern pattern) {
            GenerationContext.DataConstructorInfo ctorInfo = context.lookupConstructor(pattern.constructorName());
            String javaCtor = ctorInfo == null ? capitalize(pattern.constructorName()) : ctorInfo.javaName();
            String alias = context.reserveLetName(pattern.constructorName() + "Case");
            builder.append("                case ").append(javaCtor).append(" ").append(alias).append(" -> {\n");
            context.pushScope();
            List<String> sanitized = context.registerPatternBindings(pattern.bindings());
            List<GenerationContext.DataFieldInfo> fields = ctorInfo == null ? List.of() : ctorInfo.fields();
            for (int i = 0; i < sanitized.size(); i++) {
                String binding = pattern.bindings().get(i);
                String localName = sanitized.get(i);
                if (localName == null || "_".equals(binding)) {
                    continue;
                }
                String fieldAccess;
                if (i < fields.size()) {
                    fieldAccess = alias + "." + fields.get(i).name() + "()";
                } else {
                    fieldAccess = alias + ".component" + (i + 1) + "()";
                }
                builder.append("                    var ").append(localName).append(" = ").append(fieldAccess).append(";\n");
            }
            String body = genExpr(matchCase.body(), context);
            context.popScope();
            builder.append("                    yield ").append(body).append(";\n");
            builder.append("                }\n");
        } else if (matchCase.pattern() instanceof WildcardPattern) {
            String body = genExpr(matchCase.body(), context);
            builder.append("                default -> {\n");
            builder.append("                    yield ").append(body).append(";\n");
            builder.append("                }\n");
        }
        return builder.toString();
    }

    private static void renderDataTypes(StringBuilder sb, GenerationContext context) {
        List<GenerationContext.DataTypeInfo> dataTypes = context.dataTypes();
        if (dataTypes.isEmpty()) {
            return;
        }
        for (GenerationContext.DataTypeInfo dataType : dataTypes) {
            if (dataType.isRecordType()) {
                GenerationContext.DataConstructorInfo ctor = dataType.constructors().get(0);
                sb.append("    public record ").append(dataType.javaName()).append("(")
                    .append(renderRecordComponents(ctor.fields()))
                    .append(") {}\n\n");
                continue;
            }
            String permits = dataType.constructors().stream()
                .map(GenerationContext.DataConstructorInfo::javaName)
                .collect(Collectors.joining(", "));
            sb.append("    public sealed interface ").append(dataType.javaName())
                .append(" permits ").append(permits).append(" {}\n\n");
            for (GenerationContext.DataConstructorInfo constructor : dataType.constructors()) {
                sb.append("    public static record ").append(constructor.javaName()).append("(")
                    .append(renderRecordComponents(constructor.fields()))
                    .append(") implements ").append(dataType.javaName()).append(" {}\n\n");
            }
        }
    }

    private static String renderRecordComponents(List<GenerationContext.DataFieldInfo> fields) {
        return fields.stream()
            .map(field -> field.javaType() + " " + field.name())
            .collect(Collectors.joining(", "));
    }

    private static ResolvedType resolveType(Operation rhs, GenerationContext context) {
        if (rhs instanceof Num) {
            return ResolvedType.scalar("int", ValueType.INT);
        }
        if (rhs instanceof Real) {
            return ResolvedType.scalar("double", ValueType.FLOAT);
        }
        if (rhs instanceof Text) {
            return ResolvedType.scalar("String", ValueType.STRING);
        }
        if (rhs instanceof Lambda lambda) {
            return resolveLambdaType(lambda, context);
        }
        if (rhs instanceof TypeCast cast) {
            return ResolvedType.scalar(cast.targetType().javaName(), cast.targetType());
        }
        return ResolvedType.scalar("var", null);
    }

    private static ResolvedType resolveLambdaType(Lambda lambda, GenerationContext context) {
        List<ValueType> paramTypes = new ArrayList<>(lambda.params().size());
        Map<String, ValueType> localTypes = new HashMap<>();
        for (LambdaParam param : lambda.params()) {
            ValueType type = defaultType(param.type());
            paramTypes.add(type);
            localTypes.put(param.name(), type);
        }
        ValueType returnType = inferOperationType(lambda.body(), localTypes, context);
        if (returnType == null) {
            returnType = ValueType.INT;
        }
        return buildFunctionalType(paramTypes, returnType, context);
    }

    private static ValueType inferOperationType(Operation op, Map<String, ValueType> localTypes, GenerationContext context) {
        if (op instanceof Num) {
            return ValueType.INT;
        }
        if (op instanceof Real) {
            return ValueType.FLOAT;
        }
        if (op instanceof Text) {
            return ValueType.STRING;
        }
        if (op instanceof TypeCast cast) {
            return cast.targetType();
        }
        if (op instanceof VarRef var) {
            ValueType local = localTypes.get(var.name());
            if (local != null) {
                return local;
            }
            ResolvedType resolved = context.lookupType(var.name());
            if (resolved != null) {
                return resolved.returnType();
            }
            return null;
        }
        if (op instanceof UnaryOper unary) {
            return inferOperationType(unary.operand(), localTypes, context);
        }
        if (op instanceof BinaryOper binary) {
            String symbol = binary.operator().symbol();
            ValueType left = inferOperationType(binary.left(), localTypes, context);
            ValueType right = inferOperationType(binary.right(), localTypes, context);
            if ("+".equals(symbol) && (left == ValueType.STRING || right == ValueType.STRING)) {
                return ValueType.STRING;
            }
            if (isComparisonOperator(symbol)) {
                return ValueType.BOOLEAN;
            }
            if ("=".equals(symbol)) {
                return left != null ? left : right;
            }
            ValueType promoted = ValueType.promoteNumeric(left, right);
            if (promoted != null) {
                return promoted;
            }
            return ValueType.FLOAT;
        }
        if (op instanceof Ternary ternary) {
            ValueType thenType = inferOperationType(ternary.thenExpr(), localTypes, context);
            ValueType elseType = inferOperationType(ternary.elseExpr(), localTypes, context);
            return mostSpecific(thenType, elseType);
        }
        if (op instanceof Call call) {
            if (call.callee() instanceof VarRef var) {
                ResolvedType resolved = context.lookupType(var.name());
                if (resolved != null) {
                    return resolved.returnType();
                }
            }
            return null;
        }
        if (op instanceof Lambda nested) {
            ResolvedType nestedResolved = resolveLambdaType(nested, context);
            return nestedResolved.returnType();
        }
        return null;
    }

    private static Double evaluateLiteralExpression(Operation operand) {
        if (operand instanceof Text text) {
            return evaluateLiteralExpression(text.value());
        }
        return null;
    }

    private static Double evaluateLiteralExpression(String expression) {
        try {
            return new LiteralExpressionParser(expression).parse();
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    private static String formatLiteral(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return "0.0";
        }
        String literal = Double.toString(value);
        if (!literal.contains(".")) {
            return literal + ".0";
        }
        return literal;
    }

    private static final class LiteralExpressionParser {
        private final String source;
        private int index;

        LiteralExpressionParser(String source) {
            this.source = source;
        }

        double parse() {
            double value = parseExpression();
            skipWhitespace();
            if (index != source.length()) {
                throw new IllegalArgumentException("Unexpected token at position " + index);
            }
            return value;
        }

        private double parseExpression() {
            double value = parseTerm();
            while (true) {
                skipWhitespace();
                if (match('+')) {
                    value += parseTerm();
                } else if (match('-')) {
                    value -= parseTerm();
                } else {
                    break;
                }
            }
            return value;
        }

        private double parseTerm() {
            double value = parseFactor();
            while (true) {
                skipWhitespace();
                if (match('*')) {
                    value *= parseFactor();
                } else if (match('/')) {
                    value /= parseFactor();
                } else {
                    break;
                }
            }
            return value;
        }

        private double parseFactor() {
            skipWhitespace();
            if (match('+')) {
                return parseFactor();
            }
            if (match('-')) {
                return -parseFactor();
            }
            if (match('(')) {
                double value = parseExpression();
                if (!match(')')) {
                    throw new IllegalArgumentException("Missing closing parenthesis at position " + index);
                }
                return value;
            }
            return parseNumber();
        }

        private double parseNumber() {
            skipWhitespace();
            int start = index;
            boolean hasDot = false;
            while (index < source.length()) {
                char ch = source.charAt(index);
                if (Character.isDigit(ch)) {
                    index++;
                } else if (ch == '.') {
                    if (hasDot) {
                        throw new IllegalArgumentException("Unexpected '.' at position " + index);
                    }
                    hasDot = true;
                    index++;
                } else {
                    break;
                }
            }
            if (start == index) {
                throw new IllegalArgumentException("Expected number at position " + index);
            }
            return Double.parseDouble(source.substring(start, index));
        }

        private boolean match(char expected) {
            if (index < source.length() && source.charAt(index) == expected) {
                index++;
                return true;
            }
            return false;
        }

        private void skipWhitespace() {
            while (index < source.length() && Character.isWhitespace(source.charAt(index))) {
                index++;
            }
        }
    }

    private static ValueType guessValueType(Operation op, GenerationContext context) {
        if (op instanceof Num) {
            return ValueType.INT;
        }
        if (op instanceof Real) {
            return ValueType.FLOAT;
        }
        if (op instanceof Text) {
            return ValueType.STRING;
        }
        if (op instanceof TypeCast cast) {
            return cast.targetType();
        }
        if (op instanceof VarRef var) {
            ResolvedType resolved = context.lookupType(var.name());
            if (resolved != null) {
                return resolved.returnType();
            }
        }
        return null;
    }

    private static final class ResolvedType {
        private final String declaration;
        private final FunctionalInterfaceDescriptor descriptor;
        private final List<ValueType> parameterTypes;
        private final ValueType returnType;

        private ResolvedType(String declaration, FunctionalInterfaceDescriptor descriptor,
                List<ValueType> parameterTypes, ValueType returnType) {
            this.declaration = declaration;
            this.descriptor = descriptor;
            this.parameterTypes = List.copyOf(parameterTypes);
            this.returnType = returnType;
        }

        static ResolvedType scalar(String declaration, ValueType type) {
            return new ResolvedType(declaration, null, List.of(), type);
        }

        boolean isFunctional() {
            return descriptor != null;
        }

        String declaration() {
            return declaration;
        }

        FunctionalInterfaceDescriptor descriptor() {
            return descriptor;
        }

        List<ValueType> parameterTypes() {
            return parameterTypes;
        }

        ValueType returnType() {
            return returnType;
        }
    }

    private record FunctionalInterfaceDescriptor(
            String simpleName,
            String qualifiedName,
            String invocationMethod,
            boolean usesGenerics,
            boolean primitiveDouble) {

        String renderDeclaration(List<ValueType> params, ValueType returnType) {
            if (!usesGenerics) {
                return simpleName;
            }
            List<String> generics = new ArrayList<>();
            for (ValueType param : params) {
                generics.add(param.boxedJavaName());
            }
            generics.add(returnType.boxedJavaName());
            return simpleName + "<" + String.join(", ", generics) + ">";
        }

        List<String> methodParameterTypes(List<ValueType> params) {
            List<String> result = new ArrayList<>(params.size());
            if (primitiveDouble) {
                for (int i = 0; i < params.size(); i++) {
                    result.add("double");
                }
            } else {
                for (ValueType param : params) {
                    result.add(param.boxedJavaName());
                }
            }
            return result;
        }

        String methodReturnType(ValueType returnType) {
            return primitiveDouble ? "double" : returnType.boxedJavaName();
        }
    }

    private static final FunctionalInterfaceDescriptor FUNCTION =
        new FunctionalInterfaceDescriptor("Function", "java.util.function.Function", "apply", true, false);
    private static final FunctionalInterfaceDescriptor BIFUNCTION =
        new FunctionalInterfaceDescriptor("BiFunction", "java.util.function.BiFunction", "apply", true, false);
    private static final FunctionalInterfaceDescriptor SUPPLIER =
        new FunctionalInterfaceDescriptor("Supplier", "java.util.function.Supplier", "get", true, false);
    private static final FunctionalInterfaceDescriptor DOUBLE_UNARY =
        new FunctionalInterfaceDescriptor("DoubleUnaryOperator", "java.util.function.DoubleUnaryOperator", "applyAsDouble", false, true);
    private static final FunctionalInterfaceDescriptor DOUBLE_BINARY =
        new FunctionalInterfaceDescriptor("DoubleBinaryOperator", "java.util.function.DoubleBinaryOperator", "applyAsDouble", false, true);

    private static FunctionalInterfaceDescriptor selectDescriptor(List<ValueType> params, ValueType returnType) {
        int arity = params.size();
        if (arity == 0) {
            return SUPPLIER;
        }
        boolean allFloat = params.stream().allMatch(t -> t == ValueType.FLOAT);
        if (allFloat && returnType == ValueType.FLOAT) {
            if (arity == 1) {
                return DOUBLE_UNARY;
            }
            if (arity == 2) {
                return DOUBLE_BINARY;
            }
        }
        return switch (arity) {
            case 1 -> FUNCTION;
            case 2 -> BIFUNCTION;
            default -> throw new UnsupportedOperationException("Functional arity " + arity + " not supported");
        };
    }

    private static ValueType defaultType(ValueType explicitType) {
        return explicitType != null ? explicitType : ValueType.INT;
    }

    private static List<String> lambdaParamNames(List<LambdaParam> params) {
        List<String> names = new ArrayList<>(params.size());
        for (LambdaParam param : params) {
            names.add(param.name());
        }
        return names;
    }

    private static ValueType mostSpecific(ValueType first, ValueType second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        if (first == second) {
            return first;
        }
        if (first == ValueType.BOOLEAN || second == ValueType.BOOLEAN) {
            if (first == ValueType.BOOLEAN && second == ValueType.BOOLEAN) {
                return ValueType.BOOLEAN;
            }
            return ValueType.BOOLEAN;
        }
        if (first == ValueType.STRING || second == ValueType.STRING) {
            return ValueType.STRING;
        }
        if (first == ValueType.FLOAT || second == ValueType.FLOAT) {
            return ValueType.FLOAT;
        }
        if (first == ValueType.INT || second == ValueType.INT) {
            return ValueType.INT;
        }
        return first;
    }

    private static boolean isComparisonOperator(String symbol) {
        return "==".equals(symbol) || "!=".equals(symbol)
            || ">=".equals(symbol) || "<=".equals(symbol)
            || ">".equals(symbol) || "<".equals(symbol);
    }

    private static final class GenerationContext {
        private final Set<String> imports = new LinkedHashSet<>();
        private final Deque<Scope> scopes = new ArrayDeque<>();
        private final Set<String> reservedNames = new HashSet<>();
        private final Map<String, Deque<String>> aliasOverrides = new HashMap<>();
        private final Map<String, DataTypeInfo> dataTypes = new LinkedHashMap<>();
        private final Map<String, DataConstructorInfo> constructors = new HashMap<>();
        private final Map<String, String> typeNameMappings = new HashMap<>();

        GenerationContext() {
            scopes.push(new Scope());
        }

        List<String> imports() {
            return new ArrayList<>(imports);
        }

        void requireImport(String fqcn) {
            imports.add(fqcn);
        }

        String reserveLetName(String original) {
            String sanitized = makeUnique(original);
            reservedNames.add(sanitized);
            return sanitized;
        }

        void registerLet(String original, String sanitized, ResolvedType typeInfo) {
            Scope scope = scopes.peek();
            scope.put(original, sanitized, typeInfo);
        }

        void registerData(DataDeclaration declaration) {
            String javaType = capitalize(declaration.name());
            typeNameMappings.put(declaration.name(), javaType);
            boolean recordStyle = declaration.constructors().size() == 1
                && !declaration.constructors().get(0).fields().isEmpty()
                && declaration.constructors().get(0).name().equals(declaration.name());
            List<DataConstructorInfo> constructorInfos = new ArrayList<>();
            for (DataConstructor constructor : declaration.constructors()) {
                List<DataFieldInfo> fields = new ArrayList<>();
                for (DataField field : constructor.fields()) {
                    fields.add(new DataFieldInfo(field.name(), resolveDataFieldType(field.typeLiteral())));
                }
                String javaCtor = recordStyle ? javaType : capitalize(constructor.name());
                DataConstructorInfo info = new DataConstructorInfo(constructor.name(), javaCtor, javaType, fields, recordStyle);
                constructorInfos.add(info);
                constructors.put(constructor.name(), info);
            }
            dataTypes.put(declaration.name(), new DataTypeInfo(declaration.name(), javaType, constructorInfos, recordStyle));
        }

        List<DataTypeInfo> dataTypes() {
            return new ArrayList<>(dataTypes.values());
        }

        DataConstructorInfo lookupConstructor(String name) {
            return constructors.get(name);
        }

        DataTypeInfo lookupDataType(String name) {
            return dataTypes.get(name);
        }

        boolean hasBinding(String original) {
            for (Scope scope : scopes) {
                if (scope.contains(original)) {
                    return true;
                }
            }
            return false;
        }

        void pushScope() {
            scopes.push(new Scope());
        }

        void popScope() {
            scopes.pop();
        }

        void pushAlias(String original, String alias) {
            aliasOverrides.computeIfAbsent(original, key -> new ArrayDeque<>()).push(alias);
        }

        void popAlias(String original) {
            Deque<String> aliases = aliasOverrides.get(original);
            if (aliases == null || aliases.isEmpty()) {
                return;
            }
            aliases.pop();
        }

        List<String> registerLambdaParams(List<String> params) {
            Scope scope = scopes.peek();
            List<String> sanitized = new ArrayList<>(params.size());
            for (String param : params) {
                String unique = makeUnique(param);
                scope.put(param, unique, null);
                sanitized.add(unique);
            }
            return sanitized;
        }

        List<String> registerPatternBindings(List<String> names) {
            Scope scope = scopes.peek();
            List<String> sanitized = new ArrayList<>(names.size());
            for (String original : names) {
                if ("_".equals(original)) {
                    sanitized.add(null);
                    continue;
                }
                String unique = makeUnique(original);
                scope.put(original, unique, null);
                sanitized.add(unique);
            }
            return sanitized;
        }

        ResolvedType lookupType(String original) {
            for (Scope scope : scopes) {
                ResolvedType resolved = scope.getType(original);
                if (resolved != null) {
                    return resolved;
                }
            }
            return null;
        }

        String resolveName(String original) {
            Deque<String> aliases = aliasOverrides.get(original);
            if (aliases != null && !aliases.isEmpty()) {
                return aliases.peek();
            }
            for (Scope scope : scopes) {
                String mapped = scope.get(original);
                if (mapped != null) {
                    return mapped;
                }
            }
            return original;
        }

        private String makeUnique(String baseName) {
            String candidate = baseName;
            int suffix = 1;
            while (!isNameAvailable(candidate)) {
                candidate = baseName + "_" + suffix++;
            }
            return candidate;
        }

        private boolean isNameAvailable(String candidate) {
            if (reservedNames.contains(candidate)) {
                return false;
            }
            for (Scope scope : scopes) {
                if (scope.containsSanitized(candidate)) {
                    return false;
                }
            }
            return true;
        }

        private String resolveDataFieldType(String literal) {
            if (literal == null || literal.isBlank() || "any".equalsIgnoreCase(literal)) {
                return "Object";
            }
            return switch (literal.toLowerCase()) {
                case "int" -> "int";
                case "float" -> "double";
                case "string" -> "String";
                case "boolean" -> "boolean";
                default -> typeNameMappings.getOrDefault(literal, capitalize(literal));
            };
        }

        private static final class Scope {
            private final Map<String, ScopeEntry> mappings = new HashMap<>();
            private final Set<String> sanitizedNames = new HashSet<>();

            void put(String original, String sanitized, ResolvedType typeInfo) {
                mappings.put(original, new ScopeEntry(sanitized, typeInfo));
                sanitizedNames.add(sanitized);
            }

            String get(String original) {
                ScopeEntry entry = mappings.get(original);
                return entry == null ? null : entry.sanitized();
            }

            boolean contains(String original) {
                return mappings.containsKey(original);
            }

            boolean containsSanitized(String name) {
                return sanitizedNames.contains(name);
            }

            ResolvedType getType(String original) {
                ScopeEntry entry = mappings.get(original);
                return entry == null ? null : entry.type();
            }

            private record ScopeEntry(String sanitized, ResolvedType type) {}
        }

        private record DataTypeInfo(String originalName, String javaName, List<DataConstructorInfo> constructors, boolean recordType) {
            boolean isRecordType() { return recordType; }
        }

        private record DataConstructorInfo(String originalName, String javaName, String ownerJavaType, List<DataFieldInfo> fields, boolean recordOwner) {
            boolean isRecordOwner() { return recordOwner; }
        }

        private record DataFieldInfo(String name, String javaType) { }
    }

    private static String escapeJavaString(String raw) {
        StringBuilder sb = new StringBuilder();
        for (char ch : raw.toCharArray()) {
            switch (ch) {
                case '\\' -> sb.append("\\\\");
                case '"' -> sb.append("\\\"");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                case '\b' -> sb.append("\\b");
                case '\f' -> sb.append("\\f");
                default -> {
                    if (ch < 32 || ch > 126) {
                        sb.append(String.format("\\u%04x", (int) ch));
                    } else {
                        sb.append(ch);
                    }
                }
            }
        }
        return sb.toString();
    }

    private static String formatDouble(double value) {
        return Double.toString(value);
    }

    private static String appendInlineComment(String base, String comment) {
        if (comment == null) {
            return base;
        }
        return base + " " + comment.stripTrailing();
    }

    private static List<String> renderStandaloneComment(String commentText) {
        String[] lines = commentText.split("\r?\n", -1);
        List<String> rendered = new ArrayList<>(lines.length == 0 ? 1 : lines.length);
        if (lines.length == 0) {
            rendered.add(commentText);
            return rendered;
        }
        for (String line : lines) {
            if (line.isBlank()) {
                rendered.add("//");
            } else {
                rendered.add(line.stripTrailing());
            }
        }
        return rendered;
    }

    private static final class AstOptimizer {
        private final Map<String, Operation> definitions = new HashMap<>();
        private final Map<String, Integer> constants = new HashMap<>();

        static Program optimize(Program program) {
            return new AstOptimizer().process(program);
        }

        private Program process(Program program) {
            List<Statement> optimized = new ArrayList<>(program.statements().size());
            for (Statement stmt : program.statements()) {
                Statement rewritten = optimizeStatement(stmt, null);
                optimized.add(rewritten);
                if (rewritten instanceof LetStatement let) {
                    definitions.put(let.name(), let.value());
                    if (let.value() instanceof Num num) {
                        constants.put(let.name(), num.value());
                    }
                }
            }
            return new Program(optimized);
        }

        private Statement optimizeStatement(Statement stmt, LambdaContext lambdaCtx) {
            if (stmt instanceof DataDeclaration) {
                return stmt;
            }
            if (stmt instanceof LetStatement let) {
                Operation value = optimizeOperation(let.value(), new LambdaContext(null));
                return new LetStatement(let.name(), let.declaredType(), let.declaredTypeLiteral(), value, let.comment());
            }
            if (stmt instanceof FunStatement fun) {
                List<String> params = fun.parameters().stream()
                    .map(Parameter::name)
                    .collect(Collectors.toList());
                Operation body = optimizeOperation(fun.body(), new LambdaContext(params));
                return new FunStatement(fun.name(), fun.parameters(), fun.returnType(), body, fun.comment());
            }
            if (stmt instanceof PrintStatement print) {
                Operation value = optimizeOperation(print.value(), lambdaCtx);
                return new PrintStatement(value, print.comment());
            }
            if (stmt instanceof ExprStatement expr) {
                Operation value = optimizeOperation(expr.value(), lambdaCtx);
                return new ExprStatement(value, expr.comment());
            }
            return stmt;
        }

        private Operation optimizeOperation(Operation op, LambdaContext lambdaCtx) {
            if (op instanceof Num || op instanceof VarRef || op instanceof Text || op instanceof Real) {
                return op;
            }
            if (op instanceof UnaryOper unary) {
                Operation operand = optimizeOperation(unary.operand(), lambdaCtx);
                return new UnaryOper(unary.operator(), operand);
            }
            if (op instanceof BinaryOper binary) {
                Operation left = optimizeOperation(binary.left(), lambdaCtx);
                Operation right = optimizeOperation(binary.right(), lambdaCtx);
                return new BinaryOper(binary.operator(), left, right);
            }
            if (op instanceof Call call) {
                Operation callee = optimizeOperation(call.callee(), lambdaCtx);
                List<Operation> args = new ArrayList<>(call.arguments().size());
                for (Operation argument : call.arguments()) {
                    args.add(optimizeOperation(argument, lambdaCtx));
                }
                return new Call(callee, args);
            }
            if (op instanceof Lambda lambda) {
                LambdaContext ctx = new LambdaContext(lambdaParamNames(lambda.params()));
                Operation body = optimizeOperation(lambda.body(), ctx);
                return new Lambda(lambda.params(), body);
            }
            if (op instanceof Ternary ternary) {
                Operation cond = optimizeOperation(ternary.cond(), lambdaCtx);
                Operation thenExpr = optimizeOperation(ternary.thenExpr(), lambdaCtx);
                Operation elseExpr = optimizeOperation(ternary.elseExpr(), lambdaCtx);
                Operation rewrittenCond = tryRewriteCond(cond, thenExpr, elseExpr, lambdaCtx);
                return new Ternary(rewrittenCond, thenExpr, elseExpr);
            }
            if (op instanceof DataConstructorCall ctorCall) {
                List<Operation> args = new ArrayList<>(ctorCall.arguments().size());
                for (Operation argument : ctorCall.arguments()) {
                    args.add(optimizeOperation(argument, lambdaCtx));
                }
                return new DataConstructorCall(ctorCall.name(), args);
            }
            if (op instanceof Match match) {
                Operation target = optimizeOperation(match.target(), lambdaCtx);
                List<MatchCase> cases = new ArrayList<>(match.cases().size());
                for (MatchCase matchCase : match.cases()) {
                    Operation body = optimizeOperation(matchCase.body(), lambdaCtx);
                    cases.add(new MatchCase(matchCase.pattern(), body));
                }
                return new Match(target, cases);
            }
            if (op instanceof TypeCast cast) {
                Operation value = optimizeOperation(cast.value(), lambdaCtx);
                return new TypeCast(value, cast.targetType());
            }
            return op;
        }

        private Operation tryRewriteCond(Operation cond, Operation thenExpr, Operation elseExpr, LambdaContext lambdaCtx) {
            if (lambdaCtx == null || !lambdaCtx.hasSingleParam()) {
                return cond;
            }
            Integer thenValue = extractConstantValue(thenExpr);
            Integer elseValue = extractConstantValue(elseExpr);
            if (thenValue == null || elseValue == null) {
                return cond;
            }
            if (thenValue != 1 || elseValue != 0) {
                return cond;
            }
            if (!(cond instanceof Call call) || call.arguments().size() != 1) {
                return cond;
            }
            Operation argument = call.arguments().get(0);
            if (!(argument instanceof VarRef argVar) || !argVar.name().equals(lambdaCtx.firstParam())) {
                return cond;
            }
            Operation callee = call.callee();
            if (!(callee instanceof VarRef calleeVar)) {
                return cond;
            }
            Operation definition = definitions.get(calleeVar.name());
            if (!(definition instanceof Lambda targetLambda) || !isZeroLikeLambda(targetLambda)) {
                return cond;
            }
            return argument;
        }

        private boolean isZeroLikeLambda(Lambda lambda) {
            if (lambda.params().size() != 1) {
                return false;
            }
            String param = lambda.params().get(0).name();
            Operation body = lambda.body();
            if (!(body instanceof Ternary ternary)) {
                return false;
            }
            if (!(ternary.cond() instanceof VarRef condVar) || !condVar.name().equals(param)) {
                return false;
            }
            return ternary.thenExpr() instanceof Num thenNum && thenNum.value() == 0
                && ternary.elseExpr() instanceof Num elseNum && elseNum.value() == 1;
        }

        private Integer extractConstantValue(Operation op) {
            if (op instanceof Num num) {
                return num.value();
            }
            if (op instanceof VarRef var) {
                return constants.get(var.name());
            }
            return null;
        }

        private static final class LambdaContext {
            private final List<String> params;

            LambdaContext(List<String> params) {
                this.params = params;
            }

            boolean hasSingleParam() {
                return params != null && params.size() == 1;
            }

            String firstParam() {
                return params.get(0);
            }
        }
    }
}
