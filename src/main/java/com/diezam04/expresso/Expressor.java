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

    public static void main(String[] args) {
        System.exit(new CommandLine(new Expressor()).execute(args));
    }

    @Override
    public void run() {
        CommandLine.usage(this, System.out);
    }

    private void log(String message, boolean verbose) {
        if (verbose) System.out.println(message);
    }

    public Integer writeFile(java.io.File source, String content, String extension, boolean verbose) {
        try (FileWriter writer = new FileWriter(source.getName().split("\\.")[0] + "." + extension)) {
            writer.write(content);
            log("File " + source.getName().split("\\.")[0] + "." + extension + " created successfully", verbose);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            return 1;
        }
        return 0;
    }

    public String loadFile(java.io.File source, boolean verbose) {
        try {
            log("Reading file: " + source.getName(), verbose);
            return Files.readString(source.toPath());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    @Command(name = "transpile", description = "Transpile a source file from expressor to java")
    public Integer transpile(@Parameters(index = "0", paramLabel = "SOURCE",
                                         description = "The source file to transpile") java.io.File source,
                             @Option(names = "--verbose", description = "Show detailed execution steps") boolean verbose) {
        log("Starting transpile...", verbose);
        return writeFile(source, Transpiler.run(loadFile(source, verbose)), "java", verbose);
    }

    @Command(name = "build", description = "Build a source file, it generates a .class")
    public Integer build(@Parameters(index = "0", paramLabel = "SOURCE",
                                     description = "The source file to build") java.io.File source,
                         @Option(names = "--verbose", description = "Show detailed execution steps") boolean verbose) {
        log("Starting build...", verbose);
        return writeFile(source, Builder.run(Transpiler.run(loadFile(source, verbose))), "class", verbose);
    }


    @Command(name = "run", description = "Run a source file, executes .class files")
    public Integer run(@Parameters(index = "0", paramLabel = "SOURCE",
                                   description = "The source file to run") java.io.File source,
                       @Option(names = "--verbose", description = "Show detailed execution steps") boolean verbose) {
        log("Starting run...", verbose);
        try {
            Runner.run(Builder.run(Transpiler.run(loadFile(source, verbose))));
        } catch (Exception e) {
            System.err.println("Execution error: " + e.getMessage());
            return 1;
        }
        log("Execution completed successfully", verbose);
        return 0;
    }
}
