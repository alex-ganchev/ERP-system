import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;


public class MenuTest {
     @Test
    void testEmployeeMenu() {
        //GIVEN
        ByteArrayInputStream testIn = new ByteArrayInputStream("iivanov\n1234\n1\n0\n2\n".getBytes());
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
    @Test
    void testAdminMenu() {
        //GIVEN
        ByteArrayInputStream testIn = new ByteArrayInputStream("aganchev\n1234\n1\n2\n0\n2\n3\n0\n3\n1\nиван иванов\n2\n49\n0\n".getBytes());
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
