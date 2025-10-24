package br.edu.ifpr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import br.edu.ifpr.utils.FileManager;

public class App {
    private static final Path FILE_PATH = Paths.get("data", "notes.txt");

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== FILE MANAGER MENU ===");
            System.out.println("1) Criar/reescrever arquivo");
            System.out.println("2) Adicionar texto");
            System.out.println("3) Ler arquivo");
            System.out.println("4) Substituir texto");
            System.out.println("5) Leitura via Stream");
            System.out.println("0) Sair");
            System.out.print("Escolha uma opção: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> {
                    System.out.print("Digite o texto: ");
                    String text = scanner.nextLine();
                    FileManager.write(text + System.lineSeparator(), FILE_PATH);
                    System.out.println("Arquivo criado ou reescrito com sucesso!");
                }
                case "2" -> {
                    System.out.print("Texto para adicionar: ");
                    String text = scanner.nextLine();
                    FileManager.append(text + System.lineSeparator(), FILE_PATH);
                    System.out.println("Texto adicionado com sucesso!");

                }
                case "3" -> {
                    if (Files.exists(FILE_PATH)) {
                        FileManager.readAll(FILE_PATH).forEach(System.out::println);
                    } else {
                        System.out.println("O arquivo ainda não existe!");
                    }
                }
                case "4" -> {
                    System.out.print("Texto a ser substituído: ");
                    String oldText = scanner.nextLine();
                    System.out.print("Novo texto: ");
                    String newText = scanner.nextLine();
                    FileManager.replaceText(FILE_PATH, oldText, newText);
                    System.out.println("Texto substituído com sucesso!");
                }
                case "5" -> {
                    System.out.println("Leitura via Stream:");
                    FileManager.readStream(FILE_PATH);
                }
                case "0" -> {
                    System.out.println("Encerrando programa...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}