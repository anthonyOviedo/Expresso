package com.diezam04.expresso.core.transpiler.src.ast;

import java.util.Objects;

public final class Oper {
    private final String symbol;

    public Oper(String symbol) {
        this.symbol = Objects.requireNonNull(symbol, "symbol");
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
