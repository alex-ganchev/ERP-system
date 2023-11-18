import java.util.ArrayList;

public class Project {
    private String name;
    private ArrayList<Employee> employeeList;
    private String projectExpiryDate;

    public Project(String name, ArrayList<Employee> employeeList, String projectExpiryDate) {
        this.name = name;
        this.employeeList = employeeList;
        this.projectExpiryDate = projectExpiryDate;
    }

    @Override
    public String toString() {
        return name;
    }
}
