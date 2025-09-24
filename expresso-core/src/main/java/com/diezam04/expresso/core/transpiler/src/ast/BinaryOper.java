package com.diezam04.expresso.core.transpiler.src.ast;

import java.util.Objects;

public final class BinaryOper extends Operation {
    private final Oper operator;
    private final Operation left;
    private final Operation right;

    public BinaryOper(Oper operator, Operation left, Operation right) {
        this.operator = Objects.requireNonNull(operator, "operator");
        this.left = Objects.requireNonNull(left, "left");
        this.right = Objects.requireNonNull(right, "right");
    }

    public Oper getOperator() {
        return operator;
    }

    public Operation getLeft() {
        return left;
    }

    public Operation getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "(" + left + " " + operator + " " + right + ")";
    }
}
