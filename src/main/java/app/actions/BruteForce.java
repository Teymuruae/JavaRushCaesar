package app.actions;

import java.nio.file.Path;

public class BruteForce implements Action {

    private void printSuccess(String pathTo) {
        System.out.println("Success");
        System.out.println("Check encrypted text at file " + pathTo);
    }

    @Override
    public void run() {
        String pathFrom = ioHelper.getNewPathIfNotValid("Enter a file path with encrypted text: ");
        String pathTo = dialogHelper.getLine("Enter a file path for decrypted text");
        acts.bruteForce(Path.of(pathFrom), Path.of(pathTo));
        printSuccess(pathTo);
    }
}