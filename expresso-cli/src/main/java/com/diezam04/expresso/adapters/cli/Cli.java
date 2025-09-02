package com.diezam04.expresso.adapters.cli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import com.diezam04.expresso.core.Transpiler;
import com.diezam04.expresso.core.Builder;
import com.diezam04.expresso.core.Runner;
import com.diezam04.expresso.core.Utils;


@Command(name = "expressor", mixinStandardHelpOptions = true,
         version = "expressor 1.1",
         description = "Custom CLI tool")
public class Cli implements Runnable {

    public static void main(String[] args) {
        System.exit(new CommandLine(new Cli()).execute(args));
    }

    @Override
    public void run() {
        CommandLine.usage(this, System.out);
    }

     @Command(name = "transpile", description = "Transpile a source file from expressor to java")
    public Integer transpile(@Parameters(index = "0", paramLabel = "SOURCE",
                                         description = "The source file to transpile") java.io.File source,
                             @Option(names = "--out", description = "Output directory (default: current directory)") String outDir,
                             @Option(names = "--verbose", description = "Show detailed execution steps") boolean verbose) {
        Utils.log("Starting transpile...");
        if (outDir == null) outDir = ".";
        String file = Utils.loadFile(source);
        if (file == null){
            Utils.log("File is empty");
            return 1;
        }
        return Utils.writeFile(source, Transpiler.run(file), "java", outDir);
    }


    @Command(name = "build", description = "Build a source file, it generates a .class")
    public Integer build(@Parameters(index = "0", paramLabel = "SOURCE",
                                     description = "The source file to build") java.io.File source,
                         @Option(names = "--out", description = "Output directory (default: current directory)") String outDir,
                         @Option(names = "--verbose", description = "Show detailed execution steps") boolean verbose) {
        Utils.log("Starting build...");
        if (outDir == null) outDir = ".";
        String file = Utils.loadFile(source);
        if (file == null){
            Utils.log("File is empty");
            return 1;
        }
        return Utils.writeFile(source, Builder.run(Transpiler.run(file)), "class", outDir);
    }


     @Command(name = "run", description = "Run a source file, executes .class files")
     public Integer run(@Parameters(index = "0", paramLabel = "SOURCE",
                                    description = "The source file to run") java.io.File source,
                        @Option(names = "--out", description = "Output directory (default: current directory)") String outDir,
                        @Option(names = "--verbose", description = "Show detailed execution steps") boolean verbose) {
         Utils.log("Starting run...");
         if (outDir == null) outDir = ".";
         try {
             String file = Utils.loadFile(source);
             if (file == null){
                 Utils.log("File is empty");
                 return 1;
             }    
             String content = Builder.run(Transpiler.run(Utils.loadFile(source)));
             int status = Utils.writeFile(source, content, "class", outDir);
             if (status == 0) {
                 String baseName = source.getName().replaceFirst("\\.[^.]+$", ""); // quita extensión original
                 File outFile = new File(outDir, baseName + ".class");
                 Runner.run(outFile.getAbsolutePath());
             }
         } catch (Exception e) {
             System.err.println("Execution error: " + e.getMessage());
             return 1;
         }
         Utils.log("Execution completed successfully");
         return 0;
     }
 }
