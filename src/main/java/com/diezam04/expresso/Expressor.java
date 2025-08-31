package com.diezam04.expresso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "expressor", mixinStandardHelpOptions = true,
         version = "expressor 1.1",
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

    public Integer writeFile(java.io.File source, String content, String extension, String outDir, boolean verbose) {
        try {
            java.io.File outFolder = new java.io.File(outDir);
            if (!outFolder.exists()) {
                outFolder.mkdirs(); // crea la carpeta si no existe
            }
            String baseName = source.getName().split("\\.")[0];
            java.io.File outFile = new java.io.File(outFolder, baseName + "." + extension);

            try (FileWriter writer = new FileWriter(outFile)) {
                writer.write(content);
            }

            log("File " + outFile.getAbsolutePath() + " created successfully", verbose);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            return 1;
        }
        return 0;
    }

    public String loadFile(java.io.File source, boolean verbose) {
        try {
            log("Reading file: " + source.getName(), verbose);
            String content = Files.readString(source.toPath());
            if (!content.isBlank()) {
                return content;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: "+ source.getName() + " ERORR: "+e.getMessage());
        }
        return null;
    }


     @Command(name = "transpile", description = "Transpile a source file from expressor to java")
    public Integer transpile(@Parameters(index = "0", paramLabel = "SOURCE",
                                         description = "The source file to transpile") java.io.File source,
                             @Option(names = "--out", description = "Output directory (default: current directory)") String outDir,
                             @Option(names = "--verbose", description = "Show detailed execution steps") boolean verbose) {
        log("Starting transpile...", verbose);
        if (outDir == null) outDir = ".";
        String file = loadFile(source, verbose);
        if (file == null){
            log("File is empty",verbose);
            return 1;
        }
        return writeFile(source, Transpiler.run(file), "java", outDir, verbose);
    }


    @Command(name = "build", description = "Build a source file, it generates a .class")
    public Integer build(@Parameters(index = "0", paramLabel = "SOURCE",
                                     description = "The source file to build") java.io.File source,
                         @Option(names = "--out", description = "Output directory (default: current directory)") String outDir,
                         @Option(names = "--verbose", description = "Show detailed execution steps") boolean verbose) {
        log("Starting build...", verbose);
        if (outDir == null) outDir = ".";
        String file = loadFile(source, verbose);
        if (file == null){
            log("File is empty",verbose);
            return 1;
        }
        return writeFile(source, Builder.run(Transpiler.run(file)), "class", outDir, verbose);
    }


    @Command(name = "run", description = "Run a source file, executes .class files")
    public Integer run(@Parameters(index = "0", paramLabel = "SOURCE",
                                   description = "The source file to run") java.io.File source,
                       @Option(names = "--out", description = "Output directory (default: current directory)") String outDir,
                       @Option(names = "--verbose", description = "Show detailed execution steps") boolean verbose) {
        log("Starting run...", verbose);
        if (outDir == null) outDir = ".";
        try {
            String file = loadFile(source, verbose);
            if (file == null){
                log("File is empty",verbose);
                return 1;
            }    
            String content = Builder.run(Transpiler.run(loadFile(source, verbose)));
            int status = writeFile(source, content, "class", outDir, verbose);
            if (status == 0) {
                String baseName = source.getName().replaceFirst("\\.[^.]+$", ""); // quita extensión original
                File outFile = new File(outDir, baseName + ".class");
                Runner.run(outFile.getAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Execution error: " + e.getMessage());
            return 1;
        }
        log("Execution completed successfully", verbose);
        return 0;
    }
}
