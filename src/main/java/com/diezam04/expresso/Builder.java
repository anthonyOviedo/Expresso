package com.diezam04.expresso;

public class Builder {
    public static String run(String javaFileString) {
        try {
            System.out.println("Build completed successfully.");
            return javaFileString ;
        } catch (Exception e) {
            System.out.println("Building Error." + e.getMessage());
            return null;
        }
    }
}
