public class Transpilador{
    public static String run(File expressoFile) {
        Function<String, String> toClassName =
                file -> file.replace(".expresso", ".java");
        try {
            System.out.println("Hello, world from Java " + System.getProperty("java.version") + "!");
            return "Transpilacion completada.";
        } catch (Exception e) {
            return "Error Transpilacion No Completada." + e.getMessage();
        }
    }
}