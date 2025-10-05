package com.diezam04.expresso.core.transpiler;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
        GenerationContext context = new GenerationContext();
        String normalizedClassName = capitalize(className);

        List<String> statements = new ArrayList<>();
        for (Statement statement : program.statements()) {
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
            String base = type + " " + letStatement.name() + " = " + genExpr(letStatement.value(), context) + ";";
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
            return var.name();
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
                        String assignment = variable.name() + " = " + genExpr(binary.right(), context);
                        yield assignment;
                    }
                    yield "(" + left + " = " + right + ")";
                }
                default -> "(" + left + " " + binary.operator().symbol() + " " + right + ")";
            };
        }
        if (expr instanceof Ternary ternary) {
            return "(" + genExpr(ternary.cond(), context) + " ? "
                + genExpr(ternary.thenExpr(), context) + " : "
                + genExpr(ternary.elseExpr(), context) + ")";
        }
        if (expr instanceof Lambda lambda) {
            context.requireFunctionalInterface(lambda.params().size());
            String params = lambda.params().isEmpty()
                ? "()"
                : "(" + String.join(", ", lambda.params()) + ")";
            return params + " -> " + genExpr(lambda.body(), context);
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
}
