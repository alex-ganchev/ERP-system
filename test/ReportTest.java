import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportTest {

    private static final ByteArrayOutputStream OUTPUT_STREAM_CAPTURE = new ByteArrayOutputStream();

    @BeforeEach
    public void beforeEach() {
        System.setOut(new PrintStream(OUTPUT_STREAM_CAPTURE));
        OUTPUT_STREAM_CAPTURE.reset();
    }

    @Test
    void testPrintReportByUserReturnCorrectOutputWithValidDailyReport() {
        //GIVEN
        List<DailyReport> testDailyReports = new ArrayList<>();
        DailyReport testDailyReport = new DailyReport(LocalDate.parse("11/11/2023", AppConstants.DATE_FORMAT), new Client("Тестов клиент", new Project("Тестов проект")), new Employee("Тестов служител"), 7.7);
        testDailyReports.add(testDailyReport);
        //WHEN
        Report.printReportsByUser(testDailyReports);
        String result = OUTPUT_STREAM_CAPTURE.toString();
        //THEN
        StringBuilder str = new StringBuilder();
        str.append(String.format("%54s\n", "СТАТИСТИКА ЗА " + testDailyReports.get(0).getEmployee().getName().toUpperCase()));
        str.append("----------------------------------------------------------------------------------------\n");
        str.append(String.format("%-13s %-13s %-12s %-24s %-25s%n", "Дата", "Общо часове", "Часове", "Клиент", "Проект"));
        str.append("----------------------------------------------------------------------------------------\n");
        str.append(String.format("%-13s %-13s %-12s %-24s %-25s%n",
                testDailyReport.getDate().format(AppConstants.DATE_FORMAT),
                0.0,
                testDailyReport.getTime(),
                testDailyReport.getClient().getName(),
                testDailyReport.getClient().getProject().getName()));
        str.append("----------------------------------------------------------------------------------------\n");
        String expectedResult = str.toString();
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testPrintReportByWeekReturnCorrectOutputWithValidDailyReport() {
        //GIVEN
        List<DailyReport> testDailyReports = new ArrayList<>();
        DailyReport testDailyReport = new DailyReport(LocalDate.parse("11/11/2023", AppConstants.DATE_FORMAT), new Client("Тестов клиент", new Project("Тестов проект")), new Employee("Тестов служител"), 7.7);
        testDailyReports.add(testDailyReport);
        //WHEN
        Report.printReportsByWeek(testDailyReports);
        String result = OUTPUT_STREAM_CAPTURE.toString();
        //THEN
        StringBuilder str = new StringBuilder();
        str.append("-------------------------------------------------------------------------------------------\n");
        str.append(String.format("%-13s %-20s %-8s %-24s %-25s%n", "Дата", "Служител", "Часове", "Клиент", "Проект"));
        str.append("-------------------------------------------------------------------------------------------\n");
        str.append(String.format("%-13s %-20s %-8s %-24s %-25s%n",
                testDailyReport.getDate().format(AppConstants.DATE_FORMAT),
                testDailyReport.getEmployee().getName(),
                testDailyReport.getTime(),
                testDailyReport.getClient().getName(),
                testDailyReport.getClient().getProject().getName()));
        str.append("-------------------------------------------------------------------------------------------\n");
        String expectedResult = str.toString();
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testPrintReportByUserReturnCorrectOutputWithEmptyDailyReport() {
        //GIVEN
        List<DailyReport> testDailyReports = new ArrayList<>();
        //WHEN
        Report.printReportsByUser(testDailyReports);
        String result = OUTPUT_STREAM_CAPTURE.toString().trim();
        //THEN
        Assertions.assertEquals("Няма намерени резултати!", result);
    }
    @Test
    void testReportByUserReturnCorrectListWhenUserIsNotValid() {
        //GIVEN
        //WHEN
        List<DailyReport> result = Report.reportsByUser("Несъществуващ служител");
        //THEN
        Assertions.assertTrue(result.isEmpty());
    }
    @Test
    void testReportByUserReturnCorrectListWhenUserIsValid() {
        //GIVEN
        //WHEN
        List<DailyReport> result = Report.reportsByUser("Иван Иванов");
        //THEN
        Assertions.assertFalse(result.isEmpty());
    }
}
