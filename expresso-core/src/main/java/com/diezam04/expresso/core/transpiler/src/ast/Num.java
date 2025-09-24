package com.diezam04.expresso.core.transpiler.src.ast;

public final class Num extends Operation {
    private final int value;

    public Num(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
