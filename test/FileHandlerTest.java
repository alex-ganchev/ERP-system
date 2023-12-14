import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileHandlerTest {
    Employee employee;
    DailyReport dailyReport;
    LocalDate date;

    @Before
    public void setUp() {
        employee = new Employee("ТестовСлужител", "testEmployee", "1234");
        date = LocalDate.parse("2023-12-12");
        dailyReport = new DailyReport(date, new Client("ТД на НАП - Разград", new Project("Профилактика на UPS-и"), date), employee, 3);
    }

    @Test
    public void testAddNewUser() {
        int firstLen = FileHandler.readUsers().size();
        int expectedResult = firstLen;
        if (Validation.validateNewUser(employee.getUsername(), employee.getName())) {
            FileHandler.writeObject(employee, AppConstants.FILE_USERS);
            expectedResult++;
        }
        Assert.assertEquals(expectedResult, FileHandler.readUsers().size());
    }

    @Test
    public void testWriteDailyReport() {
        int expectedRes = FileHandler.readReports().size() + 1;
        FileHandler.writeObject(dailyReport, AppConstants.FILE_DAILY_REPORTS);
        Assert.assertEquals(expectedRes, FileHandler.readReports().size());
    }
}
