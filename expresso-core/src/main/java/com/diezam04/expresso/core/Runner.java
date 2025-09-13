package com.diezam04.expresso.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Runner {
public static String run(String classFilePath) {
    try {
        File f = new File(classFilePath);
        String className = f.getName().replace(".class", "");
        String cp = f.getParent();

        Process process = new ProcessBuilder("java", "-cp", cp, className)
                .redirectErrorStream(true)
                .start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            return reader.lines()
                    .reduce(new StringBuilder(),
                            (sb, line) -> sb.append(line).append(System.lineSeparator()),
                            StringBuilder::append)
                    .toString()
                    .trim();
        }
    } catch (Exception e) {
        return "Error: " + e.getMessage();
    }
}

}
