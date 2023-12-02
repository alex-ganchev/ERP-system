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
        this.employee = (Employee) Menu.activeUser;
    }

    public static void addNewReport(Scanner scanner) {
        scanner.nextLine();
        System.out.println("------------------------------------");
        Client.printAllClients();
        Client selectedClient = Client.returnSelectedClient(scanner);
        scanner.nextLine();
        System.out.print("Въведете дата : ");
        String date = scanner.nextLine();
        System.out.print("Въведете часове : ");
        double time = scanner.nextDouble();
        DailyReport dailyReport = new DailyReport(date, selectedClient, time);
        FileHandler.writeReport(dailyReport);
        Menu.employeeMenu(scanner);
    }

    @Override
    public String toString() {
        return date + ';' + employee.getUsername() + ';' + client + ';' + time;
    }
}
