import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ClientTest {
    @Test
    void testToStringReturnValidOutputForClient() {
        //GIVEN
        Client testClient = new Client("Тестов клиент", new Project("Тестов проект"), LocalDate.parse("13/12/2023", AppConstants.DATE_FORMAT));
        //WHEN
        String result = testClient.toString();
        //THEN
        String expected = "Тестов клиент;Тестов проект;13/12/2023";
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testReturnSelectedClientReturnClientWhenInputIsCorrect() {
        //GIVEN
        ByteArrayInputStream testIn = new ByteArrayInputStream("1".getBytes());
        System.setIn(testIn);
        //WHEN
        Client result = Client.returnSelectedClient(new Scanner(System.in));
        //THEN
        Assertions.assertNotNull(result);
    }
}
