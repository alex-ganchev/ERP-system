import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UserTest {
    ArrayList<User> users = FileHandler.readUsers();
    private static final String VALID_USERNAME = "user";
    private static final String VALID_PASSWORD = "1234";
    private static final String VALID_NAME = "Иван Иванов";
    private static final String INVALID_USERNAME = "invalidusername";
    private static final String INVALID_PASSWORD = "invalidpassword";
    private static final String INVALID_NAME = "invalidname";


    @BeforeEach
    public void setUp() {

    }

    @Test
    void testValidateLoginReturnTrueWithValidCredentials() {
        //GIVEN

        //WHEN
        boolean result = User.validateLogin(VALID_USERNAME, VALID_PASSWORD);
        //THEN
        Assertions.assertTrue(result);
    }
     @Test
    void testValidateLoginReturnFalseWithValidUsernameAndInvalidPassword() {
        //GIVEN

        //WHEN
        boolean result = User.validateLogin(VALID_USERNAME, INVALID_PASSWORD);
        //THEN
        Assertions.assertFalse(result);
    }
    @Test
    void testValidateLoginReturnFalseWithInvalidUsernameAndValidPassword() {
        //GIVEN

        //WHEN
        boolean result = User.validateLogin(INVALID_USERNAME, VALID_PASSWORD);
        //THEN
        Assertions.assertFalse(result);
    }
    @Test
    void testValidateLoginReturnFalseWithInvalidUsernameAndPassword() {
        //GIVEN

        //WHEN
        boolean result = User.validateLogin(INVALID_USERNAME, INVALID_PASSWORD);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    void testValidateNewUserReturnTrueWithValidCredentials() {

        //GIVEN

        //WHEN
        boolean result = User.validateNewUser(INVALID_USERNAME, INVALID_NAME);
        //THEN
        Assertions.assertTrue(result);
    }
    @Test
    void testValidateNewUserReturnFalseWithValidUsernameAndInvalidName() {
        //GIVEN

        //WHEN
        boolean result = User.validateNewUser(INVALID_USERNAME, VALID_NAME);
        //THEN
        Assertions.assertFalse(result);
    }
    @Test
    void testVValidateNewUserReturnFalseWithInvalidUsernameAndValidName() {
        //GIVEN

        //WHEN
        boolean result = User.validateNewUser(VALID_USERNAME, INVALID_NAME);
        //THEN
        Assertions.assertFalse(result);
    }
    @Test
    void testValidateNewUserReturnFalseWithInvalidUsernameAndName() {
        //GIVEN

        //WHEN
        boolean result = User.validateNewUser(VALID_USERNAME, VALID_NAME);
        //THEN
        Assertions.assertFalse(result);
    }
}
