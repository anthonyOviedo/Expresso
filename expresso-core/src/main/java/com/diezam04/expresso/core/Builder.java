package com.diezam04.expresso.core;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

class Builder {

    public static String run(String javaFileString) {
        if (javaFileString == null || javaFileString.isBlank()) {
            Utils.log("Build received empty source", "ERROR");
            return "";
        }
        return compileJava(javaFileString);
    }

    private static String compileJava(String javaSource) {
        try {
            if (!javaSource.contains("class")) {
                Utils.log("Build source does not contain 'class' keyword", "ERROR");
                return "";
            }

            Path tempDir = Files.createTempDirectory("expresso_build");
            String rawName = Utils.extractClassName(javaSource).orElse("Main");
            String className = rawName.replaceAll("[^a-zA-Z0-9_$]", "");
            Path javaFile = tempDir.resolve(className + ".java");

            try (FileWriter writer = new FileWriter(javaFile.toFile())) {
                writer.write(javaSource);
            }

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            if (compiler == null) {
                Utils.log("JavaCompiler not available. Use a JDK instead of JRE.", "ERROR");
                return "";
            }

            int result = compiler.run(null, null, null, javaFile.toString());
            if (result != 0) {
                Utils.log("Compilation failed with exit code: " + result, "ERROR");
                return "";
            }

            Path classFile = tempDir.resolve(className + ".class");
            if (!Files.exists(classFile)) {
                Utils.log("Compilation did not generate .class file.", "ERROR");
                return "";
            }

            return classFile.toAbsolutePath().toString();
        } catch (IOException e) {
            Utils.log("Build error: " + e.getMessage(), "ERROR");
            return "";
        }
    }
}
