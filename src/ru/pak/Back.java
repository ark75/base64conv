package ru.pak;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Back {
    private static int key = 0;
    public static char[] cryptoAlphabet;


    public static void setKey(int key) {
        Back.key = key;
    }

    public static int getKey() {
        return key % cryptoAlphabet.length;
    }

    public static void encrypt(Path pathIn, Path pathOut) {
        try {

            String in = Files.readString(pathIn).toLowerCase();
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                for (int j = 0; j < cryptoAlphabet.length; j++) {
                    if (in.charAt(i) == cryptoAlphabet[j]) {
                        int value = j + Back.getKey();
                        if (value >= cryptoAlphabet.length) {
                            value = value % (cryptoAlphabet.length);
                        }
                        out.append(cryptoAlphabet[value]);
                    }
                }
            }
            Files.writeString(pathOut, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void prepareAlphabet(String alphabet) {
        cryptoAlphabet = alphabet.toCharArray();
    }

    public static void decrypt(Path pathin, Path pathout) {
        try {

            String in = Files.readString(pathin).toLowerCase();
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                for (int j = 0; j < cryptoAlphabet.length; j++) {
                    if (in.charAt(i) == cryptoAlphabet[j]) {
                        int value = j - Back.getKey();
                        if (value < 0) {
                            value = value + (cryptoAlphabet.length);
                        }
                        out.append(cryptoAlphabet[value]);
                    }
                }
            }
            Files.writeString(pathout, out);
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    public static String decrypt(Path pathin) {
        try {
            String in = Files.readString(pathin).toLowerCase();
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                for (int j = 0; j < cryptoAlphabet.length; j++) {
                    if (in.charAt(i) == cryptoAlphabet[j]) {
                        int value = j - Back.getKey();
                        if (value < 0) {
                            value = value + (cryptoAlphabet.length);
                        }
                        out.append(cryptoAlphabet[value]);
                    }
                }
            }
            return out.toString();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void bruteforce(Path inputFile, Path outputFile) {
        for (int i = 1; i < cryptoAlphabet.length; i++) {
            Back.setKey(i);
            String out = decrypt(inputFile);
            assert out != null;
            Boolean res = qualityOfText(out);
            if (res) {
                try {
                    Files.writeString(outputFile, out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private static Boolean qualityOfText(String in) {
        boolean flag1 = false;
        boolean flag2 = true;
        if (in.contains(". ")) {
            flag1 = true;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(in);
        while (stringTokenizer.hasMoreTokens()) {
            String str = stringTokenizer.nextToken();
            if (str.length() > 30) {
                flag2 = false;
            }
        }
        return flag1 && flag2;
    }

    public static HashMap<Character, Double> getStatistic(Path pathIn) {
        try {
            HashMap<Character, Double> statistic = new HashMap<>();
            String in = Files.readString(pathIn).toLowerCase();

            for (int i = 0; i < cryptoAlphabet.length; i++) {
                int count = 0;
                for (int j = 0; j < in.length(); j++) {
                    if (cryptoAlphabet[i] == in.charAt(j)) {
                        count++;
                    }
                }
                Double percentage = (double) count / (double) in.length();
                statistic.put(cryptoAlphabet[i], percentage);
            }
            return statistic;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void statAnalysis(Path encryptedFile, Path sampleText, Path decryptedFile) {
        HashMap<Character, Double> statsOfDecryptedFile = new HashMap<>();
        HashMap<Character, Double> statsOfSampleText = new HashMap<>();
        HashMap<Character, Character> tableOfCorrespondence = new HashMap<>();
        statsOfDecryptedFile = getStatistic(encryptedFile);
        statsOfSampleText = getStatistic(sampleText);
        assert statsOfSampleText != null;
        assert statsOfDecryptedFile != null;

        while (!statsOfDecryptedFile.isEmpty()) {
            while (!statsOfSampleText.isEmpty()) {
                tableOfCorrespondence.put(mostFreqChar(statsOfDecryptedFile), mostFreqChar(statsOfSampleText));
                statsOfSampleText.remove(mostFreqChar(statsOfSampleText));
                statsOfDecryptedFile.remove(mostFreqChar(statsOfDecryptedFile));
            }
        }
        try {
            StringBuilder out = new StringBuilder();
            String in = Files.readString(encryptedFile).toLowerCase();
            for (int i = 0; i < in.length(); i++) {
                out.append(tableOfCorrespondence.get(in.charAt(i)));
            }
            Files.writeString(decryptedFile, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Character mostFreqChar(HashMap<Character, Double> valuesInMap) {
        if (valuesInMap.isEmpty()) {
            return null;
        }
        Double maxValue = 0.0;
        for (Double value : valuesInMap.values()) {
            if (value >= maxValue) {
                maxValue = value;
            }
        }
        for (Map.Entry<Character, Double> entry : valuesInMap.entrySet()) {
            if (entry.getValue() == maxValue) {
                return entry.getKey();
            }
        }
        return null;
    }
}

