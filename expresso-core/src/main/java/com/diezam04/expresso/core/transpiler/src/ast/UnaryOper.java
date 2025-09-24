package com.diezam04.expresso.core.transpiler.src.ast;

import java.util.Objects;

public final class UnaryOper extends Operation {
    private final Oper operator;
    private final Operation operand;

    public UnaryOper(Oper operator, Operation operand) {
        this.operator = Objects.requireNonNull(operator, "operator");
        this.operand = Objects.requireNonNull(operand, "operand");
    }

    public Oper getOperator() {
        return operator;
    }

    public Operation getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        return operator + "" + operand;
    }
}
