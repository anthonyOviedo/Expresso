// ----------------------------------------------------------------------------------------------------
//          Authors: 
//          Antony Oviedo Alfaro ID: 207640246
//          Esteban Francisco Sánchez Sánchez  ID: 402640418
// ----------------------------------------------------------------------------------------------------

package com.diezam04.expresso.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
static private boolean verbose = true;


public static void setVerbose(Boolean flag) {
    verbose = Boolean.TRUE.equals(flag);
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
        String out = "[" + timestamp + "] [" + state + "] " + message;
        if ("ERROR".equalsIgnoreCase(state)) {
            System.err.println(out);
        } else {
            System.out.println(out);
        }
    }
}

static public Integer writeFile(java.io.File source, String content, String extension, String outDir) {
    String baseName = source.getName();
    int idx = baseName.lastIndexOf('.');
    if (idx > 0) baseName = baseName.substring(0, idx);
    log("writing file " + baseName, "INFO");
    java.io.File outFolder = new java.io.File(outDir);

    if (!outFolder.exists()) {
        log("Creating folder " + outDir);
        if (outFolder.mkdirs()) {
            log("Created successfully");
        }
    }

    java.io.File outFile = new java.io.File(outFolder, baseName + "." + extension);

    try {
        if ("class".equals(extension)) {
            Path generatedClassPath = Path.of(content);
            Files.copy(generatedClassPath, outFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            copyInnerClasses(generatedClassPath, outFolder.toPath());
            log("Binary file " + outFile.getAbsolutePath() + " created successfully");
        } else {
            try (FileWriter writer = new FileWriter(outFile)) {
                writer.write(content);
            }
            log("File " + outFile.getAbsolutePath() + " created successfully");
        }
        return 0;
    } catch (IOException e) {
        System.err.println("Error writing file: " + e.getMessage());
        return 1;
    }
}

static public String loadFile(java.io.File source) {
    try {
        log("Reading file: " + source.getName());
        String content = Files.readString(source.toPath());
        if (!content.isBlank()) {
            log("File loaded: " + source.getName());
            return content;
        } else {
            log("File is empty " + source.getName(), "ERROR");
        }

    } catch (IOException e) {
        System.err.println("Error reading file: " + source.getName() + " ERROR: " + e.getMessage());
    }
    return null;
}

static public java.util.Optional<String> extractClassName(String javaSource) {
    return java.util.Arrays.stream(javaSource.split("\\R"))
            .filter(line -> line.trim().startsWith("public class"))
            .map(line -> line.replace("public class", "").trim().split(" ")[0])
            .findFirst();
}

public static File tempFile(String content, String ext) {
    try {
        File tmp = File.createTempFile("expresso-", ext);
        tmp.deleteOnExit();
        try (FileWriter fw = new FileWriter(tmp)) { fw.write(content); }
        return tmp;
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

private static void copyInnerClasses(Path mainClassPath, Path targetDir) throws IOException {
    Path parent = mainClassPath.getParent();
    if (parent == null) {
        return;
    }
    String mainFileName = mainClassPath.getFileName().toString();
    if (!mainFileName.endsWith(".class")) {
        return;
    }
    String prefix = mainFileName.substring(0, mainFileName.length() - ".class".length());
    try (var stream = Files.newDirectoryStream(parent, prefix + "$*.class")) {
        for (Path companion : stream) {
            Path target = targetDir.resolve(companion.getFileName().toString());
            Files.copy(companion, target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            log("Binary file " + target.toAbsolutePath() + " created successfully");
        }
    }
}


}
