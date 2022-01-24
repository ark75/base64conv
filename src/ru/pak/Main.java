package ru.pak;

import java.nio.file.Path;


public class Main {

    /**

     * По замечаниям
     * дописан метод mostFreqChar, вычисляющий самый часто встречающийся в статистике символ. переписан statAnalysis
     * теперь В статистиках файлов находятся самые встречающиеся и пишутся в таблицу соответствия.
     * Убраны лишние выводы в консоль
     * Исправлены наименования переменныых inputFile1 на encryptedFile, statistic1 на statsOfDecryptedFile и т.д.
     *
     * @autor Аркадий Пак
     * @version 0.2
     */


    //  public static final String ALPHABET = "123456789абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\n,.”:-!? ";
    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя\n,.”:-!? ";
    private static final String ok = "Выполнено";
    private static final String INPUT_KEY = "Введите ключ";
    private static final String INPUT_PATH_TO_DEC = "Введите путь к файлу для расшифровки";
    private static final String INPUT_PATH_TO_SAVE_DEC = "Введите путь куда сохранить расшифрованный файл";

    public static void main(String[] args) {
        Path source;
        Path target;
        Back.prepareAlphabet(ALPHABET);

        while (true) {
            System.out.println("1. Шифратор/Дешифратор");
            System.out.println("2. Криптоанализатор");
            switch (Front.makeChoice(0, 2)) {
                case 0:
                    System.exit(0);
                case 1:
                    System.out.println("1.Шифратор");
                    System.out.println("2.Дешифратор");
                    switch (Front.makeChoice(0, 2)) {
                        case 0:
                            System.exit(0);
                        case 1:
                            System.out.println(INPUT_KEY);
                            Back.setKey(Front.makeChoice(0, Integer.MAX_VALUE));
                            System.out.println("Введите путь к файлу для зашифровки");
                            source = Front.enterPath();
                            System.out.println("Введите путь куда сохранить зашифрованный файл");
                            target = Front.enterPath();
                            Back.encrypt(source, target);
                            System.out.println(ok);
                            break;
                        case 2:
                            System.out.println(INPUT_KEY);
                            Back.setKey(Front.makeChoice(0, Integer.MAX_VALUE));
                            System.out.println(INPUT_PATH_TO_DEC);
                            source = Front.enterPath();
                            System.out.println(INPUT_PATH_TO_SAVE_DEC);
                            target = Front.enterPath();
                            Back.decrypt(source, target);
                            System.out.println(ok);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("1.Метод перебора (брутфорс)");
                    System.out.println("2.Статистический метод");
                    switch (Front.makeChoice(0, 2)) {
                        case 0:
                            System.exit(0);
                        case 1:
                            System.out.println(INPUT_PATH_TO_DEC);
                            source = Front.enterPath();
                            System.out.println(INPUT_PATH_TO_SAVE_DEC);
                            target = Front.enterPath();
                            Back.bruteforce(source, target);
                            System.out.println(ok);
                            break;
                        case 2:
                            System.out.println(INPUT_PATH_TO_DEC);
                            source = Front.enterPath();
                            System.out.println("Введите путь к файлу для расчета статистики");
                            target = Front.enterPath();
                            System.out.println(INPUT_PATH_TO_SAVE_DEC);
                            Path dest = Front.enterPath();
                            Back.statAnalysis(source, target, dest);
                            System.out.println(ok);
                            break;
                    }
            }
        }
    }
}

