import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Admin extends User{
    public Admin(String name, String username, String password) {
        super.setUsername(username);
        super.setPassword(password);
        super.setName(name);
        super.setRole(Role.ADMIN);
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
        if (Validation.validateNewUser(username, name)) {
            if (role.equals(Role.ADMIN)) {
                FileHandler.writeObject(new Admin(name, username, password),AppConstants.FILE_USERS);
            } else if (role.equals(Role.EMPLOYEE)) {
                FileHandler.writeObject(new Employee(name, username, password),AppConstants.FILE_USERS);
            } else {
                System.out.println("Нещо се обърка!");
            }
        } else {
            System.out.println("Потребителя вече съществува в базата!");
        }
    }

    public static void addNewClient(Scanner scanner) {
        String input;
        scanner.nextLine();
        System.out.println("------------------------------------");
        System.out.print("Въведете име на клиента : ");
        String clientName = scanner.nextLine();
        System.out.print("Въведете име на проекта : ");
        String projectName = scanner.nextLine();
        do {
            System.out.print("Въведете дата на проекта : ");
            input = scanner.nextLine();
        } while (!Validation.isDateFormatValid(input));
        LocalDate projectDate = LocalDate.parse(input, AppConstants.DATE_FORMAT);
        Client newClient = new Client(clientName, new Project(projectName), projectDate);
        FileHandler.writeObject(newClient,AppConstants.FILE_CLIENTS);
    }

    public static void readReportsByNumberOfWeek(Scanner scanner) {
        scanner.nextLine();
        System.out.println("------------------------------------");
        System.out.print("Въведете номер на седмица : ");
        int numberOfWeek = scanner.nextInt();
        System.out.println("------------------------------------");
        List<DailyReport> reports = FileHandler.readReports();
        List<DailyReport> reportsByNumOfWeek = reports.stream().filter(dailyReport -> numberOfWeek == dateNumderOfWeek(dailyReport.getDate().format(AppConstants.DATE_FORMAT))).collect(Collectors.toList());
        Report.printReportsByWeek(reportsByNumOfWeek);
    }

    private static int dateNumderOfWeek(String date) {
        int daysToDate = getDaysToDate(date);
        int numberOfWeek = daysToDate / 7;
        if (daysToDate % 7 != 0) {
            numberOfWeek++;
        }
        return numberOfWeek;
    }

    private static int getDaysToDate(String date) {
        String[] dateDetails = date.split("/");
        int days = Integer.parseInt(dateDetails[0]);
        int months = Integer.parseInt(dateDetails[1]);
        int daysToDate = 0;
        switch (months) {
            case 12:
                daysToDate += 30;
            case 11:
                daysToDate += 31;
            case 10:
                daysToDate += 30;
            case 9:
                daysToDate += 31;
            case 8:
                daysToDate += 31;
            case 7:
                daysToDate += 30;
            case 6:
                daysToDate += 31;
            case 5:
                daysToDate += 30;
            case 4:
                daysToDate += 31;
            case 3:
                daysToDate += 28;
            case 2:
                daysToDate += 31;
            case 1:
                daysToDate += -1;
        }
        daysToDate += days;
        return daysToDate;
    }
}