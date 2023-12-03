import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class FileHandler {
    private static PrintStream printSteam = null;
    private static Scanner fileReader = null;
    private static final File FILE_USERS = new File("src/users.csv");
    private static final File FILE_CLIENTS = new File("src/clients.csv");
    private static final File FILE_DAILY_REPORTS = new File("src/reports.csv");

    public static void writeUser(User user) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_USERS, true);
            printSteam = new PrintStream(fileOutputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        }
        if (printSteam != null) {
            printSteam.append(user.toString()).append("\n");
            printSteam.close();
            System.out.println("Успешен запис!");
        }
    }

    public static void writeClient(Client client) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_CLIENTS, true);
            printSteam = new PrintStream(fileOutputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        }
        if (printSteam != null) {
            printSteam.append(client.toString()).append("\n");
            printSteam.close();
            System.out.println("Успешен запис!");
        }
    }

    public static void writeReport(DailyReport report) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_DAILY_REPORTS, true);
            printSteam = new PrintStream(fileOutputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        }
        if (printSteam != null) {
            printSteam.append(report.toString()).append("\n");
            printSteam.close();
            System.out.println("Успешен запис!");
        }
    }

    public static ArrayList<Client> readClients() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            fileReader = new Scanner(FILE_CLIENTS);
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        }
        if (fileReader != null) {
            while (fileReader.hasNextLine()) {
                String[] splitData = fileReader.nextLine().split(";");
                clients.add(new Client(splitData[0], splitData[1], splitData[2]));
            }
            fileReader.close();
        }
        return clients;
    }

    public static ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            fileReader = new Scanner(FILE_USERS);
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        }
        if (fileReader != null) {
            while (fileReader.hasNextLine()) {
                String[] splitData = fileReader.nextLine().split(";");
                if (splitData[3].equals("employee")) {
                    users.add(new Employee(splitData[0], splitData[1], splitData[2]));
                } else if (splitData[3].equals("admin")) {
                    users.add(new Admin(splitData[0], splitData[1], splitData[2]));
                }
            }
            fileReader.close();
        }
        return users;
    }
}
