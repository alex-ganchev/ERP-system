import java.util.ArrayList;

public class Employee extends User{
    private ArrayList<Report> report;

    public Employee(String username, String password) {
        super();
        super.setUsername(username);
        super.setPassword(password);
        super.setRole(Role.employee);
    }
}
