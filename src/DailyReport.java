import java.time.LocalDate;
import java.util.Scanner;

public class DailyReport {
    private LocalDate date;
    private Client client;
    private User employee;
    private double time;

    public DailyReport(LocalDate date, Client client, User employee, double time) {
        this.date = date;
        this.client = client;
        this.employee = employee;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public Client getClient() {
        return client;
    }

    public User getEmployee() {
        return employee;
    }

    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        return date.format(AppConstants.DATE_FORMAT) + ';' + client.getName() + ';' + client.getProject() + ';' + employee.getName() + ';' + time;
    }
}