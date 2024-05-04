package app.actions;

public class Exit implements Action {
    @Override
    public void run() {
        System.out.println("Bye!");
        System.exit(1);
    }
}
