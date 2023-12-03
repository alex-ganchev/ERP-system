import java.util.Scanner;

public class DailyReport {
    private String date;
    private Employee employee;
    private Client client;
    private double time;

    public DailyReport(String date, Client client, double time) {
        this.date = date;
        this.client = client;
        this.time = time;
        this.employee = (Employee) User.activeUser;
    }

    @Override
    public String toString() {
        return date + ';' + employee.getName() + ';' + client + ';' + time;
    }
}
