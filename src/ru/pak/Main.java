package ru.pak;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя,”:-!? ";
    public static final String ERR_FILE_MESSAGE = "Проблема с файлом, начните сначала!";


    public static void main(String[] args) {
        // write your code here
        Screens.choiceMode();
        Screens.makeChoice();
    }
}

