import java.time.LocalDate;
import java.util.*;

public class Employee extends User {

    public Employee(String name, String username, String password) {
        super.setName(name);
        super.setUsername(username);
        super.setPassword(password);
        super.setRole(Role.EMPLOYEE);
    }

    public Employee(String name) {
        super.setName(name);
    }


}


