import java.util.Date;
import java.util.List;

public abstract class Validation {
    public static boolean timeValidate(String input, String date) {
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
        } else if (allHours + time > 8){
            System.out.println("За " + date + " вече имате отчетени " + allHours + " часа.");
        }
        return false;
    }

    public static double returnAllHoursReportedByDate(String date) {
        double allHours = 0;
        List<DailyReport> reports = FileHandler.readReports();
        List<DailyReport> reportsByEmployee = reports.stream()
                .filter(dailyReport -> dailyReport.getEmployee().equals(User.activeUser.getName()))
                .filter(dailyReport -> dailyReport.getDate().equals(date))
                .toList();
        for (DailyReport report : reportsByEmployee) {
            allHours += report.getTime();
        }
        return allHours;
    }

    public static boolean dateFormateValidate(String date) {
        try {
            Date validateDate = DailyReport.dateFormat.parse(date);
            if (date.equals(DailyReport.dateFormat.format(validateDate))) {
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Въведете дата във формат дд/мм/гггг");
        }
        return false;
    }
    //TODO Да се добави валидация за въвеждане на дата по-голяма от toDay и по-малка от седмица назад.

}
