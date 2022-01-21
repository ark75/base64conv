package ru.pak;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя,.”:-!? \n";
    private static int choice;

    public static void main(String[] args) {

        Back.prepareAlphabet(ALPHABET);

        //  while (true) {
        //   Front.choiceMode();
        //choice = (Front.makeChoice(0, 2));
        //if (choice == 1) {
        // Front.enterKey();
        //              Path inputFile = Front.enterPath();
        //Path outputFile = Front.enterPath();
        Path inputFile = Paths.get("d:\\test1.txt");
        Path outputFile = Paths.get("d:\\test2.txt");
        Path outputFile2 = Paths.get("d:\\test3.txt");
        Path outputFile3 = Paths.get("d:\\test4.txt");

        Back.encrypt(inputFile, outputFile);
        Back.decrypt(outputFile, outputFile2);
        Back.bruteforce(outputFile, outputFile3);
        Back.getStatistic(outputFile);
        Back.statAnalysis(inputFile, inputFile,outputFile)
        //}

        //}
    }
}

