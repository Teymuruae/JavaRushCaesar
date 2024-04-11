package app;

import app.helpers.IOHelper;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Acts {
    private static int alphabetLength = Constants.ALPHABET.length();

    private static int checkKey(int key) {
        int keyToReturn = key;
        if (key > alphabetLength) {
            keyToReturn = key % alphabetLength;
        } else if (key < 0) {
            keyToReturn = alphabetLength + key % alphabetLength;
        }
        return keyToReturn;
    }

    public static String encoder(String text, int key) {
        int index;
        int newIndex = 0;
        key = checkKey(key);
        char[] txt = text.toCharArray();

        String newText = "";
        for (int i = 0; i < txt.length; i++) {
            String value = String.valueOf(txt[i]);
            if (Constants.ALPHABET.contains(value)) {
                index = Constants.ALPHABET.indexOf(value);
                newIndex = index - key;
                if (newIndex < 0) {
                    newIndex = alphabetLength + newIndex;
                } else if (newIndex > alphabetLength) {
                    newIndex = newIndex - alphabetLength;
                }
                newText = newText + Constants.ALPHABET.charAt(newIndex);
            } else {
                newText = newText + value;
            }
        }
        return newText;
    }

    public static String decoder(String text, int key) {
        return encoder(text, -key);
    }

    public static void encodeToFile(Path from, Path to, int key) {
        String text = IOHelper.reader(from);

        String encodedText = encoder(text, key);
        IOHelper.writer(to, encodedText);
    }

    public static void decodeFromFile(Path from, Path to, int key) {
        String text = IOHelper.reader(from);

        String decodedText = decoder(text, key);
        IOHelper.writer(to, decodedText);
    }

    public static void bruteForce(Path from, Path to) {
        String text = IOHelper.reader(from);

        String result = "";
        String[] optionText = new String[alphabetLength];
        for (int i = 0; i < alphabetLength - 1; i++) {
            optionText[i] = Acts.decoder(text, i);
        }
        for (int i = 0; i < optionText.length; i++) {
            if (countIndexes(optionText[i], ". ") > 5
                    && countIndexes(optionText[i], ", ") > 5) {
                result = optionText[i];
                break;
            }
        }
        IOHelper.writer(to, result);
    }

    private static int countIndexes(String text, String symbol) {
        List<Integer> lIst = new ArrayList<>();
        int index = text.indexOf(symbol);
        while (index >= 0) {
            lIst.add(index);
            index = text.indexOf(symbol, index + 1);

        }
        int counter = 0;
        for (Integer integer : lIst) {
            if (symbol.equals(". ")) {
                if (Character.isUpperCase(text.charAt(integer + 2))) {
                    counter++;
                }
            } else if (symbol.equals(", ")) {
                if (Character.isLowerCase(text.charAt(integer + 2))) {
                    counter++;
                }
            }
        }
        return counter;
    }
}