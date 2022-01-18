package ru.pak;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class Back {
    private static int key;
    public static char[] cryptoAlphabet;

    public static void setKey(int key) {
        Back.key = key;
    }

    public static int getKey() {
        return key;
    }

    public static void crypto(Path enterPath) {
        try {
            System.out.println(message);
            var inputStream = Files.newBufferedReader(enterPath);
            while (inputStream.ready()) {
                char[] chars = inputStream.readLine().toLowerCase().toCharArray();
                int j = 0;
                for (int i = 0; i < chars.length; i++) {
                    while (chars[i] != cryptoAlphabet[j]) {
                        j++;
                    }
                    chars[i] = cryptoAlphabet[j + Back.getKey()];
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void prepareAlphabet(String alphabet) {
        cryptoAlphabet = alphabet.toCharArray();
    }
}

