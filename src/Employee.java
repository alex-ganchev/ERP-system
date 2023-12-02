import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends User {

    public Employee(String username, String password) {
        super.setUsername(username);
        super.setPassword(password);
        super.setRole(Role.employee);
    }


}
