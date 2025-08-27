
import java.util.function.Function;
import java.util.function.Supplier;

public class Runner {

   
    public static String run(String expressoFile) {
        Function<String, String> toClassName =
                file -> file.replace(".expresso", "");

        Supplier<ProcessBuilder> processSupplier =
                () -> new ProcessBuilder("java", toClassName.apply(expressoFile))
                        .inheritIO();

        try {
            Process process = processSupplier.get().start();
            process.waitFor();
            return "Ejecución completada.";
        } catch (Exception e) {
            return "Error en ejecución: " + e.getMessage();
        }
    }
}