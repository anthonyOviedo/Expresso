// ----------------------------------------------------------------------------------------------------
//          Authors: 
//          Antony Oviedo Alfaro ID: 207640246
//          Esteban Francisco Sánchez Sánchez  ID: 402640418
// ----------------------------------------------------------------------------------------------------

package com.diezam04.expresso.core;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.diezam04.expresso.core.transpiler.AstBuilder;
import com.diezam04.expresso.core.transpiler.ExprLexer;
import com.diezam04.expresso.core.transpiler.ExprParser;
import com.diezam04.expresso.core.transpiler.Visitor;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Program;

public final class Transpiler {

    private Transpiler() { throw new AssertionError("Utility class"); }

    public static String run(String expressoSource) {
        return run(expressoSource, "Main");        
    }

    public static String run(String expressoSource, String className) {
        if (expressoSource == null || expressoSource.isBlank()) {
            Utils.log("Transpiler received empty source", "ERROR");
            return "";
        }
        try {
            ParseTree tree = parseToAst(expressoSource);
            Program program = new AstBuilder().build(tree);
            return Visitor.generate(program, className);
        } catch (RuntimeException ex) {
            Utils.log("Transpilation failed: " + ex.getMessage(), "ERROR");
            return "";
        }
    }

    public static ParseTree parseToAst(String source) {
        return buildParser(source).prog();
    }

    private static ExprParser buildParser(String source) {
        CharStream input = CharStreams.fromString(source);
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        parser.setBuildParseTree(true);
        return parser;
    }
}
