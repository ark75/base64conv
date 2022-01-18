package ru.pak;

public class Main {

    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя,”:-!? ";
    public static final String ERR_FILE_MESSAGE = "Проблема с файлом, начните сначала!";


    public static void main(String[] args) {
        // write your code here
        Back.prepareAlphabet(ALPHABET);

        while (true) {
            Front.choiceMode();
            if (Front.makeChoice(0,2) == 1) {
                Front.enterKey();
                Back.crypto(Front.enterPath("Введите путь к файлу для шифрования", "Введите путь для сохранения файла");
            }

        }
    }
}

