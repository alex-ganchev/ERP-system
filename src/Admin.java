import java.util.Scanner;

public class Admin extends User {
    public Admin( String name, String username, String password) {
        super.setUsername(username);
        super.setPassword(password);
        super.setName(name);
        super.setRole(Role.admin);
    }

    public static void addNewAdmin(Scanner scanner) {
        System.out.println("------------------------------------");
        System.out.print("Въведете имена : ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Въведете потребителско име : ");
        String username = scanner.nextLine();
        System.out.print("Въведете парола : ");
        String password = scanner.nextLine();
        User newAdmin = new Admin(name, username, password);
        FileHandler.writeUser(newAdmin);
        Menu.adminMenuUserManagement(scanner);
    }

    public static void addNewEmployee(Scanner scanner) {
        System.out.println("------------------------------------");
        System.out.print("Въведете имена : ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Въведете потребителско име : ");
        String username = scanner.nextLine();
        System.out.print("Въведете парола : ");
        String password = scanner.nextLine();
        User newEmployee = new Employee(name, username, password);
        FileHandler.writeUser(newEmployee);
        Menu.adminMenuUserManagement(scanner);
    }
}
