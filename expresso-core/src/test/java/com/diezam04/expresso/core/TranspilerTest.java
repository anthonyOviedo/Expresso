package com.diezam04.expresso.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class TranspilerTest {

    @TempDir
    Path tempDir;

    @Test
    void transpileCreatesJavaFileFromExampleSource() throws IOException {
        // Step 1: load the canonical example program.
        Path example = Path.of("..", "examples", "HelloWorld0.expresso").toAbsolutePath().normalize();
        assertTrue(Files.exists(example), "Example file must exist");
        String source = Files.readString(example);
        assertFalse(source.isBlank(), "Example input must not be blank");

        // Step 2: transpile -> Java source should be generated and contain the expected class.
        String javaSource = Transpiler.run(source, "HelloWorld0");
        assertFalse(javaSource.isBlank(), "Transpiler should emit Java code");
        assertTrue(javaSource.contains("public class HelloWorld0"),
            () -> "Generated Java must declare class HelloWorld0\n" + javaSource);

        // Step 3: persist result -> file must exist and match the generated content.
        Path javaFile = tempDir.resolve("HelloWorld0.java");
        Files.writeString(javaFile, javaSource);
        assertTrue(Files.exists(javaFile), "Transpiled Java file should exist");
        assertEquals(javaSource, Files.readString(javaFile), "Written Java file must match generated content");
    }
}
