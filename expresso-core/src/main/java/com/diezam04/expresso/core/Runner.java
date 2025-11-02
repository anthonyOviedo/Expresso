package com.diezam04.expresso.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Runner {
public static String run(String classFilePath) {
    try {
        File f = new File(classFilePath);
        if (!f.exists()) {
            String msg = "Compiled class file not found: " + classFilePath;
            System.err.println(msg);
            return msg;
        }

        String className = f.getName().replace(".class", "");
        String cp = f.getParent();

        Process process = new ProcessBuilder("java", "-cp", cp, className)
                .redirectErrorStream(true)
                .start();

        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
        }

        int exitCode = process.waitFor();
        String result = output.toString().stripTrailing();

        if (!result.isEmpty()) {
            System.out.println(result);
        }

        if (exitCode != 0 && result.isEmpty()) {
            String msg = "Execution finished with exit code " + exitCode;
            System.err.println(msg);
            return msg;
        }

        return result;
    } catch (Exception e) {
        String msg = "Execution error: " + e.getMessage();
        System.err.println(msg);
        return msg;
    }
}

}
