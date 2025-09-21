package com.diezam04.expresso.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RunnerTest {

    @Test
    void runReturnsProcessOutputForValidClassFile() {
        // Define una clase Java mínima que imprime un mensaje conocido.
        String javaSource = "public class Hello {public static void main(String[] args) {System.out.print(\"Hola Expresso\");}}";

        // Usa el Builder para compilar la clase y obtener la ruta del .class generado.
        String classFilePath = Builder.run(javaSource);
        assertNotNull(classFilePath, "Builder.run should return compiled class path");
        assertFalse(classFilePath.isBlank(), "Builder.run should return compiled class path");

        // Ejecuta el .class con Runner y comprueba que la salida corresponde a lo esperado.
        String output = Runner.run(classFilePath);
        assertEquals("Hola Expresso", output);
    }

    @Test
    void runWithNullPathReturnsErrorMessage() {
        // Ejecutar Runner con una ruta inválida debe regresar un mensaje de error.
        String result = Runner.run(null);
        assertTrue(result.startsWith("Error:"));
    }
}


// assertNotNull → verifies the Builder produced a result.
// assertFalse(...isBlank()) → ensures the result isn’t empty/meaningless.
// assertEquals → validates the expected output of the compiled program.
// assertTrue(...startsWith("Error:")) → checks error handling for invalid input.