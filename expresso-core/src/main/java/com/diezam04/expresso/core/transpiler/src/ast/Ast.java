package com.diezam04.expresso.core.transpiler.src.ast;

import java.util.List;
import java.util.Objects;

public final class Ast {

    private Ast() { throw new AssertionError("Utility class"); }

    public sealed interface Node permits Program, Operation { }
    public sealed interface Operation extends Node permits Num, UnaryOper, BinaryOper, Lambda, Call, VarRef, Ternary { }

    public static record Num(int value) implements Operation { }

    public static record Program(List<Operation> statements) implements Node {
        public Program { statements = List.copyOf(Objects.requireNonNull(statements)); }
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

    public static record Call(Operation lambda, Operation argument) implements Operation {
        public Call { Objects.requireNonNull(lambda); Objects.requireNonNull(argument); }
    }

    public static record VarRef(String name) implements Operation {
        public VarRef { Objects.requireNonNull(name); }
    }

    public static record Ternary(Operation cond, Operation thenExpr, Operation elseExpr) implements Operation {
        public Ternary { Objects.requireNonNull(cond); Objects.requireNonNull(thenExpr); Objects.requireNonNull(elseExpr); }
    }
}
