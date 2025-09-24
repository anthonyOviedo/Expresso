package com.diezam04.expresso.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

import com.diezam04.expresso.core.transpiler.generated.ExprParser;

class TranspilerTest {

    @Test
    void runReturnsSourceWhenInputIsValid() {
        String source = "1 + 2\n";

        String result = Transpiler.run(source);

        assertEquals(source, result);
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
        ExprParser.ProgContext prog = (ExprParser.ProgContext) tree;
        assertEquals(1, prog.stat().size(), "Expected a single statement in the program");
    }
}
