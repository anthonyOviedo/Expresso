package com.diezam04.expresso;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "expressor", mixinStandardHelpOptions = true,
         version = "expressor 1.0",
         description = "Custom CLI tool")
public class Expressor implements Runnable {

    @Option(names = "--verbose", description = "Show detailed execution steps")
    private boolean verbose;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Expressor()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        CommandLine.usage(this, System.out);
    }

    private void log(String message) {
        if (verbose) {
            System.out.println(message);
        }
    }

    public Integer writeFile(java.io.File source, String fileContent, String extension) {
        String fileName = source.getName().split("\\.")[0];
        try (FileWriter writer = new FileWriter(fileName + "." + extension)) {
            writer.write(fileContent);
            log("Archivo " + fileName + "." + extension + " generado correctamente");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            return 1;
        }
        return 0;
    }

    public String loadFile(java.io.File source) {
        try {
            log("Leyendo archivo: " + source.getName());
            return Files.readString(source.toPath());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    @Command(name = "transpile", description = "Transpile a source file from expressor to java")
    public Integer transpile(@Parameters(index = "0", paramLabel = "SOURCE",
                description = "The source file to transpile")
    java.io.File source) {
        log("Iniciando transpile...");
        String content = loadFile(source);
        if (content == null) return 1;
        log("Transpilando archivo...");
        return writeFile(source, Transpiler.run(content), "java");
    }

    @Command(name = "build", description = "Build a source file, it generates a .class")
    public Integer build(@Parameters(index = "0", paramLabel = "SOURCE",
                description = "The source file to build")
    java.io.File source) {
        log("Iniciando build...");
        String content = loadFile(source);
        if (content == null) return 1;
        log("Transpilando archivo...");
        String javaCode = Transpiler.run(content);
        log("Compilando archivo...");
        return writeFile(source, Builder.run(javaCode), "class");
    }

    @Command(name = "run", description = "Run a source file, executes .class files")
    public Integer run(@Parameters(index = "0", paramLabel = "SOURCE",
                description = "The source file to build")
    java.io.File source) {
        log("Iniciando run...");
        try {
            String content = loadFile(source);
            if (content == null) return 1;
            log("Transpilando archivo...");
            String javaCode = Transpiler.run(content);
            log("Compilando archivo...");
            String bytecode = Builder.run(javaCode);
            log("Ejecutando archivo...");
            Runner.run(bytecode);
        } catch (Exception e) {
            System.out.println("Error en la ejecucion. " + e.getMessage());
            return 1;
        }
        log("Ejecución finalizada correctamente");
        return 0;
    }
}
