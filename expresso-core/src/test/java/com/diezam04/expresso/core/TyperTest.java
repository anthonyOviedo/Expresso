package com.diezam04.expresso.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.diezam04.expresso.core.transpiler.AstBuilder;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Program;

class TyperTest {

    @TempDir
    Path tempDir;

    @Test
    void typerWritesLetDeclarations() throws IOException {
        Path testFile = tempDir.resolve("Sample.expresso");
        String source = """
            let z:double = 1
            let x = 0
            data shape = {
                Circle(radius:float),
                Rectangle(length:float, width:float)
            }
            """;
        Files.writeString(testFile, source);

        Program program = buildProgram(source);
        Path outDir = tempDir.resolve("dist");
        Typer.analyze(program, testFile, outDir);

        Path primary = testFile.resolveSibling("Sample.expresso.typings");
        Path secondary = outDir.resolve("Sample.expresso.typings");
        assertFalse(Files.exists(primary), "typings file must not be created beside source when --out set");
        assertTrue(Files.exists(secondary), "typings file must be created in the output directory");
        List<String> lines = Files.readAllLines(secondary);
        assertEquals("# Sample.expresso", lines.get(0));
        assertEquals("z: double", lines.get(1));
        assertEquals("x: ~", lines.get(2));
        assertTrue(lines.stream().anyMatch(line -> line.startsWith("shape:")), "should include data type line");
        assertTrue(lines.stream().anyMatch(line -> line.startsWith("Circle:")), "should include constructor line");
    }

    private Program buildProgram(String source) {
        ParseTree tree = Transpiler.parseToAst(source);
        return new AstBuilder().build(tree);
    }
}
