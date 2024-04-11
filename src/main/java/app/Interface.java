package app;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Interface {

    private static Scanner scanner = new Scanner(System.in);

    private static int readInt() {
        String value = new Scanner(System.in).nextLine();
        return Integer.valueOf(value);
    }

    private static String readString() {
        return new Scanner(System.in).nextLine();
    }

    public static void appStarter() {
        int option;

        String optionText = "Options:\n " +
                "- to encode, enter 1\n " +
                "- to decode by key, enter 2\n" +
                "- to decode by bruteForce, enter 3\n" +
                "- to exit, enter 0";
        System.out.println(optionText);
        System.out.print("Enter an option number: ");

        option = scanner.nextInt();

        switch (option) {
            case 0:
                System.out.println("Good bye!!!");
                return;
            case 1:
                encrypt();
                break;
            case 2:
                decrypt();
                break;
            case 3:
                bruteForce();
                break;
            default:
                System.out.println("wrong option number. Try again");
        }

    }

    private static void encrypt() {
        String pathTo = "";
        System.out.println("Enter a file path with text that needs to be encrypted: ");
        String pathFrom = readString();
        pathFrom = newPathIfNotValid(pathFrom);
        if (pathFrom.equals("0")) {
            System.out.println("Good bye!!!");
            return;
        }
        System.out.println("Enter a key: ");
        int key = readInt();
        System.out.println("Enter a file path for encrypted text: ");
        pathTo = readString();
        Acts.encodeToFile(Path.of(pathFrom), Path.of(pathTo), key);
        System.out.println("Success");
        System.out.println("Check encrypted text at file " + pathTo);
    }

    private static void decrypt() {
        System.out.println("Enter a file path with encrypted text");
        String pathFrom = readString();
        pathFrom = newPathIfNotValid(pathFrom);
        if (pathFrom.equals("0")) {
            System.out.println("Good bye!!!");
            return;
        }
        System.out.println("Enter a file path for decrypted text");
        String pathTo = readString();
        System.out.println("Enter a key");
        int key = readInt();
        Acts.decodeFromFile(Path.of(pathFrom), Path.of(pathTo), key);
        System.out.println("Success");
        System.out.println("Check decrypted text at file " + pathTo);
    }

    private static void bruteForce() {
        System.out.println("Enter a file path with encrypted text");
        String pathFrom = readString();
        pathFrom = newPathIfNotValid(pathFrom);
        if (pathFrom.equals("0")) {
            System.out.println("Good bye!!!");
            return;
        }
        System.out.println("Enter a file path for decrypted text");
        String pathTo = readString();
        Acts.bruteForce(Path.of(pathFrom), Path.of(pathTo));
        System.out.println("Success");
        System.out.println("Check decrypted text at file " + pathTo);
    }

    private static String newPathIfNotValid(String pathName) {
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