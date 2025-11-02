package com.diezam04.expresso.core;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class BuilderTest {

    @TempDir
    Path tempDir;

    @Test
    void buildProducesValidClassFileAfterTranspile() throws IOException {
        // Step 1: read example Expresso program.
        Path example = Path.of("..", "examples", "HelloWorld0.expresso").toAbsolutePath().normalize();
        assertTrue(Files.exists(example), "Example file must exist");
        String source = Files.readString(example);
        assertFalse(source.isBlank(), "Example input must not be blank");

        // Step 2: transpile and capture Java output.
        String javaSource = Transpiler.run(source, "HelloWorld0");
        assertFalse(javaSource.isBlank(), "Transpiler should emit Java code");

        // Step 3: write Java file for traceability.
        Path javaFile = tempDir.resolve("HelloWorld0.java");
        Files.writeString(javaFile, javaSource);
        assertTrue(Files.exists(javaFile), "Java source file should exist on disk");

        // Step 4: build -> expect non-empty path to a .class file.
        String classFilePath = Builder.run(javaSource);
        assertNotNull(classFilePath, "Builder should return a class file path");
        assertFalse(classFilePath.isBlank(), "Builder should not return an empty class path");

        Path classFile = Path.of(classFilePath);
        assertTrue(Files.exists(classFile), "Compiled .class file must exist");
        assertTrue(classFile.getFileName().toString().endsWith(".class"), "Output must have .class extension");

        // Step 5: verify class file header -> first bytes should match CAFEBABE magic.
        try (InputStream in = Files.newInputStream(classFile)) {
            byte[] magic = in.readNBytes(4);
            assertEquals(4, magic.length, "Class file header must contain four bytes");
            assertArrayEquals(new byte[] {(byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE}, magic,
                "Class file must start with CAFEBABE");
        }
    }
}
