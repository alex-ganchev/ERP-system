import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        if (Validation.returnAllHoursReportedByDate(date) == 8) {
            System.out.println("За " + date + " вече имате отчетени 8 часа.");
        } else {
            do {
                System.out.print("Въведете часове : ");
                input = scanner.next();
            } while (!Validation.timeValidate(input, date));
            time = Double.parseDouble(input);
            DailyReport dailyReport = new DailyReport(date, selectedClient.getName(), selectedClient.getProject(), activeUser.getName(), time);
            FileHandler.writeReport(dailyReport);
        }
        Menu.employeeMenu(scanner);
    }

    public static void report() {
        double allTime = 0;
        List<DailyReport> reports = FileHandler.readReports();
        List<DailyReport> reportsByEmployee = reports.stream()
                .filter(dailyReport -> dailyReport.getEmployee().equals(activeUser.getName()))
                .filter(dailyReport -> dailyReport.getDate().equals("03/12/2023"))
                .toList();
        for (DailyReport report : reportsByEmployee) {
            //System.out.println("Дата: " + report.getDate() + "\nКлиент: " + report.getClient() + "\nПроект: " + report.getProject() + "\nСлужител: " + report.getEmployee() + "\nВреме: " + report.getTime() + "\n");

        }
    }
}


