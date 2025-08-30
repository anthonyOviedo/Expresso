package com.diezam04.expresso;

public class Transpiler {

    public static String run(String expressoFile) {
        try {
            log("Transpilation completed.");
            return expressoFile;
        } catch (Exception e) {
            log("Error. Transpilation not completed " + e.getMessage());
            return null;
        }
    }

    private static void log(String message) {
        System.out.println(message);
    }
}
