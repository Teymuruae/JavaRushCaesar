package app.helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class IOHelper {

    public static String reader(Path path)  {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path);
        } catch (IOException ioException) {
            System.out.println("Path is wrong. Try again " + ioException.getMessage());
            ioException.printStackTrace();
        }
        String textToReturn = "";
        for (String line : lines) {
            textToReturn = textToReturn + line;
        }
        return textToReturn;
    }

    public static void writer(Path path, String text) {
        if(!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
        try {
            Files.writeString(path, text);
        } catch (IOException ioException) {
            System.out.println("Something wrong. Try again " + ioException.getMessage());
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        writer(Path.of("kuku.txt"), "kukushka");
    }
}