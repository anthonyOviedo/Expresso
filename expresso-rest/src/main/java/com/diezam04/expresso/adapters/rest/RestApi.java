package com.diezam04.expresso.adapters.rest;


import com.diezam04.expresso.core.Transpiler;
import com.diezam04.expresso.core.Builder;
import com.diezam04.expresso.core.Runner;
import com.diezam04.expresso.core.Utils;

import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.file.Files;

import java.io.File;

@SpringBootApplication(scanBasePackages = "com.diezam04.expresso")
@RestController
@RequestMapping("/api/v1")
public class RestApi {

    public static void main(String[] args) {
        SpringApplication.run(RestApi.class, args);
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @PostMapping(value = "/transpile", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> transpile(@RequestBody String source) {
        Utils.log("Starting transpile...");
        if (source == null || source.isBlank()) {
            return ResponseEntity.badRequest().body("Source is empty");
        }
        System.out.println("Source received:\n" + source);
        String content = Transpiler.run(source);
        return ResponseEntity.status(content.equals("") ? 500 : 200).body(content);
    }

    @PostMapping(value = "/build", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<ByteArrayResource> build(@RequestBody String source) {
        try {
        String javaCode = Transpiler.run(source);
        String outDir = Files.createTempDirectory("expresso_build").toString();

        File javaFile = new File(outDir, "Main.java");
        Utils.writeFile(javaFile, javaCode, "java", outDir);
        
        File classFile = new File(outDir, "Main.class");
        Utils.writeFile(classFile, Builder.run(javaCode), "class", outDir);


        // 4. Return .class as download
        byte[] bytes = Files.readAllBytes(classFile.toPath());
        ByteArrayResource res = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Main.class")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .contentLength(bytes.length)
            .body(res);

    } catch (Exception e) {
        return ResponseEntity.status(500).body(new ByteArrayResource(
            ("Error: " + e.getMessage()).getBytes()
        ));
    }
}

    @PostMapping(value = "/run", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> run(@RequestBody String source) {
        Utils.log("Starting run...");
        if (source == null || source.isBlank()) {
            return ResponseEntity.badRequest().body("Source is empty");
        }
        try {
            String javaCode = Transpiler.run(source);
            String outDir = Files.createTempDirectory("expresso_build").toString();

            File javaFile = new File(outDir, "Main.java");
            Utils.writeFile(javaFile, javaCode, "java", outDir);
            
            File classFile = new File(outDir, "Main.class");
            Utils.writeFile(classFile, Builder.run(javaCode), "class", outDir);
  
            
            return ResponseEntity.ok(Runner.run(classFile.getAbsolutePath()));
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Execution error: " + e.getMessage());
        }
    }
}
