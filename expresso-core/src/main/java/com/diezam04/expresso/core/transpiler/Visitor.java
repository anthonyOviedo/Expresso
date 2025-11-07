// ----------------------------------------------------------------------------------------------------
//          Authors: 
//          Antony Oviedo Alfaro ID: 207640246
//          Esteban Francisco Sánchez Sánchez  ID: 402640418
// ----------------------------------------------------------------------------------------------------

package com.diezam04.expresso.core.transpiler;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.diezam04.expresso.core.transpiler.src.ast.Ast.BinaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Call;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.CommentStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.ExprStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Lambda;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.LetStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Num;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Operation;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.PrintStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Program;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Statement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Ternary;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.UnaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.VarRef;

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
            String type = resolveType(letStatement.value(), context);
            String sanitizedName = context.reserveLetName(letStatement.name());
            String base = type + " " + sanitizedName + " = " + genExpr(letStatement.value(), context) + ";";
            context.registerLet(letStatement.name(), sanitizedName);
            return List.of(appendInlineComment(base, letStatement.comment()));
        }
        if (stmt instanceof PrintStatement printStatement) {
            String expr = genExpr(printStatement.value(), context);
            String base = "print(" + expr + ");";
            return List.of(appendInlineComment(base, printStatement.comment()));
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

    private static String genExpr(Operation expr, GenerationContext context) {
        if (expr instanceof Num num) {
            return String.valueOf(num.value());
        }
        if (expr instanceof VarRef var) {
            return context.resolveName(var.name());
        }
        if (expr instanceof UnaryOper unary) {
            return unary.operator().symbol() + genExpr(unary.operand(), context);
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
            context.requireFunctionalInterface(lambda.params().size());
            context.pushScope();
            List<String> params = context.registerLambdaParams(lambda.params());
            String renderedParams = params.isEmpty()
                ? "()"
                : "(" + String.join(", ", params) + ")";
            String body = genExpr(lambda.body(), context);
            context.popScope();
            return renderedParams + " -> " + body;
        }
        if (expr instanceof Call call) {
            String callee = genExpr(call.callee(), context);
            if (call.arguments().isEmpty()) {
                context.requireSupplier();
                return callee + ".get()";
            }
            String args = call.arguments().stream()
                .map(arg -> genExpr(arg, context))
                .collect(Collectors.joining(", "));
            return callee + ".apply(" + args + ")";
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

    private static String resolveType(Operation rhs, GenerationContext context) {
        if (rhs instanceof Num) {
            return "int";
        }
        if (rhs instanceof Lambda lambda) {
            int arity = lambda.params().size();
            context.requireFunctionalInterface(arity);
            return switch (arity) {
                case 0 -> "Supplier<Integer>";
                case 1 -> "Function<Integer, Integer>";
                case 2 -> "BiFunction<Integer, Integer, Integer>";
                default -> throw new UnsupportedOperationException("Lambdas with " + arity + " parameters are not supported yet");
            };
        }
        return "var";
    }

    private static final class GenerationContext {
        private final Set<String> imports = new LinkedHashSet<>();
        private final Deque<Scope> scopes = new ArrayDeque<>();
        private final Set<String> reservedNames = new HashSet<>();

        GenerationContext() {
            scopes.push(new Scope());
        }

        void requireFunctionalInterface(int arity) {
            switch (arity) {
                case 0 -> requireSupplier();
                case 1 -> requireFunction();
                case 2 -> requireBiFunction();
                default -> { throw new UnsupportedOperationException("Functional arity " + arity + " not supported"); }
            }
        }

        void requireSupplier() {
            imports.add("java.util.function.Supplier");
        }

        void requireFunction() {
            imports.add("java.util.function.Function");
        }

        void requireBiFunction() {
            imports.add("java.util.function.BiFunction");
        }

        List<String> imports() {
            return new ArrayList<>(imports);
        }

        String reserveLetName(String original) {
            String sanitized = makeUnique(original);
            reservedNames.add(sanitized);
            return sanitized;
        }

        void registerLet(String original, String sanitized) {
            Scope scope = scopes.peek();
            scope.put(original, sanitized);
        }

        void pushScope() {
            scopes.push(new Scope());
        }

        void popScope() {
            scopes.pop();
        }

        List<String> registerLambdaParams(List<String> params) {
            Scope scope = scopes.peek();
            List<String> sanitized = new ArrayList<>(params.size());
            for (String param : params) {
                String unique = makeUnique(param);
                scope.put(param, unique);
                sanitized.add(unique);
            }
            return sanitized;
        }

        String resolveName(String original) {
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

        private static final class Scope {
            private final Map<String, String> mappings = new HashMap<>();
            private final Set<String> sanitizedNames = new HashSet<>();

            void put(String original, String sanitized) {
                mappings.put(original, sanitized);
                sanitizedNames.add(sanitized);
            }

            String get(String original) {
                return mappings.get(original);
            }

            boolean containsSanitized(String name) {
                return sanitizedNames.contains(name);
            }
        }
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
            if (stmt instanceof LetStatement let) {
                Operation value = optimizeOperation(let.value(), new LambdaContext(null));
                return new LetStatement(let.name(), value, let.comment());
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
            if (op instanceof Num || op instanceof VarRef) {
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
                LambdaContext ctx = new LambdaContext(lambda.params());
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
            String param = lambda.params().get(0);
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
