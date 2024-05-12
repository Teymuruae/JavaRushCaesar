package app.actions;

import app.Acts;
import app.helpers.DialogHelper;
import app.helpers.IOHelper;

public interface Action {

    DialogHelper dialogHelper = new DialogHelper();
    IOHelper ioHelper = new IOHelper();
    Acts acts = new Acts();

    public void run();
}
