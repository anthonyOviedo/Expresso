package com.diezam04.expresso;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;


@Command(name = "expressor", mixinStandardHelpOptions = true,
         version = "expressor 1.0",
         description = "Custom CLI tool")
public class Expressor implements Runnable {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Expressor()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        CommandLine.usage(this, System.out);
    }
    
    public Integer writeFile(java.io.File source,String fileContent, String extension, java.io.File outDir) {  
        String fileName = source.getName().split("\\.")[0];
        if (!outDir.exists()) {
            outDir.mkdirs(); 
        }
        java.io.File outFile = new java.io.File(outDir, fileName + "." + extension); 
        try (FileWriter writer = new FileWriter(fileName + "." + extension)) {
            writer.write(fileContent);
            System.out.println("File written successfully");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            return 1;
        }
        return 0;
    }

    public String loadFile(java.io.File source){
        try {
            return Files.readString(source.toPath());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    @Command(name = "transpile", description = "Transpile a source file from expresso to java")
    public Integer transpile(
        @Parameters(index = "0", paramLabel = "SOURCE", description = "The source file to transpile")
        java.io.File source,
        @Option(names = {"--out"}, description = "Directorio de salida", defaultValue = ".")
        java.io.File outDir
    ) {
    return writeFile(source, Transpiler.run(loadFile(source)), "java", outDir);
   }


    @Command(name = "build", description = "Build a source file, it generates a .class")
    public Integer build(
        @Parameters(index = "0", paramLabel = "SOURCE", description = "The source file to build")
        java.io.File source,
        @Option(names = {"--out"}, description = "Directorio de salida", defaultValue = ".")
        java.io.File outDir
    ) {
    return writeFile(source, Builder.run(Transpiler.run(loadFile(source))), "class", outDir);
    }


    @Command(name = "run", description = "Run a source file, executes .class files")
    public Integer run(@Parameters(index = "0", paramLabel = "SOURCE",
                description = "The source file to build")
    java.io.File source) {
        try {
            Runner.run(Builder.run(Transpiler.run(loadFile(source))));
        } catch (Exception e) {
            System.out.println("Error en la ejecucion." + e.getMessage());
            return 1;
        }
        return  0;
    }
}
