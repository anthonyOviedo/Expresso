package com.diezam04.expresso.core.transpiler;

import com.diezam04.expresso.core.transpiler.src.ast.Ast.BinaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Call;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Num;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Operation;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Program;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.UnaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.VarRef;

public final class Visitor {

    private Visitor() { throw new AssertionError("Utility class"); }

    public static String generate(Program program, String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("import java.util.function.UnaryOperator;\n");
        sb.append("public class ").append(capitalize(className)).append(" {\n\n");
        sb.append("    public static void print(Object arg) { System.out.println(arg); }\n\n");
        sb.append("    public static void main(String... args) {\n");
        for (Operation stmt : program.statements())
            sb.append("        ").append(genStatement(stmt)).append("\n");
        sb.append("    }\n");
        sb.append("}\n");
        return sb.toString();
    }

    private static String capitalize(String name) {
        if (name == null || name.isEmpty()) return "Main";
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    private static String genStatement(Operation stmt) {
        if (stmt instanceof BinaryOper b && "=".equals(b.operator().symbol())) {
            return "int " + ((VarRef)b.left()).name() + " = " + genExpr(b.right()) + ";";
        } else if (stmt instanceof Call c && ((VarRef)c.lambda()).name().equals("print")) {
            return "print(" + genExpr(c.argument()) + ");";
        }
        return "";
    }

    private static String genExpr(Operation expr) {
        return switch (expr) {
            case Num n -> String.valueOf(n.value());
            case VarRef v -> v.name();
            case UnaryOper u -> u.operator().symbol() + genExpr(u.operand());
            case BinaryOper b -> {
                if ("**".equals(b.operator().symbol()))
                    yield "pow(" + genExpr(b.left()) + ", " + genExpr(b.right()) + ")";
                else
                    yield "(" + genExpr(b.left()) + " " + b.operator().symbol() + " " + genExpr(b.right()) + ")";
            }
            default -> expr.toString();
        };
    }
}
