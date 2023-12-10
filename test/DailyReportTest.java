import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class DailyReportTest {
    private static final String EXPECTED = "11/11/2023;Тестов клиент;Тестов проект;Тестов служител;7.7";
    @Test
    void testToStringReturnValidOutput() {
        //GIVEN
        DailyReport testDailyReport = new DailyReport(LocalDate.parse("11/11/2023", AppConstants.DATE_FORMAT), new Client("Тестов клиент", "Тестов проект"), new Employee("Тестов служител"), 7.7);
        //WHEN
        String result = testDailyReport.toString();
        //THEN
        Assertions.assertEquals(EXPECTED,result);
    }
}
