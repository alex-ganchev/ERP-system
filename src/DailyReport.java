import java.text.SimpleDateFormat;
import java.util.Date;
public class DailyReport {
    private String date;
    private String client;
    private String project;
    private String employee;
    private double time;
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static String toDay = dateFormat.format(new Date());

    public DailyReport(String date, String client, String project, String employee, double time) {
        this.date = date;
        this.client = client;
        this.project = project;
        this.employee = employee;
        this.time = time;
    }

    @Override
    public String toString() {
        return date + ';' + client + ';' + project + ';' + employee + ';' + time;
    }
}