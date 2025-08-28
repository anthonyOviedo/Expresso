package com.diezam04.expresso;

public class Builder {
    public static String run(String javaFileString) {
        try {
            System.out.println("Build completo");
            return javaFileString ;
        } catch (Exception e) {
            System.out.println("Error en el Build." + e.getMessage());
            return null;
        }
    }
}
