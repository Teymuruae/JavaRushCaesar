package app.helpers;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class DialogHelper {

    private Scanner scanner = new Scanner(System.in);
    public String getLine(String what) {
        String line = "";
        while (scanner.hasNextLine()) {
            System.out.println(what);
            line = scanner.nextLine();
        }
        return line;
    }

    public int getInt(String what) {
        String result = getLine(what);
        return Integer.getInteger(result);
    }
}
