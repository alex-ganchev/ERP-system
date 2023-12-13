import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;


public class MenuTest {
    @Test
    void testLoginMenuReturnCorrectOutputWithIncorrectUsernameAndPassword() {
        //GIVEN
        ByteArrayInputStream testIn = new ByteArrayInputStream("user\n1234\n".getBytes());
        System.setIn(testIn);
        //WHEN
        try {
            Menu.loginMenu(new Scanner(System.in));
        } catch (Exception e) {
            System.out.println();
        }
        //THEN
        Assertions.assertNotNull(User.activeUser);
    }

}
