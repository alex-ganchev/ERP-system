import java.util.Date;

public abstract class Validation {
    public static boolean timeValidate(String input) {
        double time = 0;
        try {
            time = Double.parseDouble(input);
        } catch (Exception e) {
            System.out.println("Въведен е невалиден формат за час!");
        }
        if (time > 8) {
            System.out.println("Въведени са повече от 8 часа!");
        } else if (time < 0) {
            System.out.println("Въведени са отрицателни часове!");
        } else if (time > 0) {
            return true;
        }
        return false;
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

}
