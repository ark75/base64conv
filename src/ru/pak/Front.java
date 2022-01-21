package ru.pak;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.lang.String.format;

public class Front {

    public static final String CHOICE = "Введите цифру (от %d до  %d ) и нажмите Enter, 0 для выхода";
    public static final String ERR_IN_MESSAGE = "Неверный ввод, введите снова!";
    public static final String MESSAGE_FILE_NOT_EXISTS = "Указанный файл/путь не существует. Будет создан новый файл.";

    public static int makeChoice(int start, int end) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf((CHOICE) + "%n", start, end);
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice == 0) {
                    System.exit(0);
                } else if (choice >= start && choice <= end) {
                    break;
                } else {
                    System.out.println(ERR_IN_MESSAGE);
                    scanner.next();
                }
            } catch (Exception e) {
                System.out.println(ERR_IN_MESSAGE);
                scanner.next();
            }
        }
        return choice;
    }

    public static Path enterPath() {

        Scanner scanner = new Scanner(System.in);
        String inputPath = "";
        Path inputPathFile;
        while (true) {
            try {
                inputPath = scanner.nextLine();
                inputPathFile = Paths.get(inputPath);
                if (Files.exists(inputPathFile)) {
                    break;
                } else if (Files.notExists(inputPathFile)) {
                    System.out.println(MESSAGE_FILE_NOT_EXISTS);
                    Files.createFile(inputPathFile);
                    break;
                } else {
                    System.out.println(ERR_IN_MESSAGE);
                    scanner.next();
                }
            } catch (Exception e) {
                System.out.println(ERR_IN_MESSAGE);
                scanner.next();
            }
        }
        return inputPathFile;
    }
}


