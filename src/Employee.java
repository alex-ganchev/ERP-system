import java.util.ArrayList;
import java.util.Date;
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
        scanner.nextLine();
        Client.printAllClients();
        Client selectedClient = Client.returnSelectedClient(scanner);
        scanner.nextLine();
        while (true) {
            if (date == null) {
                while (true) {
                    System.out.print("Въведете дата : ");
                    date = scanner.nextLine();
                    try {
                        Date validateDate = DailyReport.dateFormat.parse(date);
                        if (date.equals(DailyReport.dateFormat.format(validateDate))) {
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("Въведете дата във формат дд/мм/гггг");
                    }
                }
            }
            while (true) {
                time = 0;
                System.out.print("Въведете часове : ");
                try {
                    String input = scanner.next();
                    time = Double.parseDouble(input);
                } catch (Exception e) {
                    System.out.println("Въведен е невалиден формат за час!");
                }
                if (time > 8) {
                    System.out.println("Въведени са повече от 8 часа!");
                } else if (time < 0) {
                    System.out.println("Въведени са отрицателни часове!");
                } else if (time > 0) {
                    break;
                }
            }
            DailyReport dailyReport = new DailyReport(date, selectedClient.getName(), selectedClient.getProject(), activeUser.getName(), time);
            FileHandler.writeReport(dailyReport);
            Menu.employeeMenu(scanner);
        }
    }
}

