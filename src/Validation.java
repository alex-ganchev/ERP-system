import java.time.LocalDate;
import java.util.List;

public class Validation {
    public static boolean timeValidate(String input, LocalDate date) {
        double time = 0;
        double allHours = returnAllHoursReportedByDate(date);
        try {
            time = Double.parseDouble(input);
        } catch (Exception e) {
            System.out.println("Въведен е невалиден формат за час!");
        }
        if (time > 8) {
            System.out.println("Въведени са повече от 8 часа!");
        } else if (time < 0) {
            System.out.println("Въведени са отрицателни часове!");
        } else if (time > 0 && ((allHours + time) <= 8)) {
            return true;
        } else if (allHours + time > 8) {
            System.out.println("За " + date.format(AppConstants.DATE_FORMAT) + " вече имате отчетени " + allHours + " часа.");
        }
        return false;
    }

    public static double returnAllHoursReportedByDate(LocalDate dateString) {
        double allHours = 0;
        List<DailyReport> reports = FileHandler.readReports();
        List<DailyReport> reportsByEmployee = reports.stream()
                .filter(dailyReport -> dailyReport.getEmployee().equals(User.activeUser.getName()))
                .filter(dailyReport -> dailyReport.getDate().equals(dateString))
                .toList();
        for (DailyReport report : reportsByEmployee) {
            allHours += report.getTime();
        }
        return allHours;
    }

    public static boolean dateFormateValidate(String dateString) {
        try {
            LocalDate validateDate = LocalDate.parse(dateString, AppConstants.DATE_FORMAT);
            if (validateDate.isAfter(LocalDate.now())) {
                System.out.println("Не можете да се отчитата за бъдещ период.");
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Въведете дата във формат дд/мм/гггг");
        }
        return false;
    }

}
