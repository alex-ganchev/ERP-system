import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    public static void writeObject(Object object, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            PrintStream printSteam = new PrintStream(fileOutputStream);
            printSteam.append(object.toString()).append("\n");
            printSteam.close();
            System.out.println("Успешен запис!");
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Client> readClients() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(AppConstants.FILE_CLIENTS);
            while (fileReader.hasNextLine()) {
                String[] splitData = fileReader.nextLine().split(";");
                clients.add(new Client(splitData[0], new Project(splitData[1]), LocalDate.parse(splitData[2], AppConstants.DATE_FORMAT)));
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return clients;
    }

    public static ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(AppConstants.FILE_USERS);
            while (fileReader.hasNextLine()) {
                String[] splitData = fileReader.nextLine().split(";");
                if (splitData[3].equals(Role.EMPLOYEE.toString())) {
                    users.add(new Employee(splitData[0], splitData[1], splitData[2]));
                } else if (splitData[3].equals(Role.ADMIN.toString())) {
                    users.add(new Admin(splitData[0], splitData[1], splitData[2]));
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public static List<DailyReport> readReports() {
        List<DailyReport> reports = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(AppConstants.FILE_DAILY_REPORTS);
            while (fileReader.hasNextLine()) {
                String[] splitData = fileReader.nextLine().split(";");
                reports.add(new DailyReport(LocalDate.parse(splitData[0], AppConstants.DATE_FORMAT), new Client(splitData[1], new Project(splitData[2])), new Employee(splitData[3]), Double.valueOf(splitData[4])));
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return reports;
    }
}