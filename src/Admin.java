import java.util.Scanner;

public class Admin extends User {
    public Admin( String name, String username, String password) {
        super.setUsername(username);
        super.setPassword(password);
        super.setName(name);
        super.setRole(Role.admin);
    }

    public static void addNewUser(Scanner scanner, Role role) {
        System.out.println("------------------------------------");
        System.out.print("Въведете имена : ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Въведете потребителско име : ");
        String username = scanner.nextLine();
        System.out.print("Въведете парола : ");
        String password = scanner.nextLine();
        if(role.equals(Role.admin)){
            FileHandler.writeUser(new Admin(name, username, password));
        }else if(role.equals(Role.employee)){
            FileHandler.writeUser(new Employee(name, username, password));
        }else{
            System.out.println("Нещо се обърка!");
        }
        Menu.adminMenuUserManagement(scanner);
    }


}