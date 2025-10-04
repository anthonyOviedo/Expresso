package com.diezam04.expresso.core.transpiler.src.ast;

import java.util.List;
import java.util.Objects;

public final class Ast {

    private Ast() {
        throw new AssertionError("Utility class");
    }

    /**
     * Basic interpreter support that mirrors the structure used in the Node sample.
     */
    public static int evaluate(Operation node) {
        return switch (node) {
            case Num(var value) -> value;
            case UnaryOper(var operator, var operand) -> switch (operator.symbol()) {
                case "-" -> -evaluate(operand);
                default -> throw new UnsupportedOperationException("Operator not supported: " + operator.symbol());
            };
            case BinaryOper(var operator, var left, var right) -> {
                int leftValue = evaluate(left);
                int rightValue = evaluate(right);
                yield switch (operator.symbol()) {
                    case "+" -> leftValue + rightValue;
                    case "-" -> leftValue - rightValue;
                    case "*" -> leftValue * rightValue;
                    case "/" -> {
                        if (rightValue == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        yield leftValue / rightValue;
                    }
                    default -> throw new UnsupportedOperationException("Operator not supported: " + operator.symbol());
                };
            }
        };
    }

    public sealed interface Node permits Program, Operation { }

    public sealed interface Operation extends Node permits Num, UnaryOper, BinaryOper { }

    public static record Num(int value) implements Operation { }

    public static record Program(List<Operation> statements) implements Node {
        public Program {
            statements = List.copyOf(Objects.requireNonNull(statements, "statements"));
        }
    }

    public static record Oper(String symbol) {
        public Oper {
            if (symbol == null || symbol.isBlank()) {
                throw new IllegalArgumentException("Operator symbol must not be blank");
            }
        }
    }

    public static record UnaryOper(Oper operator, Operation operand) implements Operation {
        public UnaryOper {
            Objects.requireNonNull(operator, "operator");
            Objects.requireNonNull(operand, "operand");
        }
    }

    public static record BinaryOper(Oper operator, Operation left, Operation right) implements Operation {
        public BinaryOper {
            Objects.requireNonNull(operator, "operator");
            Objects.requireNonNull(left, "left");
            Objects.requireNonNull(right, "right");
        }
    }
}

