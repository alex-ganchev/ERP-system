import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private String client;
    private String project;
    private String date;

    public String getName() {
        return client;
    }

    public void setName(String name) {
        this.client = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Client(String client, String project, String date) {
        this.client = client;
        this.project = project;
        this.date = date;
    }

//    public static void addNewClient(Scanner scanner) {
//        scanner.nextLine();
//        System.out.println("------------------------------------");
//        System.out.print("Въведете име на клиента : ");
//        String clientName = scanner.nextLine();
//        System.out.print("Въведете име на проекта : ");
//        String projectName = scanner.nextLine();
//        System.out.print("Въведете дата на проекта : ");
//        String projectDate = scanner.nextLine();
//        Client newClient = new Client(clientName, projectName, projectDate);
//        FileHandler.writeClient(newClient);
//        Menu.adminMenuClientManagement(scanner);
//    }

    public static void printAllClients() {
        ArrayList<Client> clientsList = FileHandler.readClients();
        for (int i = 0; i < clientsList.size(); i++) {
            System.out.println("------------------------------------");
            System.out.println(i + 1 + " - Клиент : " + clientsList.get(i).getName());
            System.out.println("    Проект : " + clientsList.get(i).getProject());
            System.out.println("    Дата   : " + clientsList.get(i).getDate());
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
        return client + ";" + project + ";" + date;
    }
}