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
            Client selectedClient = Client.returnSelectedClient(scanner);
            scanner.nextLine();
            do {
                System.out.print("Въведете часове : ");
                input = scanner.next();
            } while (!Validation.timeValidate(input, date));
            time = Double.parseDouble(input);
            DailyReport dailyReport = new DailyReport(date, selectedClient.getName(), selectedClient.getProject(), activeUser.getName(), time);
            FileHandler.writeReport(dailyReport);
        }
    }

    public static void printAllReports(Scanner scanner) {
        //TODO Да се оправи визуализацията.
        System.out.println("------------------------------------");
        System.out.println("Дата\t\tОбщо часове\tЧасове\t\tКлиент\t\t\t\tПроект");
        List<DailyReport> reports = FileHandler.readReports();

        List<DailyReport> reportsByEmployee = reports.stream()
                .filter(dailyReport -> dailyReport.getEmployee().equals(activeUser.getName()))
                .toList();

        Map<LocalDate, List<DailyReport>> groupedDailyReports = reportsByEmployee.stream()
                .sorted(Comparator.comparing(DailyReport::getDate))
                .collect(Collectors.groupingBy(DailyReport::getDate, TreeMap::new, Collectors.toList()));

        for (Map.Entry<LocalDate, List<DailyReport>> entry : groupedDailyReports.entrySet()) {
            LocalDate key = entry.getKey();
            List<DailyReport> value = entry.getValue();
            System.out.print(key.format(AppConstants.DATE_FORMAT) + "\t\t" + Validation.returnAllHoursReportedByDate(key));
            for (int i = 0; i < value.size(); i++) {
                System.out.println((i == 0 ? "\t\t" : "\t\t\t\t\t\t") + value.get(i).getTime() + "\t\t" + value.get(i).getClient() + "\t\t" + value.get(i).getProject());
            }
        }
    }
}


