package com.diezam04.expresso.core;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Utils {
    static private Boolean verbose;

    private static Utils instance = null;

    static public void setVerbose(Boolean flag) {
        verbose = flag;
    }

    
    static public Boolean getVerbose() {
        return verbose;
    }

    static public void log(String message) {
        log(message, "INFO");
    }
    static public void log(String message, String state) {
        if (getVerbose()){
            message = " [" + state + "] " + message;
             System.out.println(message);
        }
    }

    static public Integer writeFile(java.io.File source, String content, String extension, String outDir) {
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

            log("File " + outFile.getAbsolutePath() + " created successfully");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            return 1;
        }
        return 0;
    }

    static public String loadFile(java.io.File source) {
        try {
            log("Reading file: " + source.getName());
            String content = Files.readString(source.toPath());
            if (!content.isBlank()) {
                log("File loaded: " + source.getName());
                return content;
            }
            log("file Empty " + source.getName(),"ERROR");

        } catch (IOException e) {
            System.err.println("Error reading file: "+ source.getName() + " ERORR: "+e.getMessage());
        }
        return null;
    }

}
