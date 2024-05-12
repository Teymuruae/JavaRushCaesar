package app.actions;

public class Exit implements Action {
    @Override
    public void run() {
        System.out.println("Good bye!!!");
        System.exit(0);
    }
}