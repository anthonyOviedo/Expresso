package com.diezam04.expresso.adapters.rest;

import com.diezam04.expresso.core.Builder;
import com.diezam04.expresso.core.Runner;
import com.diezam04.expresso.core.Transpiler;
import com.diezam04.expresso.core.Utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication(scanBasePackages = "com.diezam04.expresso")
@RestController
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestApi {

    private static final String DEFAULT_CLASS_NAME = "Main";
    private static final ObjectMapper JSON = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(RestApi.class, args);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "ok");
        return ResponseEntity.ok(body);
    }

    @PostMapping(value = "/transpile", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Map<String, Object>> transpile(
        @RequestBody(required = false) String requestBody,
        @org.springframework.web.bind.annotation.RequestHeader(value = HttpHeaders.CONTENT_TYPE, required = false) String contentType
    ) {
        Map<String, String> payload = parsePayload(contentType, requestBody);
        String source = extractSource(payload);
        if (source == null) {
            return badRequest("Source is empty");
        }

        String className = sanitizeClassName(payload.get("className"));
        Utils.log("Transpiling source for class " + className + "...");

        String javaSource;
        try {
            javaSource = Transpiler.run(source, className);
        } catch (Exception ex) {
            return serverError("Transpilation failed: " + ex.getMessage());
        }

        if (javaSource == null || javaSource.isBlank()) {
            return badRequest("Transpiler returned empty output");
        }

        Map<String, Object> response = success("Transpilation succeeded");
        response.put("javaSource", javaSource);
        response.put("className", className);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/build", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Map<String, Object>> build(
        @RequestBody(required = false) String requestBody,
        @org.springframework.web.bind.annotation.RequestHeader(value = HttpHeaders.CONTENT_TYPE, required = false) String contentType
    ) {
        Map<String, String> payload = parsePayload(contentType, requestBody);
        String source = extractSource(payload);
        if (source == null) {
            return badRequest("Source is empty");
        }

        String className = sanitizeClassName(payload.get("className"));
        Utils.log("Building class " + className + "...");

        String javaSource;
        try {
            javaSource = Transpiler.run(source, className);
        } catch (Exception ex) {
            return serverError("Transpilation failed: " + ex.getMessage());
        }

        if (javaSource == null || javaSource.isBlank()) {
            return badRequest("Transpiler returned empty output");
        }

        String classFilePath;
        try {
            classFilePath = Builder.run(javaSource);
        } catch (Exception ex) {
            return serverError("Build failed: " + ex.getMessage());
        }

        if (classFilePath == null || classFilePath.isBlank()) {
            return serverError("Builder did not produce a class file");
        }

        try {
            byte[] bytes = Files.readAllBytes(Path.of(classFilePath));
            String base64 = Base64.getEncoder().encodeToString(bytes);
            Map<String, Object> response = success("Build succeeded");
            response.put("fileName", className + ".class");
            response.put("classBytesBase64", base64);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return serverError("Error reading generated class: " + ex.getMessage());
        }
    }

    @PostMapping(value = "/run", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Map<String, Object>> run(
        @RequestBody(required = false) String requestBody,
        @org.springframework.web.bind.annotation.RequestHeader(value = HttpHeaders.CONTENT_TYPE, required = false) String contentType
    ) {
        Map<String, String> payload = parsePayload(contentType, requestBody);
        String source = extractSource(payload);
        if (source == null) {
            return badRequest("Source is empty");
        }

        String className = sanitizeClassName(payload.get("className"));
        Utils.log("Running class " + className + "...");

        String javaSource;
        try {
            javaSource = Transpiler.run(source, className);
        } catch (Exception ex) {
            return serverError("Transpilation failed: " + ex.getMessage());
        }

        if (javaSource == null || javaSource.isBlank()) {
            return badRequest("Transpiler returned empty output");
        }

        String classFilePath;
        try {
            classFilePath = Builder.run(javaSource);
        } catch (Exception ex) {
            return serverError("Build failed: " + ex.getMessage());
        }

        if (classFilePath == null || classFilePath.isBlank()) {
            return serverError("Builder did not produce a class file");
        }

        String output;
        try {
            output = Runner.run(classFilePath);
        } catch (Exception ex) {
            return serverError("Execution error: " + ex.getMessage());
        }

        if (output == null) {
            return serverError("Runner returned no output");
        }

        Map<String, Object> response = success("Execution succeeded");
        response.put("output", output);
        return ResponseEntity.ok(response);
    }

    private String extractSource(Map<String, String> payload) {
        if (payload == null) {
            return null;
        }
        String source = payload.get("source");
        if (source == null) {
            return null;
        }
        String trimmed = source.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    private String sanitizeClassName(String raw) {
        if (raw == null) {
            return DEFAULT_CLASS_NAME;
        }
        String trimmed = raw.trim();
        if (trimmed.isEmpty()) {
            return DEFAULT_CLASS_NAME;
        }
        String normalized = trimmed.replaceAll("[^a-zA-Z0-9_$]", "");
        if (normalized.isEmpty()) {
            return DEFAULT_CLASS_NAME;
        }
        char first = normalized.charAt(0);
        if (!Character.isJavaIdentifierStart(first)) {
            normalized = "_" + normalized;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(normalized.charAt(0)));
        for (int i = 1; i < normalized.length(); i++) {
            char c = normalized.charAt(i);
            if (!Character.isJavaIdentifierPart(c)) {
                sb.append('_');
            } else {
                sb.append(c);
            }
        }
        String result = sb.toString();
        if (!result.equals(trimmed)) {
            Utils.log("Class name sanitized from '" + trimmed + "' to '" + result + "'");
        }
        return result;
    }

    private Map<String, Object> success(String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("ok", true);
        body.put("message", message);
        return body;
    }

    private ResponseEntity<Map<String, Object>> badRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error(message));
    }

    private ResponseEntity<Map<String, Object>> serverError(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error(message));
    }

    private Map<String, Object> error(String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("ok", false);
        body.put("message", message);
        return body;
    }

    private Map<String, String> parsePayload(String contentType, String body) {
        if (body == null || body.isBlank()) {
            return Map.of();
        }

        if (contentType != null && contentType.toLowerCase().contains("application/json")) {
            try {
                Map<String, Object> raw = JSON.readValue(body, Map.class);
                Map<String, String> normalized = new LinkedHashMap<>();
                for (Map.Entry<String, Object> entry : raw.entrySet()) {
                    normalized.put(entry.getKey(), entry.getValue() == null ? null : Objects.toString(entry.getValue()));
                }
                return normalized;
            } catch (JsonProcessingException ex) {
                Utils.log("JSON parse error: " + ex.getMessage(), "ERROR");
                return Map.of("source", body);
            }
        }

        return Map.of("source", body);
    }
}
