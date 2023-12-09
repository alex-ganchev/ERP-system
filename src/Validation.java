import java.time.LocalDate;
import java.util.List;

public class Validation {
    public static boolean timeValidate(String input, LocalDate date) {
        double hours = 0;
        double allHours = returnAllHoursReportedByDate(date);
        try {
            hours = Double.parseDouble(input);
        } catch (Exception e) {
            System.out.println("Въведен е невалиден формат за час!");
        }
        if (hours > 8) {
            System.out.println("Въведени са повече от 8 часа!");
        } else if (hours < 0) {
            System.out.println("Въведени са отрицателни часове!");
        } else if (hours > 0 && ((allHours + hours) <= 8)) {
            return true;
        } else if (allHours + hours > 8) {
            System.out.println("За " + date.format(AppConstants.DATE_FORMAT) + " вече имате отчетени " + allHours + " часа.");
        }
        return false;
    }

    public static double returnAllHoursReportedByDate(LocalDate date) {
        double allHours = 0;
        List<DailyReport> reports = FileHandler.readReports();
        List<DailyReport> reportsByEmployee = reports.stream()
                .filter(dailyReport -> dailyReport.getEmployee().getName().equals(User.activeUser.getName()))
                .filter(dailyReport -> dailyReport.getDate().equals(date))
                .toList();
        for (DailyReport report : reportsByEmployee) {
            allHours += report.getTime();
        }
        return allHours;
    }

    public static boolean isDateFormatValid(String input) {
        try {
            LocalDate.parse(input, AppConstants.DATE_FORMAT);
            return true;
        } catch (Exception e) {
            System.out.println("Въведете дата във формат дд/мм/гггг");
        }
        return false;
    }

    public static boolean isDateAfterNow(String input) {
        LocalDate validateDate = LocalDate.parse(input, AppConstants.DATE_FORMAT);
        if (validateDate.isAfter(LocalDate.now())) {
            System.out.println("Не можете да се отчитата за бъдещ период.");
            return true;
        }
        return false;
    }
}
