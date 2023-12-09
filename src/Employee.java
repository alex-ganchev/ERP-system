import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Employee extends User {

    public Employee(String name, String username, String password) {
        super.setName(name);
        super.setUsername(username);
        super.setPassword(password);
        super.setRole(Role.employee);
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

    public static void printAllReports(Scanner scanner) {
        List<DailyReport> reports = FileHandler.readReports();

        List<DailyReport> reportsByEmployee = reports.stream()
                .filter(dailyReport -> dailyReport.getEmployee().getName().equals(activeUser.getName()))
                .toList();

        Map<LocalDate, List<DailyReport>> groupedDailyReports = reportsByEmployee.stream()
                .sorted(Comparator.comparing(DailyReport::getDate))
                .collect(Collectors.groupingBy(DailyReport::getDate, TreeMap::new, Collectors.toList()));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%-13s %-13s %-12s %-24s %-25s%n", "Дата", "Общо часове", "Часове", "Клиент", "Проект");
        System.out.println("----------------------------------------------------------------------------------------");

        for (Map.Entry<LocalDate, List<DailyReport>> entry : groupedDailyReports.entrySet()) {
            LocalDate key = entry.getKey();
            List<DailyReport> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                System.out.printf("%-13s %-13s %-12s %-24s %-25s%n",
                        (i == 0 ? key.format(AppConstants.DATE_FORMAT) : ""),
                        (i == 0 ? Validation.returnAllHoursReportedByDate(key) : ""),
                        value.get(i).getTime(),
                        value.get(i).getClient().getName(),
                        value.get(i).getClient().getProject());
            }
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }
}


