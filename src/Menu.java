import java.util.Scanner;

public abstract class Menu {
    public static User activeUser;
    public static User loginMenu(Scanner scanner) {
        String password;
        String username;

        System.out.println("------------------------------------");
        System.out.println("    Enterprise Resource Planning    ");
        System.out.println("------------------------------------");
        do {
            System.out.print("Въведете потребителско име : ");
            username = scanner.next();
            System.out.print("Въведете парола : ");
            password = scanner.next();
            activeUser = User.validate(username, password);
            if (activeUser == null) {
                System.out.println("------------------------------------");
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
        System.out.println("--------------- МЕНЮ ---------------");
        System.out.println("     ПОТРЕБИТЕЛ: АДМИНИСТРАТОР");
        System.out.println("------------------------------------");
        System.out.println("1 - Управление на клиенти");
        System.out.println("2 - Управление на потребители");
        System.out.println("3 - Показване на статистика");
        System.out.println("0 - Изход");
        System.out.println("------------------------------------");
        System.out.print("Вашият избор : ");
        input = scanner.next();
        switch (input) {
            case "1":
                adminMenuClientManagement(scanner);
                break;
            case "2":
                adminMenuUserManagement(scanner);
                break;
            case "3":

                break;
            case "0":
                System.out.println("--------------- ИЗХОД --------------");
                System.out.println("Андрей Димитров и Александър Ганчев");
                System.out.println("    https://digitalrazgrad.org/");
                System.out.println("------------------------------------");
                break;
            default:
                System.out.println("Въведена е невалидна стойност!");
                adminMenu(scanner);
        }
    }

    public static void adminMenuClientManagement(Scanner scanner) {
        String input;
        do {
            System.out.println("------------------------------------");
            System.out.println("      УПРАВЛЕНИЕ НА КЛИЕНТИ     ");
            System.out.println("------------------------------------");
            System.out.println("1 - Добавяне на клиент");
            System.out.println("2 - Списък на клиентите");
            System.out.println("0 - Изход");
            System.out.println("------------------------------------");
            System.out.print("Вашият избор : ");
            input = scanner.next();
            switch (input) {
                case "1":
                    Client.addNewClient(scanner);
                    break;
                case "2":
                    Client.printAllClients();
                    break;
                case "0":
                    System.out.println("Избрахте изход!");
                    break;
                default:
                    System.out.println("Въведена е невалидна стойност!");
            }
        } while (!input.equals("0"));
        adminMenu(scanner);
    }

    public static void adminMenuUserManagement(Scanner scanner) {
        String input;
        do {
            System.out.println("------------------------------------");
            System.out.println("      УПРАВЛЕНИЕ НА ПОТРЕБИТЕЛИ     ");
            System.out.println("------------------------------------");
            System.out.println("1 - Добавяне на служител");
            System.out.println("2 - Добавяне на администратор");
            System.out.println("0 - Изход");
            System.out.println("------------------------------------");
            System.out.print("Вашият избор : ");
            input = scanner.next();
            switch (input) {
                case "1":
                    Admin.addNewEmployee(scanner);
                    break;
                case "2":
                    Admin.addNewAdmin(scanner);
                    break;
                case "0":
                    System.out.println("Избрахте изход!");
                    break;
                default:
                    System.out.println("Въведена е невалидна стойност!");
            }
        } while (!input.equals("0"));
        adminMenu(scanner);
    }

    public static void employeeMenu(Scanner scanner) {
        String input;

        System.out.println("--------------- МЕНЮ ---------------");
        System.out.println("       ПОТРЕБИТЕЛ: СЛУЖИТЕЛ");
        System.out.println("------------------------------------");
        System.out.println("1 - Попълване на дневен отчет");
        System.out.println("2 - Показване на статистика");
        System.out.println("0 - Изход");
        System.out.println("------------------------------------");
        System.out.print("Вашият избор : ");
        input = scanner.next();
        switch (input) {
            case "1":
                DailyReport.addNewReport(scanner);
                break;
            case "2":

                break;
            case "0":
                System.out.println("Избрахте изход!");
                break;
            default:
                System.out.println("Въведена е невалидна стойност!");
                employeeMenu(scanner);
        }
    }

}
