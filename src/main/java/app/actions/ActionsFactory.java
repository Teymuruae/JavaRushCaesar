package app.actions;

import java.util.HashMap;
import java.util.Map;

public class ActionsFactory {

    private HashMap<Integer, Action> map = new HashMap() {
        {
            put(0, new Exit());
            put(1, new Encryptor());
            put(2, new Decryptor());
            put(3, new BruteForce());
        }
    };

    public Action getAction(int command) {
        Action action = map.get(command);
        if(action == null){
            System.out.println("You entered an incorrect value. Please, try again.");
            System.exit(0);
        }
        return action;
    }
}