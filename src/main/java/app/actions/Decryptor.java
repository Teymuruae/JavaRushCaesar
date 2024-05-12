package app.actions;

import java.nio.file.Path;

public class Decryptor implements Action {

    private void printSuccess(String pathTo) {
        System.out.println("Success");
        System.out.println("Check decrypted text at file " + pathTo);
    }

    @Override
    public void run() {
        String pathFrom = ioHelper.getNewPathIfNotValid("Enter a file path with encrypted text: ");
        String pathTo = dialogHelper.getLine("Enter a file path for decrypted text");
        int key = dialogHelper.getInt("Enter a key");
        acts.decodeFromFile(Path.of(pathFrom), Path.of(pathTo), key);
        printSuccess(pathTo);
    }
}