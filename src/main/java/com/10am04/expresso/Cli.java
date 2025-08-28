import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.Callable;



@Command(name = "expressor", mixinStandardHelpOptions = true,
         version = "expressor 1.0",
         description = "Custom CLI tool",
         subcommands = { Expressor.Transpile.class })
public class Expressor implements Runnable {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Expressor()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        // If no subcommand is given, show usage
        CommandLine.usage(this, System.out);
    }

    @Command(name = "transpile", description = "Transpile a source file")
    public void transpile() {
        @Parameters(index = "0", description = "The source file to transpile")
        private File source;

        if (!source.exists()) {
                System.err.println("File not found: " + source);
                return 1;
        }else{
            String code = Files.readString(source.toPath());
            System.out.println("✨ Transpiling file: " + source.getName());
            writeFile(Transpilador.run(source));
        }
        
    }
}
