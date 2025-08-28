package com.diezam04.expresso;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


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
        // If no subcommand is given, show usage
        CommandLine.usage(this, System.out);
    }
    
    public void writeFile(File source,String file, String extension) {
        // Crear un nuevo archivo con la extensión indicada
        String fileName = source.getName();
        if (!fileName.endsWith("." + extension)) {
            fileName = fileName + "." + extension;
        }

        File newFile = new File(source.getParent(), fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            // Aquí puedes poner el contenido que quieras escribir
            writer.write("Este es un archivo generado con extensión: " + extension);
            writer.newLine();
            writer.write("Ruta absoluta: " + newFile.getAbsolutePath());
            writer.newLine();
            writer.write("¡Archivo escrito correctamente!");
            System.out.println("Archivo creado: " + newFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Command(name = "transpile", description = "Transpile a source file")
    public void transpile(@Parameters(index = "0", paramLabel = "SOURCE",
                description = "The source file to transpile")
    java.io.File source,
    @Option(names = {"-o","--out"}) java.io.File out) {
        if (!source.exists()) 
                System.err.println("File not found: " + source);
        else{
            String code = source.toString();
            System.out.println("✨ Transpiling file: " + source.getName());
            writeFile(source,Transpilador.run(code),".java");
        }

    }
}
