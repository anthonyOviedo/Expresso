package com.diezam04.expresso.core;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    static private Boolean verbose = true;

    public static void setVerbose(Boolean flag) {
        verbose = flag;
    }

    static public Boolean getVerbose() {
        return verbose;
    }

    static public void log(String message) {
        log(message, "INFO");
    }
    
    static public void log(String message, String state) {
        if (getVerbose()) {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.println("[" + timestamp + "] [" + state + "] " + message);
        }
    }

    static public Integer writeFile(java.io.File source, String content, String extension, String outDir) {
        String baseName = source.getName().split("\\.")[0];
        log("writing file " + baseName, "INFO");
        java.io.File outFolder = new java.io.File(outDir);

        if (!outFolder.exists()) {
            log("Creating folder " + outDir);
            if (outFolder.mkdirs()) {
                log("Created successfully");
            }
        }

        java.io.File outFile = new java.io.File(outFolder, baseName + "." + extension);

        try (FileWriter writer = new FileWriter(outFile)) {
            writer.write(content);
            log("File " + outFile.getAbsolutePath() + " created successfully");
            return 0;
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            return 1;
        }

    }

    static public String loadFile(java.io.File source) {

        try {
            String baseName = source.getName().split("\\.")[0];

            log("Reading file: " + source.getName());
            String content = Files.readString(source.toPath());
            if (!content.isBlank()) {
                log("File loaded: " + source.getName());
                return content;
            }else{  
                log("File is empty " + source.getName(),"ERROR");
            }

            
        } catch (IOException e) {
            System.err.println("Error reading file: "+ source.getName() + " ERORR: "+e.getMessage());
        }
        return null;
    }

}
