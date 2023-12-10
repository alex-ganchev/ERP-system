import java.util.Scanner;

public class Menu {
    public static void loginMenu(Scanner scanner) {
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
            if (!User.validateLogin(username, password)) {
                System.out.println("------------------------------------");
                System.out.println("Грешно потребителско име или парола!");
                System.out.println("------------------------------------");
            }
        } while (User.activeUser == null);
        defineMenu(scanner);
    }

    private static void defineMenu(Scanner scanner) {
        if (User.activeUser.getRole().equals(Role.ADMIN)) {
            adminMenu(scanner);
        } else if (User.activeUser.getRole().equals(Role.EMPLOYEE)) {
            employeeMenu(scanner);
        } else {
            System.out.println("Нещо се обърка!");
        }
    }

    public static void adminMenu(Scanner scanner) {
        String input;
        System.out.println("--------------- МЕНЮ ---------------");
        System.out.println(User.activeUser.getRole().getName() + " : " + User.activeUser.getName());
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
                adminMenuStatisticManagement(scanner);
                break;
            case "0":
                exit();
                break;
            default:
                System.out.println("Въведена е невалидна стойност!");
        }
        adminMenu(scanner);
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
                    Admin.addNewClient(scanner);
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
            System.out.println("3 - Списък на потребителите");
            System.out.println("0 - Изход");
            System.out.println("------------------------------------");
            System.out.print("Вашият избор : ");
            input = scanner.next();
            switch (input) {
                case "1":
                    Admin.addNewUser(scanner, Role.EMPLOYEE);
                    break;
                case "2":
                    Admin.addNewUser(scanner, Role.ADMIN);
                    break;
                case "3":
                    User.printAllUsers();
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

    public static void adminMenuStatisticManagement(Scanner scanner) {
        String input;
        do {
            System.out.println("------------------------------------");
            System.out.println("       СТАТИСТИКА ЗА СЛУЖИТЕЛИ      ");
            System.out.println("------------------------------------");
            System.out.println("1 - Търсене по име на служител");
            System.out.println("2 - Търсене по номер на седмица");
            System.out.println("0 - Изход");
            System.out.println("------------------------------------");
            System.out.print("Вашият избор : ");
            input = scanner.next();
            switch (input) {
                case "1":
                    Admin.readReportsByEmployeeName(scanner);
                    break;
                case "2":
                    Admin.readReportsByNumberOfWeek(scanner);
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
        System.out.println(User.activeUser.getRole().getName() + " : " + User.activeUser.getName());
        System.out.println("------------------------------------");
        System.out.println("1 - Попълване на дневен отчет");
        System.out.println("2 - Показване на пълна статистика");
        System.out.println("3 - Статистика от дата до дата");
        System.out.println("0 - Изход");
        System.out.println("------------------------------------");
        System.out.print("Вашият избор : ");
        input = scanner.next();
        switch (input) {
            case "1":
                employeeMenuDailyReport(scanner);
                break;
            case "2":
                ReportGenerator.printReports(ReportGenerator.reportsByUser(User.activeUser));
                break;
            case "3":
                ReportGenerator.printReports(ReportGenerator.reportsByDate(scanner));
                break;
            case "0":
                exit();
                break;
            default:
                System.out.println("Въведена е невалидна стойност!");
        }
        employeeMenu(scanner);
    }

    public static void employeeMenuDailyReport(Scanner scanner) {
        String input;
        System.out.println("----------- ДНЕВЕН ОТЧЕТ -----------");
        System.out.println("1 - Дневен отчет за : " + AppConstants.toDayString);
        System.out.println("2 - Дневен отчет за отминал период");
        System.out.println("0 - Изход");
        System.out.println("------------------------------------");
        System.out.print("Вашият избор : ");
        input = scanner.next();
        switch (input) {
            case "1":
                Employee.addDailyReport(scanner, AppConstants.toDay);
                break;
            case "2":
                Employee.addDailyReport(scanner, null);
                break;
            case "0":
                employeeMenu(scanner);
                break;
            default:
                System.out.println("Въведена е невалидна стойност!");
        }
        employeeMenuDailyReport(scanner);
    }

    public static void exit() {
        System.out.println("--------------- ИЗХОД --------------");
        System.out.println("Андрей Димитров и Александър Ганчев");
        System.out.println("    https://digitalrazgrad.org/");
        System.out.println("------------------------------------");
        System.exit(0);
    }

}