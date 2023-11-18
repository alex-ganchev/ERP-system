import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends User{
    private ArrayList<Report> report;

    public Employee(String username, String password) {
        super.setUsername(username);
        super.setPassword(password);
        super.setRole(Role.employee);
    }
    public static void addNewEmployee(Scanner scanner) {
        System.out.println("------------------------------------");
        System.out.print("Въведете потребителско име : ");
        String username = scanner.next();
        System.out.print("Въведете парола : ");
        String password = scanner.next();
        User newEmployee = new Employee(username, password);
        FileHandler.writeUser(newEmployee);
        Menu.adminMenuUserManagement(scanner);
    }
}
