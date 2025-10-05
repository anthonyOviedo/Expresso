package com.diezam04.expresso.core.transpiler;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import com.diezam04.expresso.core.transpiler.generated.ExprBaseVisitor;
import com.diezam04.expresso.core.transpiler.generated.ExprParser;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.BinaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Call;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Num;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Oper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Operation;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Program;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.UnaryOper;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.VarRef;


public final class AstBuilder extends ExprBaseVisitor<Operation> {

    public Program build(ParseTree tree) {
        List<Operation> stmts = new ArrayList<>();
        for (ParseTree stat : ((ExprParser.ProgContext) tree).stat()) {
            Operation op = visit(stat);
            if (op != null) stmts.add(op);
        }
        return new Program(stmts);
    }

    @Override
    public Operation visitInt(ExprParser.IntContext ctx) {
        return new Num(Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public Operation visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Operation visitAddSub(ExprParser.AddSubContext ctx) {
        return new BinaryOper(new Oper(ctx.op.getText()), visit(ctx.expr(0)), visit(ctx.expr(1)));
    }

    @Override
    public Operation visitMulDiv(ExprParser.MulDivContext ctx) {
        return new BinaryOper(new Oper(ctx.op.getText()), visit(ctx.expr(0)), visit(ctx.expr(1)));
    }

    @Override
    public Operation visitUnaryMinus(ExprParser.UnaryMinusContext ctx) {
        return new UnaryOper(new Oper("-"), visit(ctx.expr()));
    }

    @Override
    public Operation visitLetStat(ExprParser.LetStatContext ctx) {
        return new BinaryOper(new Oper("="), new VarRef(ctx.ID().getText()), visit(ctx.expr()));
    }

    @Override
    public Operation visitPrintStat(ExprParser.PrintStatContext ctx) {
        Operation expr = visit(ctx.expr());
        if (expr == null) {
            expr = new Num(0);
        }
        return new Call(new VarRef("print"), expr);
    }

 

}
