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

    public sealed interface Statement extends Node permits LetStatement, PrintStatement, ExprStatement, CommentStatement, FunStatement, DataDeclaration { }

    public sealed interface Operation extends Node permits Num, Real, UnaryOper, BinaryOper, Lambda, Call, VarRef, Ternary, Text, DataConstructorCall, Match, TypeCast { }

    public static record Num(int value) implements Operation { }
    public static record Real(double value) implements Operation { }

    public static record Program(List<Statement> statements) implements Node {
        public Program { statements = List.copyOf(Objects.requireNonNull(statements)); }
    }

    public static record LetStatement(String name, ValueType declaredType, String declaredTypeLiteral, Operation value, String comment) implements Statement {
        public LetStatement {
            Objects.requireNonNull(name);
            Objects.requireNonNull(value);
            if (declaredType != null && (declaredTypeLiteral == null || declaredTypeLiteral.isBlank())) {
                throw new IllegalArgumentException("Declared type literal must not be blank when a type is provided");
            }
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

    public static record DataDeclaration(String name, List<DataConstructor> constructors, String comment) implements Statement {
        public DataDeclaration {
            Objects.requireNonNull(name);
            constructors = List.copyOf(Objects.requireNonNull(constructors));
            comment = normalizeComment(comment);
        }
    }

    public static record DataConstructor(String name, List<DataField> fields) {
        public DataConstructor {
            Objects.requireNonNull(name);
            fields = List.copyOf(Objects.requireNonNull(fields));
        }
    }

    public static record DataField(String name, String typeLiteral) {
        public DataField {
            Objects.requireNonNull(name);
        }
    }

    public static record FunStatement(
            String name,
            List<Parameter> parameters,
            ValueType returnType,
            Operation body,
            String comment) implements Statement {
        public FunStatement {
            Objects.requireNonNull(name);
            parameters = List.copyOf(Objects.requireNonNull(parameters));
            Objects.requireNonNull(returnType);
            Objects.requireNonNull(body);
            comment = normalizeComment(comment);
        }
    }

    public static record Parameter(String name, ValueType type) {
        public Parameter {
            Objects.requireNonNull(name);
            Objects.requireNonNull(type);
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

    public static record Lambda(List<LambdaParam> params, Operation body) implements Operation {
        public Lambda {
            params = List.copyOf(Objects.requireNonNull(params));
            Objects.requireNonNull(body);
        }
    }

    public static record LambdaParam(String name, ValueType type) {
        public LambdaParam {
            Objects.requireNonNull(name);
        }
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

    public static record Text(String value) implements Operation {
        public Text {
            Objects.requireNonNull(value);
        }
    }

    public static record TypeCast(Operation value, ValueType targetType) implements Operation {
        public TypeCast {
            Objects.requireNonNull(value);
            Objects.requireNonNull(targetType);
        }
    }

    public static record DataConstructorCall(String name, List<Operation> arguments) implements Operation {
        public DataConstructorCall {
            Objects.requireNonNull(name);
            arguments = List.copyOf(Objects.requireNonNull(arguments));
        }
    }

    public static record Match(Operation target, List<MatchCase> cases) implements Operation {
        public Match {
            Objects.requireNonNull(target);
            cases = List.copyOf(Objects.requireNonNull(cases));
        }
    }

    public static record MatchCase(Pattern pattern, Operation body) {
        public MatchCase {
            Objects.requireNonNull(pattern);
            Objects.requireNonNull(body);
        }
    }

    public sealed interface Pattern permits ConstructorPattern, WildcardPattern { }

    public static record ConstructorPattern(String constructorName, List<String> bindings) implements Pattern {
        public ConstructorPattern {
            Objects.requireNonNull(constructorName);
            bindings = List.copyOf(Objects.requireNonNull(bindings));
        }
    }

    public static final class WildcardPattern implements Pattern {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof WildcardPattern;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    public enum ValueType {
        INT("int", "Integer"),
        FLOAT("double", "Double"),
        STRING("String", "String"),
        BOOLEAN("boolean", "Boolean"),
        ANY("Object", "Object");

        private final String javaRepresentation;
        private final String boxedRepresentation;

        ValueType(String javaRepresentation, String boxedRepresentation) {
            this.javaRepresentation = javaRepresentation;
            this.boxedRepresentation = boxedRepresentation;
        }

        public String javaName() {
            return javaRepresentation;
        }

        public String boxedJavaName() {
            return boxedRepresentation;
        }

        public boolean isNumeric() {
            return this == INT || this == FLOAT;
        }

        public static ValueType promoteNumeric(ValueType left, ValueType right) {
            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }
            if (left == FLOAT || right == FLOAT) {
                return FLOAT;
            }
            if (left == INT && right == INT) {
                return INT;
            }
            return null;
        }

        public static ValueType fromLiteral(String literal) {
            if (literal == null || literal.isBlank()) {
                return ANY;
            }
            if ("int".equalsIgnoreCase(literal)) {
                return INT;
            }
            if ("float".equalsIgnoreCase(literal)) {
                return FLOAT;
            }
            if ("double".equalsIgnoreCase(literal)) {
                return FLOAT;
            }
            if ("string".equalsIgnoreCase(literal)) {
                return STRING;
            }
            if ("boolean".equalsIgnoreCase(literal)) {
                return BOOLEAN;
            }
            if ("any".equalsIgnoreCase(literal)) {
                return ANY;
            }
            return ANY;
        }
    }

    private static String normalizeComment(String comment) {
        return (comment == null || comment.isBlank()) ? null : comment;
    }
}
