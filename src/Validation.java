import java.time.LocalDate;
import java.util.List;

public class Validation {
    public static boolean isTimeValid(String input, LocalDate date) {
        double hours = 0;
        double allHours = returnAllHoursReportedByDate(date);
        try {
            hours = Double.parseDouble(input);
        } catch (Exception e) {
            System.out.println("Въведен е невалиден формат за час!");
        }
        if (hours > AppConstants.MAX_WORKING_HOURS) {
            System.out.println("Въведени са повече от " + AppConstants.MAX_WORKING_HOURS + " часа!");
        } else if (hours < 0) {
            System.out.println("Въведени са отрицателни часове!");
        } else if (hours > 0 && ((allHours + hours) <= AppConstants.MAX_WORKING_HOURS)) {
            return true;
        } else if (allHours + hours > AppConstants.MAX_WORKING_HOURS) {
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

    public static boolean isDateAfterClientEndDate(LocalDate date, Client client) {
        if (date.isAfter(client.getDate())) {
            System.out.println("Проекта е изтекъл към датата на отчитане!");
            return true;
        }
        return false;
    }
}
