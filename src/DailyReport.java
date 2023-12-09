import java.time.LocalDate;

public class DailyReport {
    private LocalDate date;
    private String client;
    private String project;
    private String employee;
    private double time;

    public DailyReport(LocalDate date, String client, String project, String employee, double time) {
        this.date = date;
        this.client = client;
        this.project = project;
        this.employee = employee;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }
    public String getClient() {
        return client;
    }

    public String getProject() {
        return project;
    }

    public String getEmployee() {
        return employee;
    }

    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        return date.format(AppConstants.DATE_FORMAT) + ';' + client + ';' + project + ';' + employee + ';' + time;
    }
}