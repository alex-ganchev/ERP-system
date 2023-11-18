import java.util.ArrayList;
import java.util.Scanner;

public class Project {
    private Client client;
    private String name;
    private ArrayList<Employee> employeeList;
    private String projectExpiryDate;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getProjectExpiryDate() {
        return projectExpiryDate;
    }

    public void setProjectExpiryDate(String projectExpiryDate) {
        this.projectExpiryDate = projectExpiryDate;
    }

    public Project(Client client,String name, String projectExpiryDate) {
        this.client = client;
        this.name = name;
        //this.employeeList = employeeList;
        this.projectExpiryDate = projectExpiryDate;
    }

    public static void addNewProject(Scanner scanner, Client client) {
        scanner.nextLine();
        System.out.println("------------------------------------");
        System.out.println("КЛИЕНТ : " + client.getName());
        System.out.print("Въведете име на проект  : ");
        String projectName = scanner.nextLine();
        System.out.print("Въведете дата за край на проекта : ");
        String projectExpiryDate = scanner.next();
        Project newProject = new Project(client, projectName, projectExpiryDate);
        FileHandler.writeProject(newProject);
        Menu.adminMenuProjectManagement(scanner);
    }

    public static void printAllProject() {
        ArrayList<Project> projects = FileHandler.readProjects();
        System.out.println("------------------------------------");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println(i + 1 + " - " + projects.get(i).getClient() + " - "
                    + projects.get(i).getName() + " - " + projects.get(i).getProjectExpiryDate());
        }
    }

    @Override
    public String toString() {
        return client + ";" + name + ";" + projectExpiryDate;
    }
}
