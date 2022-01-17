package ru.pak;

import java.util.Scanner;

public class Screens {
    public static int choice;
    public static final String QUESTION = "Выберите режим";
    public static final String CHOICE = "Введите цифру (1 или 2) и нажмите Enter, 0 для выходв";
    public static final String ERR_IN_MESSAGE = "Неверный ввод, введите снова!";

    public static void choiceMode() {
        System.out.println(QUESTION);
        System.out.println("1. Шифратор/Дешифратор");
        System.out.println("2. Криптоанализатор");
    }

    public static int makeChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(CHOICE);

       while (true) {
           choice = Integer.parseInt(scanner.nextLine());
           if (choice >= 0 && choice <= 2) {
               break;
           } else {
               System.out.println(ERR_IN_MESSAGE);
           }
       }
        return choice;
    }

}


