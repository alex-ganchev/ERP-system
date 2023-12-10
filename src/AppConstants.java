import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppConstants {
    public static final File FILE_USERS = new File("data/users.csv");
    public static final File FILE_CLIENTS = new File("data/clients.csv");
    public static final File FILE_DAILY_REPORTS = new File("data/reports.csv");
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final double MAX_WORKING_HOURS = 8;
    public static LocalDate toDay = LocalDate.now();
    public static String toDayString = toDay.format(DATE_FORMAT);


}
