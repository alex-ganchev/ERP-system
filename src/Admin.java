import java.lang.reflect.Array;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        if(User.validateNewUser(username,name)){
            if(role.equals(Role.admin)){
                FileHandler.writeUser(new Admin(name, username, password));
            }else if(role.equals(Role.employee)){
                FileHandler.writeUser(new Employee(name, username, password));
            }else{
                System.out.println("Нещо се обърка!");
            }
        }else{
            System.out.println("Потребителя вече съществува в базата!");
        }
        Menu.adminMenuUserManagement(scanner);
    }

    public static void addNewClient(Scanner scanner) {
        scanner.nextLine();
        System.out.println("------------------------------------");
        System.out.print("Въведете име на клиента : ");
        String clientName = scanner.nextLine();
        System.out.print("Въведете име на проекта : ");
        String projectName = scanner.nextLine();
        System.out.print("Въведете дата на проекта : ");
        String projectDate = scanner.nextLine();
        Client newClient = new Client(clientName, projectName, projectDate);
        FileHandler.writeClient(newClient);
        Menu.adminMenuClientManagement(scanner);
    }

    public static void readReportsByEmployeeName(Scanner scanner) {
        scanner.nextLine();
        System.out.println("------------------------------------");
        System.out.print("Въведете име на служителя : ");
        String employeeName = scanner.nextLine();
        System.out.println("------------------------------------");
        List<DailyReport> reports = FileHandler.readReports();
        List<DailyReport> reportsByEmployee = reports.stream().filter(dailyReport -> dailyReport.getEmployee().equals(employeeName)).collect(Collectors.toList());
        for(DailyReport report:reportsByEmployee){
            System.out.println("Дата: " + report.getDate() + "\nКлиент: " + report.getClient() + "\nПроект: " + report.getProject() + "\nСлужител: " + report.getEmployee() + "\nВреме: " + report.getTime() + "\n");
        }
    }
}