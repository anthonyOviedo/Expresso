package com.diezam04.expresso;

import java.io.File;
import java.util.function.Function;

public class Runner {

    public static String run(File classFile) {
        Function<String, String> toClassName =
                file -> file.replace(".class", "");

        try {
            String className = toClassName.apply(classFile.getName());
            log("Executing class: " + className);
            ProcessBuilder processBuilder = new ProcessBuilder("java", className);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
            return "Execution completed.";
        } catch (Exception e) {
            return "Error. Execution not completed. " + e.getMessage();
        }
    }

    public static Integer run(String classFile) {
        log("Executing class: " + classFile);
        return 0;
    }

    private static void log(String message) {
        System.out.println(message);
    }
}
