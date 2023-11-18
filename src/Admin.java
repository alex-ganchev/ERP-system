import java.util.Scanner;

public class Admin extends User{
    public Admin(String username, String password) {
        super.setUsername(username);
        super.setPassword(password);
        super.setRole(Role.admin);
    }
    public static void addNewAdmin(Scanner scanner) {
        System.out.println("------------------------------------");
        System.out.print("Въведете потребителско име : ");
        String username = scanner.next();
        System.out.print("Въведете парола : ");
        String password = scanner.next();
        User newAdmin = new Admin(username, password);
        FileHandler.writeUser(newAdmin);
        Menu.adminMenuUserManagement(scanner);
    }
}
