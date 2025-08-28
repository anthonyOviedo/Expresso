package com.diezam04.expresso;

public class Transpiler{
    public static String run(String expressoFile) {
        try {
            System.out.println("Transpilacion completada.");
            return expressoFile ;
        } catch (Exception e) {
            System.out.println("Error Transpilacion No Completada." + e.getMessage());
            return null;
        }
    }
}