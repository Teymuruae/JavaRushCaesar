package app.actions;

import java.util.HashMap;

public class ActionsFactory {

    private HashMap<Integer, Action> map = new HashMap<Integer, Action>() {
        {
            put(0, new Exit());
            put(1, new Encryptor());
            put(2, new Decryptor());
            put(3, new BruteForce());
        }
    };

    public Action getAction(int command) {
        return map.get(command);
    }
}