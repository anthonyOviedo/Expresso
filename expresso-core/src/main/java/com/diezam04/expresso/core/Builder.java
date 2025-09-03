package com.diezam04.expresso.core;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Builder {

    /**
     * Compila un archivo Java y genera el correspondiente .class.
     * @param javaFileString código fuente en Java
     * @return ruta absoluta del archivo .class generado o "" si falla
     */
    public static String run(String javaFileString) {
        if (javaFileString == null || javaFileString.isBlank()) {
            Utils.log("Build received empty source", "ERROR");
            return "";
        }
        return compileJava(javaFileString);
    }

    private static String compileJava(String javaSource) {
        try {
            // Crear directorio temporal
            Path tempDir = Files.createTempDirectory("expresso_build");

            // Nombre de clase pública principal
            String rawName = Utils.extractClassName(javaSource).orElse("Main");
            // Limpiar nombre para evitar caracteres inválidos
            String className = rawName.replaceAll("[^a-zA-Z0-9_$]", "");

            Path javaFile = tempDir.resolve(className + ".java");

            // Guardar el código fuente en el archivo .java
            try (FileWriter writer = new FileWriter(javaFile.toFile())) {
                writer.write(javaSource);
            }

            // Compilador de Java
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
