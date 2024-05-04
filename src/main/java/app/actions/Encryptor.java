package app.actions;

import app.helpers.DialogHelper;

import java.nio.file.Path;

public class Encryptor implements Action {


    @Override
    public void run() {
        String pathTo = "";
        String pathFrom = new DialogHelper().getLine("Enter a file path with text that needs to be encrypted: ");
        pathFrom = getNewPathIfNotValid(pathFrom);
        acts.encrypt(pathFrom, pathTo);

    }
}
