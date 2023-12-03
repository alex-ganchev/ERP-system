import java.util.Scanner;

public class DailyReport {
    private String date;
    private String employee;
    private String client;
    private double time;

    public DailyReport(String date, String client, String employee, double time) {
        this.date = date;
        this.client = client;
        this.time = time;
        this.employee = employee;
    }
    @Override
    public String toString() {
        return date + ';' + client + ';' + employee + ';' + time;
    }
}