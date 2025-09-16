package com.diezam04.expresso.core;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Tester {

    public static boolean test(String classFilePath) {
        if (classFilePath == null || classFilePath.isBlank()) {
            Utils.log("Tester: classFilePath inválido", "ERROR");
            return false;
        }

        File f = new File(classFilePath);
        if (!f.exists() || !f.isFile()) {
            Utils.log("Tester: .class no encontrado: " + classFilePath, "ERROR");
            return false;
        }
        if (!classFilePath.endsWith(".class")) {
            Utils.log("Tester: extensión inválida: " + f.getName(), "ERROR");
            return false;
        }
        if (f.length() == 0) {
            Utils.log("Tester: .class vacío: " + f.getName(), "ERROR");
            return false;
        }

        try (URLClassLoader loader = new URLClassLoader(new URL[]{f.getParentFile().toURI().toURL()})) {
            String className = f.getName().replace(".class", "");
            loader.loadClass(className);
            Utils.log("Tester: .class cargado OK: " + f.getName());
            return true;
        } catch (Throwable t) {
            Utils.log("Tester: fallo al cargar .class: " + t.getMessage(), "ERROR");
            return false;
        }
    }
}
