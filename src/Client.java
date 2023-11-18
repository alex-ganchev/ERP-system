import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private String name;
    private ArrayList<Project> projects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
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

    public static void printAllClients() {
        ArrayList<Client> clients = FileHandler.readClients();
        System.out.println("------------------------------------");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(i + 1 + " - " + clients.get(i).getName());
        }
        //System.out.println("------------------------------------");
    }

    @Override
    public String toString() {
        return name;
    }
}
