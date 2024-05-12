package app;

import app.actions.Action;
import app.actions.ActionsFactory;
import app.helpers.DialogHelper;

public class Interface {
    String optionText = "Options:\n " +
            "- to encode, enter 1\n " +
            "- to decode by key, enter 2\n" +
            "- to decode by bruteForce, enter 3\n" +
            "- to exit, enter 0\n" +
            "Enter an option number: ";

    private DialogHelper helper = new DialogHelper();

    public void appStart() {
        int option = helper.getInt(optionText);
        Action action = new ActionsFactory().getAction(option);
        action.run();
    }
}