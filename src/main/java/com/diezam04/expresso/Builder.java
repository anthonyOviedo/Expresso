package com.diezam04.expresso;

public class Builder {

    public static String run(String javaFileString) {
        try {
            log("Build completed successfully.");
            return javaFileString;
        } catch (Exception e) {
            log("Building Error. " + e.getMessage());
            return null;
        }
    }

    private static void log(String message) {
        System.out.println(message);
    }
}
