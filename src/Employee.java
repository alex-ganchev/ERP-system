import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends User {

    public Employee(String name, String username, String password) {
        super.setName(name);
        super.setUsername(username);
        super.setPassword(password);
        super.setRole(Role.employee);
    }
    public static void addNewReport(Scanner scanner) {
        scanner.nextLine();
        System.out.println("------------------------------------");
        Client.printAllClients();
        Client selectedClient = Client.returnSelectedClient(scanner);
        scanner.nextLine();
        System.out.print("Въведете дата : ");
        String date = scanner.nextLine();
        System.out.print("Въведете часове : ");
        double time = scanner.nextDouble();
        DailyReport dailyReport = new DailyReport(date, selectedClient, time);
        FileHandler.writeReport(dailyReport);
        Menu.employeeMenu(scanner);
    }

}
