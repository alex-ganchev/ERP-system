import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {
    public static User activeUser;
    private String name;
    private String username;
    private String password;
    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void printAllUsers() {
        ArrayList<User> users = FileHandler.readUsers();
        for (int i = 0; i < users.size(); i++) {
            System.out.println("------------------------------------");
            System.out.println(i + 1 + " - " + users.get(i).getRole().getName() + " : " + users.get(i).getName());
            System.out.println("    username : " + users.get(i).getUsername());
        }
    }

    public static void addDailyReport(Scanner scanner, LocalDate date) {
        double time;
        String input;
        scanner.nextLine();
        if (date == null) {
            do {
                System.out.print("Въведете дата : ");
                input = scanner.nextLine();
            } while (!Validation.isDateFormatValid(input) || Validation.isDateAfterNow(input));
            date = LocalDate.parse(input, AppConstants.DATE_FORMAT);
        }

        if (Validation.returnAllHoursReportedByDate(date, activeUser) == AppConstants.MAX_WORKING_HOURS) {
            System.out.println("За " + date.format(AppConstants.DATE_FORMAT) + " вече имате отчетени " + AppConstants.MAX_WORKING_HOURS + " часа.");
        } else {
            Client.printAllClients();
            Client selectedClient;
            do {
                selectedClient = Client.returnSelectedClient(scanner);
            } while (Validation.isDateAfterClientEndDate(date, selectedClient));
            scanner.nextLine();
            do {
                System.out.print("Въведете часове : ");
                input = scanner.next();
            } while (!Validation.isTimeValid(input, date));
            time = Double.parseDouble(input);
            DailyReport dailyReport = new DailyReport(date, selectedClient, activeUser, time);
            FileHandler.writeObject(dailyReport, AppConstants.FILE_DAILY_REPORTS);
        }
    }

    @Override
    public String toString() {
        return name + ";" + username + ";" + password + ";" + role;
    }
}