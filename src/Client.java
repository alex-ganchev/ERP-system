import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client(String name) {
        this.name = name;
    }

    public static void addNewClient(Scanner scanner) {
        scanner.nextLine();
        System.out.println("------------------------------------");
        System.out.print("Въведете име на клиента : ");
        String clientName = scanner.nextLine();
        Client newClient = new Client(clientName);
        FileHandler.writeClient(newClient);
        Menu.adminMenuClientManagement(scanner);
    }

    public static ArrayList<Client> returnAllClients() {
        ArrayList<Client> clients = FileHandler.readClients();
        System.out.println("------------------------------------");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(i + 1 + " - " + clients.get(i).getName());
        }
        return clients;
    }

    public static Client returnSelectedClient(Scanner scanner) {
        ArrayList<Client> clientsList = returnAllClients();
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
        return name;
    }
}
