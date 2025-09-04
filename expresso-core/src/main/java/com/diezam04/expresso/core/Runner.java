package com.diezam04.expresso.core;

import java.io.*;

public class Runner {

    public static String run(String classFilePath) {
        try {
            File f = new File(classFilePath);
            String className = f.getName().replace(".class", "");
            String cp = f.getParent();

            Process p = new ProcessBuilder("java", "-cp", cp, className)
                    .redirectErrorStream(true) 
                    .start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }

            p.waitFor();
            return output.toString().trim(); 
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
