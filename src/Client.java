import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private String client;
    private String project;
    private LocalDate date;

    public String getName() {
        return client;
    }

    public String getProject() {
        return project;
    }

    public LocalDate getDate() {
        return date;
    }

    public Client(String client, String project, LocalDate date) {
        this.client = client;
        this.project = project;
        this.date = date;
    }

    public static void printAllClients() {
        ArrayList<Client> clientsList = FileHandler.readClients();
        for (int i = 0; i < clientsList.size(); i++) {
            System.out.println("------------------------------------");
            System.out.println(i + 1 + " - Клиент : " + clientsList.get(i).getName());
            System.out.println("    Проект : " + clientsList.get(i).getProject());
            System.out.println("    Дата   : " + clientsList.get(i).getDate().format(AppConstants.DATE_FORMAT));
        }
    }

    public static Client returnSelectedClient(Scanner scanner) {
        ArrayList<Client> clientsList = FileHandler.readClients();
        Client client = null;
        do {
            System.out.println("------------------------------------");
            System.out.print("Изберете клиент : ");
            String input = scanner.next();
            try {
                client = clientsList.get(Integer.parseInt(input) - 1);
            } catch (Exception e) {
                System.out.println("Въведена е невалидна стойност!");
            }
        }
        while (client == null);
        return client;
    }

    @Override
    public String toString() {
        return client + ";" + project + ";" + date.format(AppConstants.DATE_FORMAT);
    }
}