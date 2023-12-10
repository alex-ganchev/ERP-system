import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserTest {
    @Test
    void testToStringReturnValidOutputForEmployee() {
        //GIVEN
        User testUser = new Employee("Тестово име","testusername","testpassword");
        //WHEN
        String result = testUser.toString();
        //THEN
        String expected = "Тестово име;testusername;testpassword;EMPLOYEE";
        Assertions.assertEquals(expected, result);
    }
    @Test
    void testToStringReturnValidOutputForAdmin() {
        //GIVEN
        User testUser = new Admin("Тестово име","testusername","testpassword");
        //WHEN
        String result = testUser.toString();
        //THEN
        String expected = "Тестово име;testusername;testpassword;ADMIN";
        Assertions.assertEquals(expected, result);
    }
}
