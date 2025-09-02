package com.diezam04.expresso.core;

import java.io.File;

public class Runner {

    public static String run(String classFilePath) {
        try {
        File f = new File(classFilePath);
        String className = f.getName().replace(".class", "");
        String cp = f.getParent();
        Process p = new ProcessBuilder("java", "-cp", cp, className)
                .inheritIO()
                .start();
        p.waitFor();
        return "Execution completed.";
    } catch (Exception e) {
        return "Error: " + e.getMessage();
    }
    }
}