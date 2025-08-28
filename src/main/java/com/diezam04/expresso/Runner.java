package com.diezam04.expresso;


import java.io.File;
import java.util.function.Function;

public class Runner {
    
    public static String run(File classFile) {
        Function<String, String> toClassName =
                file -> file.replace(".class", "");
        
        try {
            String className = toClassName.apply(classFile.getName());
            System.out.println("Ejecutando clase: " + className);
            
            ProcessBuilder processBuilder = new ProcessBuilder("java", className);
            processBuilder.inheritIO(); 
            Process process = processBuilder.start();
            process.waitFor();
            
            return "Ejecucion completada.";
        } catch (Exception e) {
            return "Error Ejecucion No Completada." + e.getMessage();
        }
    }
}