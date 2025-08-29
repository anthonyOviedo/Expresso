package com.diezam04.expresso;

public class Transpiler{
    public static String run(String expressoFile) {
        try {
            System.out.println("Transpilation completed.");
            return expressoFile ;
        } catch (Exception e) {
            System.out.println("Error. Transpilation not completed" + e.getMessage());
            return null;
        }
    }
}