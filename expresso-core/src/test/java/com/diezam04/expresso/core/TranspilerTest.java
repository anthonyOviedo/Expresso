package com.diezam04.expresso.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;

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

        // Regex tolerante a espacios y saltos de línea (DOTALL)
        String regex =
            "^package\\s+com\\.diezam04\\.expresso\\.generated;\\R+" +          // package
            "public\\s+class\\s+Main\\s*\\{\\R+" +                              // class header
            "\\s*public\\s+static\\s+void\\s+main\\(String\\[\\]\\s+args\\)\\s*\\{\\R+" + // main
            "\\s*//\\s*---\\s*Transpiled\\s+Expresso\\s+code\\s*---\\R+" +     // comment
            "\\s*1\\s*\\+\\s*2\\R+" +                                          // the source
            "\\s*\\}\\R+" +                                                    // close main
            "\\}\\R*\\z";                                                      // close class (EOF)

        assertTrue(Pattern.compile(regex, Pattern.DOTALL).matcher(result).find(),
            () -> "Output did not match expected Java wrapper.\n--- Got ---\n" + result);
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
