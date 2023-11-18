import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class FileHandler {
    private static PrintStream printSteam = null;
    private static Scanner fileReader = null;
    private static final File fileUsers = new File("src/users.csv");

    public static void writeUser(User user) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileUsers, true);
            printSteam = new PrintStream(fileOutputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        }
        if (printSteam != null) {
            printSteam.append("\n").append(user.toString());
            printSteam.close();
            System.out.println("Успешен запис!");
        }
    }

    public static ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            fileReader = new Scanner(fileUsers);
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        }
        if (fileReader != null) {
            while (fileReader.hasNextLine()) {
                String[] splitData = fileReader.nextLine().split(";");
                if (splitData[2].equals("employee")) {
                    users.add(new Employee(splitData[0], splitData[1]));
                } else if (splitData[2].equals("admin")) {
                    users.add(new Admin(splitData[0], splitData[1]));
                }
            }
            fileReader.close();
        }
        return users;
    }
}
