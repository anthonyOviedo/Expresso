// ----------------------------------------------------------------------------------------------------
//          Authors:
//          Antony Oviedo Alfaro ID: 207640246
//          Esteban Francisco Sánchez Sánchez  ID: 402640418
// ----------------------------------------------------------------------------------------------------

package com.diezam04.expresso.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.LetStatement;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Program;
import com.diezam04.expresso.core.transpiler.src.ast.Ast.Statement;

public final class Typer {

    private Typer() { throw new AssertionError("Utility class"); }

    public static void analyze(Program program, Path sourceFile, Path alternateDir) {
        if (program == null) {
            return;
        }
        TypingsCollector collector = new TypingsCollector(sourceFile, alternateDir);
        collector.collect(program);
    }

    public static final class TyperException extends RuntimeException {
        public TyperException(String message) {
            super(message);
        }
    }

    private static final class TypingsCollector {
        private final Path sourceFile;
        private final Path alternateDir;
        private final Map<String, String> lets = new LinkedHashMap<>();
        private final List<String> dataLines = new ArrayList<>();

        TypingsCollector(Path sourceFile, Path alternateDir) {
            this.sourceFile = sourceFile;
            this.alternateDir = alternateDir;
        }

        void collect(Program program) {
            for (Statement statement : program.statements()) {
                if (statement instanceof LetStatement letStatement) {
                    registerLet(letStatement);
                } else if (statement instanceof com.diezam04.expresso.core.transpiler.src.ast.Ast.DataDeclaration dataDeclaration) {
                    registerData(dataDeclaration);
                }
            }
            if (alternateDir != null) {
                writeTypingsFile();
            }
        }

        private void registerLet(LetStatement letStatement) {
            String name = letStatement.name();
            String typeLiteral = letStatement.declaredTypeLiteral();
            String recorded = (typeLiteral == null || typeLiteral.isBlank()) ? "~" : typeLiteral.trim();
            lets.put(name, recorded);
        }

        private void registerData(com.diezam04.expresso.core.transpiler.src.ast.Ast.DataDeclaration declaration) {
            String name = declaration.name();
            String constructors = declaration.constructors().stream()
                .map(com.diezam04.expresso.core.transpiler.src.ast.Ast.DataConstructor::name)
                .collect(Collectors.joining("|"));
            dataLines.add(name + ": " + constructors);
            for (com.diezam04.expresso.core.transpiler.src.ast.Ast.DataConstructor ctor : declaration.constructors()) {
                String fieldList;
                if (ctor.fields().isEmpty()) {
                    fieldList = "void";
                } else {
                    fieldList = ctor.fields().stream()
                        .map(field -> formatFieldType(field.typeLiteral()))
                        .collect(Collectors.joining(", "));
                }
                dataLines.add(ctor.name() + ": " + fieldList + " ^ " + name);
            }
        }

        private void writeTypingsFile() {
            if (sourceFile == null) {
                return;
            }
            Path absolute = sourceFile.toAbsolutePath();
            String fileName = absolute.getFileName().toString();
            List<String> lines = new ArrayList<>();
            lines.add("# " + fileName);
            if (lets.isEmpty() && dataLines.isEmpty()) {
                lines.add("(none)");
            } else {
                lets.forEach((name, type) -> lines.add(name + ": " + type));
                dataLines.forEach(lines::add);
            }
            Path altDirAbsolute = alternateDir.toAbsolutePath();
            Path target = altDirAbsolute.resolve(fileName + ".typings");
            writeLines(target, lines);
        }

        private String formatFieldType(String literal) {
            if (literal == null || literal.isBlank()) {
                return "~";
            }
            return literal.trim();
        }

        private void writeLines(Path target, List<String> lines) {
            try {
                Path parent = target.getParent();
                if (parent != null) {
                    Files.createDirectories(parent);
                }
                Files.write(target, lines);
            } catch (IOException ex) {
                throw new TyperException("Failed to write typings file: " + target);
            }
        }
    }
}
