// ----------------------------------------------------------------------------------------------------
//          Authors: 
//          Antony Oviedo Alfaro ID: 207640246
//          Esteban Francisco Sánchez Sánchez  ID: 402640418
// ----------------------------------------------------------------------------------------------------
package com.diezam04.expresso.core.transpiler.src.ast;

import java.util.List;
import java.util.Objects;

public final class Ast {

    private Ast() { throw new AssertionError("Utility class"); }

    public sealed interface Node permits Program, Statement, Operation { }

    public sealed interface Statement extends Node permits LetStatement, PrintStatement, ExprStatement, CommentStatement { }

    public sealed interface Operation extends Node permits Num, UnaryOper, BinaryOper, Lambda, Call, VarRef, Ternary { }

    public static record Num(int value) implements Operation { }

    public static record Program(List<Statement> statements) implements Node {
        public Program { statements = List.copyOf(Objects.requireNonNull(statements)); }
    }

    public static record LetStatement(String name, Operation value, String comment) implements Statement {
        public LetStatement {
            Objects.requireNonNull(name);
            Objects.requireNonNull(value);
            comment = normalizeComment(comment);
        }
    }

    public static record PrintStatement(Operation value, String comment) implements Statement {
        public PrintStatement {
            Objects.requireNonNull(value);
            comment = normalizeComment(comment);
        }
    }

    public static record ExprStatement(Operation value, String comment) implements Statement {
        public ExprStatement {
            Objects.requireNonNull(value);
            comment = normalizeComment(comment);
        }
    }

    public static record CommentStatement(String text) implements Statement {
        public CommentStatement {
            Objects.requireNonNull(text);
        }
    }

    public static record Oper(String symbol) {
        public Oper { if (symbol == null || symbol.isBlank()) throw new IllegalArgumentException("Operator symbol must not be blank"); }
    }

    public static record UnaryOper(Oper operator, Operation operand) implements Operation {
        public UnaryOper { Objects.requireNonNull(operator); Objects.requireNonNull(operand); }
    }

    public static record BinaryOper(Oper operator, Operation left, Operation right) implements Operation {
        public BinaryOper { Objects.requireNonNull(operator); Objects.requireNonNull(left); Objects.requireNonNull(right); }
    }

    public static record Lambda(List<String> params, Operation body) implements Operation {
        public Lambda { Objects.requireNonNull(params); Objects.requireNonNull(body); }
    }

    public static record Call(Operation callee, List<Operation> arguments) implements Operation {
        public Call {
            Objects.requireNonNull(callee);
            arguments = List.copyOf(Objects.requireNonNull(arguments));
        }
    }

    public static record VarRef(String name) implements Operation {
        public VarRef { Objects.requireNonNull(name); }
    }

    public static record Ternary(Operation cond, Operation thenExpr, Operation elseExpr) implements Operation {
        public Ternary { Objects.requireNonNull(cond); Objects.requireNonNull(thenExpr); Objects.requireNonNull(elseExpr); }
    }

    private static String normalizeComment(String comment) {
        return (comment == null || comment.isBlank()) ? null : comment;
    }
}
