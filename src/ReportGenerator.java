import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReportGenerator {
    public static void printReports(List<DailyReport> reports) {
        Map<LocalDate, List<DailyReport>> groupedDailyReports = reports.stream()
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

    public static List<DailyReport> reportsByUser(User user) {
        List<DailyReport> reports = FileHandler.readReports();

        List<DailyReport> reportsByEmployee = reports.stream()
                .filter(dailyReport -> dailyReport.getEmployee().getName().equals(user.activeUser.getName()))
                .toList();
        return reportsByEmployee;
    }

    public static List<DailyReport> reportsByDate(Scanner scanner) {
        scanner.nextLine();
        String input;
        LocalDate startDate;
        LocalDate endDate;
        do {
            System.out.print("Въведете начална дата : ");
            input = scanner.nextLine();
        } while (!Validation.isDateFormatValid(input) || Validation.isDateAfterNow(input));
        startDate = LocalDate.parse(input, AppConstants.DATE_FORMAT);
        do {
            System.out.print("Въведете крайна дата : ");
            input = scanner.nextLine();
        } while (!Validation.isDateFormatValid(input) || Validation.isDateAfterNow(input));
        endDate = LocalDate.parse(input, AppConstants.DATE_FORMAT);
        List<DailyReport> reports = FileHandler.readReports();

        List<DailyReport> reportsByDate = reports.stream()
                .filter(dailyReport -> dailyReport.getEmployee().getName().equals(User.activeUser.getName()))
                .filter(dailyReport -> (dailyReport.getDate().isAfter(startDate) || dailyReport.getDate().isEqual(startDate))
                        && (dailyReport.getDate().isBefore(endDate) || dailyReport.getDate().isEqual(endDate)))
                .toList();
        return reportsByDate;
    }
}
