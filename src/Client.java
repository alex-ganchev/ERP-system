import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private String name;
    private Project project;
    private LocalDate date;

    public String getName() {
        return name;
    }

    public Project getProject() {
        return project;
    }

    public LocalDate getDate() {
        return date;
    }

    public Client(String name, Project project, LocalDate date) {
        this.name = name;
        this.project = project;
        this.date = date;
    }

    public Client(String name, Project project) {
        this.name = name;
        this.project = project;
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
        return name + ";" + project.getName() + ";" + date.format(AppConstants.DATE_FORMAT);
    }
}