package ru.pak;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Back {
    private static int key = 2;
    public static char[] cryptoAlphabet;
    public static int fileSize = 0;
    private static HashMap<Character, Double> Statistic = new HashMap<>();

    public static void setKey(int key) {
        Back.key = key;
    }

    public static int getKey() {
        return key;
    }

    public static void encrypt(Path pathIn, Path pathOut) {
        try {
            System.out.println();
            String in = Files.readString(pathIn).toLowerCase();
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                for (int j = 0; j < cryptoAlphabet.length; j++) {
                    if (in.charAt(i) == cryptoAlphabet[j]) {
                        int value = j + Back.getKey();
                        if (value >= cryptoAlphabet.length) {
                            value = value % (cryptoAlphabet.length - 1);
                        }
                        out.append(cryptoAlphabet[value]);


                    }
                }
            }
            Files.writeString(pathOut, out);
            System.out.println("Шифровниу");
            ;


        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void prepareAlphabet(String alphabet) {
        cryptoAlphabet = alphabet.toCharArray();
    }

    public static void decrypt(Path pathin, Path pathout) {
        try {
            System.out.println();
            String in = Files.readString(pathin);
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                for (int j = 0; j < cryptoAlphabet.length; j++) {
                    if (in.charAt(i) == cryptoAlphabet[j]) {
                        int value = j - Back.getKey();
                        if (value < 0) {
                            value = value + (cryptoAlphabet.length - 1);
                        }
                        out.append(cryptoAlphabet[value]);


                    }
                }
            }
            Files.writeString(pathout, out);
            System.out.println("Дешифрование");
            ;


        } catch (
            Exception e) {
            e.printStackTrace();

        }

    }

    public static void bruteforce(Path inputFile, Path outputFile) {
        for (int i = 1; i < cryptoAlphabet.length; i++) {
            Back.setKey(i);
            decrypt(inputFile, outputFile);
            Boolean res = qualityOfText(outputFile);
            if (res) {
                break;
            }
        }


    }

    private static Boolean qualityOfText(Path pathIn) {

        try {

            String in = Files.readString(pathIn).toLowerCase();

            if (in.contains(". ")) {
                System.out.println("Э Вроде норм");
                return true;
            }


        } catch (
            Exception e) {
            e.printStackTrace();

        }

        return false;
    }

    public static HashMap getStatistic(Path pathIn) {

        try {
            String in = Files.readString(pathIn).toLowerCase();

            for (int i = 0; i < cryptoAlphabet.length; i++) {
                int count = 0;
                for (int j = 0; j < in.length(); j++) {
                    if (cryptoAlphabet[i] == in.charAt(j)) {
                        count++;
                    }
                }

                if (count != 0) {
                    System.out.println((double) count / (double) in.length() + " " + i + "  sd" + cryptoAlphabet[i]);
                    Double percentage = (double) count / (double) in.length();
                    Statistic.put(cryptoAlphabet[i], percentage);
                }
            }
            ;
            return Statistic;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Statistic;
    }

    public static void statAnalysis(Path inputFile, Path inputFile1, Path outputFile) {
        HashMap<Character, Double> statistic = new HashMap<>();
        HashMap<Character, Double> statistic2 = new HashMap<>();
        statistic = getStatistic(inputFile);
        statistic2 = getStatistic(inputFile1);
        Boolean isPresent = false;
        for (Character character : statistic.keySet()) {
            for (Character character1 : statistic2.keySet()) {
                if (character == character1) {
                    isPresent = true;
                }
            }
            if (!isPresent) {

            }
        }

    }
}

