package com.diezam04.expresso.core.transpiler;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import com.diezam04.expresso.core.transpiler.generated.ExprBaseVisitor;
import com.diezam04.expresso.core.transpiler.generated.ExprParser;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.BinaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Node;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Num;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Oper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Operation;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Program;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.UnaryOper;

public final class AstBuilder extends ExprBaseVisitor<Node> {

    public Program build(ParseTree tree) {
        return (Program) visit(tree);
    }

    @Override
    public Node visitProg(ExprParser.ProgContext ctx) {
        List<Operation> statements = new ArrayList<>();
        for (ExprParser.StatContext statCtx : ctx.stat()) {
            Operation operation = (Operation) visit(statCtx);
            if (operation != null) {
                statements.add(operation);
            }
        }
        return new Program(statements);
    }

    @Override
    public Node visitLetStat(ExprParser.LetStatContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Node visitPrintStat(ExprParser.PrintStatContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Node visitExprStat(ExprParser.ExprStatContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Node visitBlankStat(ExprParser.BlankStatContext ctx) {
        return null;
    }

    @Override
    public Node visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Node visitMulDiv(ExprParser.MulDivContext ctx) {
        Operation left = (Operation) visit(ctx.expr(0));
        Operation right = (Operation) visit(ctx.expr(1));
        return new BinaryOper(new Oper(ctx.op.getText()), left, right);
    }

    @Override
    public Node visitAddSub(ExprParser.AddSubContext ctx) {
        Operation left = (Operation) visit(ctx.expr(0));
        Operation right = (Operation) visit(ctx.expr(1));
        return new BinaryOper(new Oper(ctx.op.getText()), left, right);
    }

    @Override
    public Node visitUnaryMinus(ExprParser.UnaryMinusContext ctx) {
        Operation operand = (Operation) visit(ctx.expr());
        return new UnaryOper(new Oper("-"), operand);
    }

    @Override
    public Node visitInt(ExprParser.IntContext ctx) {
        return new Num(Integer.parseInt(ctx.INT().getText()));
    }
}
