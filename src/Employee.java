import java.util.Scanner;

public class Employee extends User {

    public Employee(String name, String username, String password) {
        super.setName(name);
        super.setUsername(username);
        super.setPassword(password);
        super.setRole(Role.employee);
    }

    public static void addNewReport(Scanner scanner, String date) {
        double time;
        String input;
        scanner.nextLine();
        Client.printAllClients();
        Client selectedClient = Client.returnSelectedClient(scanner);
        scanner.nextLine();
        if (date == null) {
            do {
                System.out.print("Въведете дата : ");
                date = scanner.nextLine();
            } while (!Validation.dateFormateValidate(date));
        }
        do {
            System.out.print("Въведете часове : ");
            input = scanner.next();
        } while (!Validation.timeValidate(input));
        time = Double.parseDouble(input);
        DailyReport dailyReport = new DailyReport(date, selectedClient.getName(), selectedClient.getProject(), activeUser.getName(), time);
        FileHandler.writeReport(dailyReport);
        Menu.employeeMenu(scanner);
    }
}


