package app.helpers;

import app.actions.Exit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class IOHelper {

    public String read(Path path) {
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

    public void write(Path path, String text) {
        if (!Files.exists(path)) {
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

    public String getNewPathIfNotValid(String textToPrint) {
       String pathName =  new DialogHelper().getLine(textToPrint);
        while (Files.notExists(Path.of(pathName))) {
            pathName = new DialogHelper().getLine("Wrong file path. Please, enter valid path or 0 for exit: ");
            if (pathName.equals("0")) {
                new Exit().run();
            }
        }
        return pathName;
    }
}