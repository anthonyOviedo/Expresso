package com.diezam04.expresso.core.transpiler.src.ast;

import java.util.Objects;

public final class Id extends Operation {
    private final String name;

    public Id(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
