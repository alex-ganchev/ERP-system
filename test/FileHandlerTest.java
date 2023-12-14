
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class FileHandlerTest {

    @Test
    public void testWriteDailyReportWithCorrectInput() {
        //GIVEN
        DailyReport testDailyReport = new DailyReport(LocalDate.parse("11/11/2023", AppConstants.DATE_FORMAT), new Client("Тестов клиент", new Project("Тестов проект")), new Employee("Тестов служител"), 7.7);
        //WHEN
        FileHandler.writeObject(testDailyReport, new File("testdata/reports.csv"));
        //THEN
        String result = "";
        try {
            Scanner fileReader = new Scanner(new File("testdata/reports.csv"));
            while (fileReader.hasNextLine()) {
                result = fileReader.nextLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(testDailyReport.toString(),result);
    }
    @Test
    public void testWriteClientWithCorrectInput() {
        //GIVEN
        Client testClient = new Client("Тестов клиент", new Project("Тестов проект"), LocalDate.parse("13/12/2023", AppConstants.DATE_FORMAT));
        //WHEN
        FileHandler.writeObject(testClient, new File("testdata/clients.csv"));
        //THEN
        String result = "";
        try {
            Scanner fileReader = new Scanner(new File("testdata/clients.csv"));
            while (fileReader.hasNextLine()) {
                result = fileReader.nextLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(testClient.toString(),result);
    }
    @Test
    public void testWriteUserWithCorrectInput() {
        //GIVEN
        User testUser = new Employee("Тестово име","testusername","testpassword");
        //WHEN
        FileHandler.writeObject(testUser, new File("testdata/users.csv"));
        //THEN
        String result = "";
        try {
            Scanner fileReader = new Scanner(new File("testdata/users.csv"));
            while (fileReader.hasNextLine()) {
                result = fileReader.nextLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(testUser.toString(),result);
    }
}
