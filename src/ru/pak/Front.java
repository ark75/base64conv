package ru.pak;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.lang.String.format;

public class Front {
    private static int choice;
    private static Path inputPathFile;
    public static final String QUESTION = "Выберите режим";
    public static final String CHOICE = "Введите цифру (от %d до  %d ) и нажмите Enter, 0 для выхода";
    public static final String ERR_IN_MESSAGE = "Неверный ввод, введите снова!";


    public static void choiceMode() {
        System.out.println(QUESTION);
        System.out.println("1. Шифратор/Дешифратор");
        System.out.println("2. Криптоанализатор");
    }

    public static int makeChoice(int start, int end) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(format(CHOICE,start,end));

        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice >= start && choice <= end) {
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

    public static void enterKey() {
        System.out.println("Введите ключ");
        Back.setKey(makeChoice(0, 40));
    }

    public static Path enterPath(String messageIn, String messageOut) {
        sout
        Scanner scanner = new Scanner(System.in);
        String inputPath = "";
        while (true) {
            try {
                inputPath = scanner.nextLine();
                inputPathFile = Paths.get(inputPath);
                if (Files.exists(inputPathFile)) {
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


