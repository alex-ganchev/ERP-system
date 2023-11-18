import java.util.Scanner;

public abstract class Menu {
    public static User loginMenu(Scanner scanner) {
        String password;
        String username;
        User activeUser;
        System.out.println("------------------------------------");
        System.out.println("    Enterprise Resource Planning    ");
        System.out.println("------------------------------------");
        do {
            System.out.print("Въведете потребителско име : ");
            username = scanner.next();
            System.out.print("Въведете парола : ");
            password = scanner.next();
            activeUser = User.validate(username, password);
            System.out.println("------------------------------------");
            if(activeUser == null){
                System.out.println("Грешно потребителско име или парола!");
                System.out.println("------------------------------------");
            }
        } while (activeUser == null);
        return activeUser;
    }

    public static void defineMenu(Scanner scanner, User user) {
        if (user.getRole().equals(Role.admin)) {
            adminMenu(scanner);
        } else if (user.getRole().equals(Role.employee)) {
            employeeMenu(scanner);
        } else {
            System.out.println("Нещо се обърка!");
        }
    }

    public static void adminMenu(Scanner scanner) {
        String input;
        System.out.println("----------------МЕНЮ----------------");
        System.out.println("     ПОТРЕБИТЕЛ: АДМИНИСТРАТОР");
        System.out.println("------------------------------------");
        do{
        System.out.println("1 - Регистриране на клиент");
        System.out.println("2 - Регистриране на проект");
        System.out.println("3 - Регистриране на потребител");
        System.out.println("4 - Регистриране на администратор");
        System.out.println("5 - Показване на статистика");
        System.out.println("6 - Изход");
        System.out.println("------------------------------------");
            System.out.print("Вашият избор : ");
        input = scanner.next();
        switch (input) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                registerNewEmployee(scanner);
                break;
            case "4":
                registerNewAdmin(scanner);
                break;
            case "5":
                break;
            case "6":
                System.out.println("Избрахте изход!");
                break;
            default:
                System.out.println("Въведена е невалидна стойност!");
                System.out.println("------------------------------------");
        }
        }while(!input.equals("6"));
    }

    public static void employeeMenu(Scanner scanner) {
        System.out.println("----------------МЕНЮ----------------");
        System.out.println("       ПОТРЕБИТЕЛ: СЛУЖИТЕЛ");
        System.out.println("------------------------------------");
        System.out.println("1 - Попълване на дневен отчет");
        System.out.println("2 - Показване на статистика");
        System.out.println("3 - Изход");
        System.out.println("------------------------------------");
    }
    public static void registerNewEmployee(Scanner scanner){
        System.out.println("------------------------------------");
        System.out.print("Въведете потребителско име : ");
        String username = scanner.next();
        System.out.print("Въведете парола : ");
        String password = scanner.next();
        User newEmployee = new Employee(username,password);
        FileHandler.writeUser(newEmployee);
        adminMenu(scanner);
    }
    public static void registerNewAdmin(Scanner scanner){
        System.out.println("------------------------------------");
        System.out.print("Въведете потребителско име : ");
        String username = scanner.next();
        System.out.print("Въведете парола : ");
        String password = scanner.next();
        User newAdmin = new Admin(username,password);
        FileHandler.writeUser(newAdmin);
        adminMenu(scanner);
    }
}
