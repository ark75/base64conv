package ru.pak;

import java.nio.file.Path;


public class Main {

    /**
     * Реализация шифратора, дешифратора и криптоанализатора для шифра Цезаря
     * Разделил классы на FRONT и Back так как привык что методы ввода и обработки в разных классах, для такой задачи можно было бы и не делать
     * Методы:
     * FRONT : выбор пользователя в меню makeChoice , и ввод пути enterPath, если их не сделать будет куча повторов в коде
     * Back: setKey и getKey сделал, чтобы знали, что я понимаю, что такое геттеры и сеттеры, getKey заоодно уменьшает ключ на n длин криптоалфавита,
     * так как мы все равно вращаем текст относительное его длины,  да и просто подумал, что голый геттер некрасиво
     * encrypt - собственно метод шифрования, считывание попробовал разными методами типа InputStreamReader с буфером и без. После экспериментов
     * код считывания стринга самый короткий и простой для понимания, еще и очень быстрый. Проверил на больших объемах, работает корректно, в частности и на книге
     * которую скинули в Slack.
     * prepareAlphabet - не знаю зачем сделал, наверное, чтобы уменьшить количество кода. конвертирует строку алфавита в массив...
     * decrypt - расшифровка... Решил поиграться с параметрическим полиморфизмом, принимая два пути он пишет на диск, принимая один путь возвращает строку
     * Второй вариант нужен для отправки методу, отвечающего за оценку качества текста
     * qualityOfText - сейчас использует два критерия, вхождения ". " а также наличие слов с длииной более 30 букв. Игрался с конкретным текстом, вроде работает, критерии можно добавлять и
     * перемножать результаты
     * bruteforce - все просто дешифруем , проверяем качество, если удовлетворяет, пишем
     * getStatistic - утильный метод посчитать статистику в строке
     * statAnalysis - получает источник,  файл для статистики, использует getStatistic. При сравнении значений за основу взял тезу, что нам нужно найти близкие
     * по частоте символы, из анализа файла увидел, что многие символы имеют очент схожую статистику, что делает некорректным применением статистического анализатора
     * То есть если одна и вторая буква имеют одинаковую частоту, то в результате получается полная каша, поэтому можно незаморачиваться, находить близкий (в пределах 10%
     * первый подходящий символ и заполнять таблицу соответствия. Дальше формируем выходную строку на основе таблицы соответствия.
     *  В результате получилось что-то более-менее читаемое, ну сравнимое с тем, что получалось на занятии. Можно конечно, еще подумать, в целом тема бесконечная
     *  Алфавит можно использовать любой, полный, включая другие языки, но при этом шифрование, дешифрование идеально, стат. метод совсем плохо, так как увеличивается количество
     *  одинаковых по частоте символов.
     *
     * @autor Аркадий Пак
     * @version 0.1
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
                    } break;
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

