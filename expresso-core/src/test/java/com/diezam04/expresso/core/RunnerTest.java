package com.diezam04.expresso.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class RunnerTest {

    @Test
    void pipelineTranspileBuildRunProducesExpectedOutput() throws IOException {
        Path example = Path.of("..", "examples", "HelloWorld0.expresso").toAbsolutePath().normalize();
        assertTrue(Files.exists(example), "Example file must exist");
        String source = Files.readString(example);
        assertFalse(source.isBlank(), "Example input must not be blank");

        String javaSource = Transpiler.run(source, "HelloWorld0");
        assertFalse(javaSource.isBlank(), "Transpiler should emit Java code");

        String classFilePath = Builder.run(javaSource);
        assertFalse(classFilePath.isBlank(), "Builder must return a class file path");

        String output = Runner.run(classFilePath);
        assertTrue(output.contains("666"), "Output must include first print from HelloWorld0");
        assertTrue(output.contains("10"), "Output must include second print from HelloWorld0");
    }
}
