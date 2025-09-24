package com.diezam04.expresso.core.transpiler.src.ast;

import java.util.List;
import java.util.Objects;

public final class Program extends Node {
    private final List<Operation> statements;

    public Program(List<Operation> statements) {
        this.statements = List.copyOf(Objects.requireNonNull(statements, "statements"));
    }

    public List<Operation> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        return "Program" + statements;
    }
}
