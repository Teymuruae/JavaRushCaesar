package app.actions;

import java.nio.file.Path;

public class Encryptor implements Action {
    private final String INPUT_QUESTION = "Please enter the path to the file: ";

    private void printSuccess(String pathTo) {
        System.out.println("Success");
        System.out.println("Check encrypted text at file " + pathTo);
    }

    @Override
    public void run() {
        String pathFrom = ioHelper.getNewPathIfNotValid("Enter a file path with text that needs to be encrypted: ");
        int key = dialogHelper.getInt("Enter a key: ");
        String pathTo = dialogHelper.getLine(INPUT_QUESTION);
        acts.encodeToFile(Path.of(pathFrom), Path.of(pathTo), key);
        printSuccess(pathTo);
    }
}