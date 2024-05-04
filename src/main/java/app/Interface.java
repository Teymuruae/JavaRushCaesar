package app;

import app.actions.Action;
import app.actions.ActionsFactory;
import app.helpers.DialogHelper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Interface {

    public static final String INPUT_QUESTION = "Please enter the path to the file: ";
    private Scanner scanner = new Scanner(System.in);
    private Acts acts = new Acts();

    private DialogHelper helper = new DialogHelper();

    private int readInt() {
        String value = new Scanner(System.in).nextLine();
        return Integer.valueOf(value);
    }

    private String readString() {
        return new Scanner(System.in).nextLine();
    }

    public void appStart() {
        int option;

        String optionText = "Options:\n " +
                "- to encode, enter 1\n " +
                "- to decode by key, enter 2\n" +
                "- to decode by bruteForce, enter 3\n" +
                "- to exit, enter 0";
        System.out.println(optionText);
        System.out.print("Enter an option number: ");

        option = scanner.nextInt();

        Action action = new ActionsFactory().getAction(option);
        action.run();
    }

    private void encrypt() {
        String pathTo = "";
          String pathFrom = helper.getLine("Enter a file path with text that needs to be encrypted: ");
        pathFrom = getNewPathIfNotValid(pathFrom);
        if (pathFrom.equals("0")) {
            System.out.println("Good bye!!!");
            return;
        }
        int key = helper.getInt("Enter a key: ");
        System.out.println(INPUT_QUESTION);
        pathTo = readString();
        acts.encodeToFile(Path.of(pathFrom), Path.of(pathTo), key);
        System.out.println("Success");
        System.out.println("Check encrypted text at file " + pathTo);
    }

    private void decrypt() {
        System.out.println("Enter a file path with encrypted text");
        String pathFrom = readString();
        pathFrom = getNewPathIfNotValid(pathFrom);
        if (pathFrom.equals("0")) {
            System.out.println("Good bye!!!");
            return;
        }
        System.out.println("Enter a file path for decrypted text");
        String pathTo = readString();
        System.out.println("Enter a key");
        int key = readInt();
        acts.decodeFromFile(Path.of(pathFrom), Path.of(pathTo), key);
        System.out.println("Success");
        System.out.println("Check decrypted text at file " + pathTo);
    }

    private void bruteForce() {
        System.out.println("Enter a file path with encrypted text");
        String pathFrom = readString();
        pathFrom = getNewPathIfNotValid(pathFrom);
        if (pathFrom.equals("0")) {
            System.out.println("Good bye!!!");
            return;
        }
        System.out.println("Enter a file path for decrypted text");
        String pathTo = readString();
        acts.bruteForce(Path.of(pathFrom), Path.of(pathTo));
        System.out.println("Success");
        System.out.println("Check decrypted text at file " + pathTo);
    }

    private String getNewPathIfNotValid(String pathName) {
        while (Files.notExists(Path.of(pathName))) {
            System.out.println("Wrong file path. Please, enter valid path or 0 for exit: ");
            pathName = readString();
            if (pathName.equals("0")) {
                break;
            }
        }
        return pathName;
    }
}