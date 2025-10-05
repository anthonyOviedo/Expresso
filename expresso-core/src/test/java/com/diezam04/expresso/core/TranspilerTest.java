package com.diezam04.expresso.core;

import static org.junit.jupiter.api.Assertions.*;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

import com.diezam04.expresso.core.transpiler.generated.ExprParser;

class TranspilerTest {

    @Test
    void runWrapsValidInputInJavaTemplate() {
        String source = "1 + 2\n";

        String result = Transpiler.run(source);
        assertNotNull(result, "Transpiler.run should not return null for valid input");
        assertFalse(result.isBlank(), "Transpiler.run should return non-blank output for valid input");

        assertTrue(result.contains("public class Main {"),
            () -> "Expected generated class declaration.\n--- Got ---\n" + result);
        assertTrue(result.contains("public static void print(Object arg)"),
            () -> "Expected print helper method.\n--- Got ---\n" + result);
        assertTrue(result.contains("public static void main(String... args) {"),
            () -> "Expected main method signature.\n--- Got ---\n" + result);
        assertTrue(result.contains("(1 + 2);"),
            () -> "Expected the original expression to appear in the main body.\n--- Got ---\n" + result);
    }

    @Test
    void runTranspilesLetStatementToIntAndPrintCall() {
        String source = String.join("\n",
            "let x = 3",
            "print(x) // hello",
            "");

        String result = Transpiler.run(source);

        assertTrue(result.contains("int x = 3;"),
            () -> "Expected integer assignment.\n--- Got ---\n" + result);
        assertTrue(result.contains("print(x); // hello"),
            () -> "Expected inline comment to be preserved.\n--- Got ---\n" + result);
    }

    @Test
    void runTranspilesLambdaDefinitionAndInvocation() {
        String source = String.join("\n",
            "let x = 6",
            "let y = 3",
            "let f = (x, z) -> z ** x + x*z + 1",
            "print(f(x, y))",
            "");

        String result = Transpiler.run(source);

        assertTrue(result.contains("BiFunction<Integer, Integer, Integer> f = (x, z) ->"),
            () -> "Expected BiFunction assignment.\n--- Got ---\n" + result);
        assertTrue(result.contains("print(f.apply(x, y));"),
            () -> "Expected BiFunction invocation via apply.\n--- Got ---\n" + result);
        assertTrue(result.contains("(int) Math.pow(z, x)"),
            () -> "Expected exponentiation to map to Math.pow.\n--- Got ---\n" + result);
    }

    @Test
    void runPreservesStandaloneAndTrailingComments() {
        String source = String.join("\n",
            "// heading",
            "let x = 1",
            "print(x) // trailing",
            "/* block */",
            "print(x + 1)",
            "");

        String result = Transpiler.run(source);

        assertTrue(result.contains("// heading"),
            () -> "Expected leading comment.\n--- Got ---\n" + result);
        assertTrue(result.contains("print(x); // trailing"),
            () -> "Expected trailing comment.\n--- Got ---\n" + result);
        assertTrue(result.contains("/* block */"),
            () -> "Expected block comment line.\n--- Got ---\n" + result);
    }

    @Test
    void runReturnsEmptyStringWhenSourceBlankOrNull() {
        assertEquals("", Transpiler.run(null));
        assertEquals("", Transpiler.run("   \n"));
    }

    @Test
    void parseToAstBuildsProgContext() {
        String source = "3 * (4 - 1)\n";

        ParseTree tree = Transpiler.parseToAst(source);

        assertNotNull(tree, "Parse tree should not be null");
        assertTrue(tree instanceof ExprParser.ProgContext, "Root should be a ProgContext");
        var prog = (ExprParser.ProgContext) tree;
        assertEquals(1, prog.stat().size(), "Expected a single statement in the program");
    }
}
