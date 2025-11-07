// ----------------------------------------------------------------------------------------------------
//          Authors: 
//          Antony Oviedo Alfaro ID: 207640246
//          Esteban Francisco Sánchez Sánchez  ID: 402640418
// ----------------------------------------------------------------------------------------------------
package com.diezam04.expresso.adapters.cli;

import java.io.File;
import java.util.Optional;
import java.util.function.Function;

import com.diezam04.expresso.core.Builder;
import com.diezam04.expresso.core.Runner;
import com.diezam04.expresso.core.Transpiler;
import com.diezam04.expresso.core.Utils;
import com.diezam04.expresso.core.transpiler.AstBuilder;
import com.diezam04.expresso.core.transpiler.Visitor;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Program;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "expressor", mixinStandardHelpOptions = true,
version = "expressor 3.2",
description = "Custom CLI tool")
public class Cli implements Runnable {

public static void main(String[] args) {
    System.exit(new CommandLine(new Cli()).execute(args));
}

@Override
public void run() {
    CommandLine.usage(this, System.out);
}

private Integer processFile(File source, String outDir, String extension,
                            Function<String, String> transformer,
                            Runnable postProcess) {
    Utils.log("Processing: " + source.getName());
    String finalOut = Optional.ofNullable(outDir).orElse(".");

    return Optional.ofNullable(Utils.loadFile(source))
        .map(transformer)
        .map(content -> Utils.writeFile(source, content, extension, finalOut))
        .map(status -> {
            if (status == 0 && postProcess != null) postProcess.run();
            return status;
        })
        .orElseGet(() -> {
            Utils.log("File is empty");
            return 1;
        });
}

@Command(name = "transpile", description = "Transpile a source file from expressor to java")
public Integer transpile(@Parameters(index = "0", paramLabel = "SOURCE") File source,
                         @Option(names = "--out") String outDir,
                         @Option(names = "--verbose") boolean verbose) {
    Utils.setVerbose(verbose);
    return processFile(source, outDir, "java", content -> {
        Program program = new AstBuilder().build(Transpiler.parseToAst(content));
        String baseName = source.getName().replaceFirst("\\.[^.]+$", "");
        return Visitor.generate(program, baseName);
    }, null);
}

@Command(name = "build", description = "Build a source file, it generates a .class")
public Integer build(@Parameters(index = "0", paramLabel = "SOURCE") File source,
                     @Option(names = "--out") String outDir,
                     @Option(names = "--verbose") boolean verbose) {
    Utils.setVerbose(verbose);
    String baseName = source.getName().replaceFirst("\\.[^.]+$", "");
    String className = Character.toUpperCase(baseName.charAt(0)) + baseName.substring(1);
    // transformer: from expresso source -> java source -> Builder.run(javaSource) returns path to .class
    return processFile(source, outDir, "class", fileContent -> Builder.run(Transpiler.run(fileContent, className)), null);
}

@Command(name = "run", description = "Run a source file, executes .class files")
public Integer run(@Parameters(index = "0", paramLabel = "SOURCE") File source,
                   @Option(names = "--out") String outDir,
                   @Option(names = "--verbose") boolean verbose) {
    Utils.setVerbose(verbose);
    String baseName = source.getName().replaceFirst("\\.[^.]+$", "");
    String className = Character.toUpperCase(baseName.charAt(0)) + baseName.substring(1);
    return processFile(source, outDir, "class", fileContent -> Builder.run(Transpiler.run(fileContent, className)), () -> {
        try {
            String baseNameInner = source.getName().replaceFirst("\\.[^.]+$", "");
            File outFile = new File(Optional.ofNullable(outDir).orElse("."), baseNameInner + ".class");
            Runner.run(outFile.getAbsolutePath());
            Utils.log("Execution completed successfully");
        } catch (Exception e) {
            System.err.println("Execution error: " + e.getMessage());
        }
    });
}


}
