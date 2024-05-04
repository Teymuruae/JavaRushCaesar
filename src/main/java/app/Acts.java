package app;

import app.helpers.IOHelper;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Acts {
    private int alphabetLength = Constants.ALPHABET.length();
    private IOHelper ioHelper = new IOHelper();

    private int checkKey(int key) {
        int keyToReturn = key;
        if (key > alphabetLength) {
            keyToReturn = key % alphabetLength;
        } else if (key < 0) {
            keyToReturn = alphabetLength + key % alphabetLength;
        }
        return keyToReturn;
    }

    public String encode(String text, int key) {
        key = checkKey(key);
        char[] txt = text.toCharArray();

        String encryptedText = "";
        for (int i = 0; i < txt.length; i++) {
            String value = String.valueOf(txt[i]);
            if (Constants.ALPHABET.contains(value)) {
                int encryptedSymbol = (Constants.ALPHABET.indexOf(value) - key + alphabetLength) % alphabetLength;
                encryptedText = encryptedText + Constants.ALPHABET.charAt(encryptedSymbol);
            } else {
                encryptedText = encryptedText + value;
            }
        }
        return encryptedText;
    }

    public String decode(String text, int key) {
        return encode(text, -key);
    }

    public  void encodeToFile(Path from, Path to, int key) {
        String text = ioHelper.read(from);

        String encodedText = encode(text, key);
        ioHelper.write(to, encodedText);
    }

    public  void decodeFromFile(Path from, Path to, int key) {
        String text = ioHelper.read(from);

        String decodedText = decode(text, key);
        ioHelper.write(to, decodedText);
    }

    public void bruteForce(Path from, Path to) {
        String text = ioHelper.read(from);

        String result = "";
        String[] optionText = new String[alphabetLength];
        for (int i = 0; i < alphabetLength - 1; i++) {
            optionText[i] = decode(text, i);
        }
        for (int i = 0; i < optionText.length; i++) {
            if (countIndexes(optionText[i], ". ") > 5
                    && countIndexes(optionText[i], ", ") > 5) {
                result = optionText[i];
                break;
            }
        }
        ioHelper.write(to, result);
    }

    private int countIndexes(String text, String symbol) {
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