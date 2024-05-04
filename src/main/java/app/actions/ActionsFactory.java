package app.actions;

import java.util.HashMap;

public class ActionsFactory {

    private HashMap<Integer, Action> map = new HashMap<Integer, Action>() {{
    1, new Encryptor(),
    2, new Decryptor(),
    3, new Brutforce(),
    4, new StaticAnalysis(),
    }
    };

    public Action getAction(int command) {
        return map.get(command);
    }
}
