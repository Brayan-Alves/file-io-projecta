package br.edu.ifpr.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileManager {
    public static void write(String content, Path path) throws IOException {
        Files.createDirectories(path.getParent());
        Files.writeString(path, content, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void append(String content, Path path) throws IOException {
        Files.createDirectories(path.getParent());
        Files.writeString(path, content, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static List<String> readAll(Path path) throws IOException {
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    public static boolean replaceText(Path path, String target, String replacement) throws IOException {
        if (!Files.exists(path)) {
            return false;
        }
        
        Path temp = Files.createTempFile("temp-", ".txt");
        boolean textFound = false;
        
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(temp, StandardCharsets.UTF_8)) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(target)) {
                    textFound = true;
                    line = line.replace(target, replacement);
                }
                writer.write(line);
                writer.newLine();
            }
        }
        
        if (textFound) {
            Files.move(temp, path, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } else {
            Files.deleteIfExists(temp);
            return false;
        }
    }

    public static void readStream(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new IOException("Arquivo não encontrado: " + path);
        }
        
        try (var stream = Files.lines(path, StandardCharsets.UTF_8)) {
            stream.forEach(System.out::println);
        }
    }
}