package br.edu.ifpr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import br.edu.ifpr.utils.FileManager;

public class App {
    private static final String DATA_DIR = "data";

    public static void main(String[] args) {
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

            try {
                switch (option) {
                    case "1" -> {
                        System.out.print("Digite o nome do arquivo: ");
                        String fileName = scanner.nextLine();
                        Path filePath = Paths.get(DATA_DIR, fileName);
                        System.out.print("Digite o texto: ");
                        String text = scanner.nextLine();
                        FileManager.write(text + System.lineSeparator(), filePath);
                        System.out.println("Arquivo criado ou reescrito com sucesso!");
                    }
                    case "2" -> {
                        System.out.print("Digite o nome do arquivo: ");
                        String fileName = scanner.nextLine();
                        Path filePath = Paths.get(DATA_DIR, fileName);
                        System.out.print("Texto para adicionar: ");
                        String text = scanner.nextLine();
                        FileManager.append(text + System.lineSeparator(), filePath);
                        System.out.println("Texto adicionado com sucesso!");
                    }
                    case "3" -> {
                        System.out.print("Digite o nome do arquivo: ");
                        String fileName = scanner.nextLine();
                        Path filePath = Paths.get(DATA_DIR, fileName);
                        if (Files.exists(filePath)) {
                            FileManager.readAll(filePath).forEach(System.out::println);
                        } else {
                            System.out.println("O arquivo ainda não existe!");
                        }
                    }
                    case "4" -> {
                        System.out.print("Digite o nome do arquivo: ");
                        String fileName = scanner.nextLine();
                        Path filePath = Paths.get(DATA_DIR, fileName);
                        if (!Files.exists(filePath)) {
                            System.out.println("O arquivo não existe!");
                            break;
                        }
                        System.out.print("Texto a ser substituído: ");
                        String oldText = scanner.nextLine();
                        System.out.print("Novo texto: ");
                        String newText = scanner.nextLine();
                        boolean success = FileManager.replaceText(filePath, oldText, newText);
                        if (success) {
                            System.out.println("Texto substituído com sucesso!");
                        } else {
                            System.out.println("Texto não encontrado! Verifique se a palavra existe e se o 'case' está correto.");
                        }
                    }
                    case "5" -> {
                        System.out.print("Digite o nome do arquivo: ");
                        String fileName = scanner.nextLine();
                        Path filePath = Paths.get(DATA_DIR, fileName);
                        if (!Files.exists(filePath)) {
                            System.out.println("O arquivo não existe!");
                            break;
                        }
                        System.out.println("Leitura via Stream:");
                        FileManager.readStream(filePath);
                    }
                    case "0" -> {
                        System.out.println("Encerrando programa...");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (IOException e) {
                System.out.println("Erro de I/O: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}