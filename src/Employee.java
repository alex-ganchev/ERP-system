import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        if (date == null) {
            do {
                System.out.print("Въведете дата : ");
                date = scanner.nextLine();
            } while (!Validation.dateFormateValidate(date));
        }
        if (Validation.returnAllHoursReportedByDate(date) == 8) {
            System.out.println("За " + date + " вече имате отчетени 8 часа.");
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

    public static void report(Scanner scanner) {
        //TODO Да се оправи визуализацията.
        System.out.println("------------------------------------");
        System.out.println("Дата\t\tОбщо часове\t\tЧасове по проекти");
        List<DailyReport> reports = FileHandler.readReports();

        List<DailyReport> reportsByEmployee = reports.stream()
                .filter(dailyReport -> dailyReport.getEmployee().equals(activeUser.getName()))
                //.sorted(Comparator.comparing(DailyReport::getDate))
                .toList();

        Map<String, List<DailyReport>> groupedDailyReports = reportsByEmployee.stream()
                .sorted(Comparator.comparing(DailyReport::getDate))
                .collect(Collectors.groupingBy(DailyReport::getDate));

        for (Map.Entry<String, List<DailyReport>> entry : groupedDailyReports.entrySet()) {
            String key = entry.getKey();
            List<DailyReport> value = entry.getValue();
            System.out.print(key + "\t\t" + Validation.returnAllHoursReportedByDate(key));
            for (int i = 0; i < value.size(); i++) {
                System.out.println("\t\t\t" + value.get(i).getTime() + " <- " /* +value.get(i).getClient() + " | " */ + value.get(i).getProject());
            }
        }
    }
}


