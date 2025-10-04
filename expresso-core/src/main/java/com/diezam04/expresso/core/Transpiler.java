package com.diezam04.expresso.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.diezam04.expresso.core.transpiler.AstBuilder;
import com.diezam04.expresso.core.transpiler.generated.ExprLexer;
import com.diezam04.expresso.core.transpiler.generated.ExprParser;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Program;

public final class Transpiler {

    private Transpiler() {
        throw new AssertionError("Utility class");
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: Transpiler <path-to-source>");
            return;
        }

        Path sourcePath = Path.of(args[0]);
        try {
            String source = Files.readString(sourcePath);
            String output = run(source);

            if (output.isEmpty()) {
                System.err.println("Transpilation failed; check logs for details.");
                return;
            }

            ExprParser parser = buildParser(source);
            ParseTree tree = parser.prog();
            Program program = new AstBuilder().build(tree);

            System.out.println("=== Parse Tree ===");
            System.out.println(tree.toStringTree(parser));
            System.out.println();

            System.out.println("=== AST ===");
            System.out.println(program);
            System.out.println();

            System.out.println("=== Transpiled Output ===");
            System.out.println(output);
        } catch (IOException ex) {
            System.err.println("Failed to read source file: " + ex.getMessage());
        }
    }

    public static String run(String expressoSource) {
        if (expressoSource == null || expressoSource.isBlank()) {
            Utils.log("Transpiler received empty source", "ERROR");
            return "";
        }

        try {
            ParseTree tree = parseToAst(expressoSource);
            Program program = new AstBuilder().build(tree);
            Utils.log(program.toString(), "INFO");
            return expressoSource;
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
