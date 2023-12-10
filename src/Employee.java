import java.time.LocalDate;
import java.util.*;

public class Employee extends User {

    public Employee(String name, String username, String password) {
        super.setName(name);
        super.setUsername(username);
        super.setPassword(password);
        super.setRole(Role.EMPLOYEE);
    }

    public Employee(String name) {
        super.setName(name);
    }

    public static void addDailyReport(Scanner scanner, LocalDate date) {
        double time;
        String input;
        scanner.nextLine();
        if (date == null) {
            do {
                System.out.print("Въведете дата : ");
                input = scanner.nextLine();
            } while (!Validation.isDateFormatValid(input) || Validation.isDateAfterNow(input));
            date = LocalDate.parse(input, AppConstants.DATE_FORMAT);
        }

        if (Validation.returnAllHoursReportedByDate(date) == 8) {
            System.out.println("За " + date.format(AppConstants.DATE_FORMAT) + " вече имате отчетени 8 часа.");
        } else {
            Client.printAllClients();
            Client selectedClient;
            do {
                selectedClient = Client.returnSelectedClient(scanner);
            } while (Validation.isDateAfterClientEndDate(date, selectedClient));
            scanner.nextLine();
            do {
                System.out.print("Въведете часове : ");
                input = scanner.next();
            } while (!Validation.isTimeValid(input, date));
            time = Double.parseDouble(input);
            DailyReport dailyReport = new DailyReport(date, selectedClient, activeUser, time);
            FileHandler.writeReport(dailyReport);
        }
    }
}


