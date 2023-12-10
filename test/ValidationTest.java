import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ValidationTest {
    ArrayList<User> users = FileHandler.readUsers();
    private static final String VALID_USERNAME = "user";
    private static final String VALID_PASSWORD = "1234";
    private static final String VALID_NAME = "Иван Иванов";
    private static final String INVALID_USERNAME = "invalidusername";
    private static final String INVALID_PASSWORD = "invalidpassword";
    private static final String INVALID_NAME = "invalidname";
    private static final String FREE_USERNAME = "freeusername";
    private static final String FREE_NAME = "Свободно Име";
    private static final String BUSY_USERNAME = "ppetrov";
    private static final String BUSY_NAME = "Петър Петров";


    @BeforeEach
    public void setUp() {

    }

    @Test
    void testValidateLoginReturnTrueWithValidCredentials() {
        //GIVEN

        //WHEN
        boolean result = Validation.validateLogin(VALID_USERNAME, VALID_PASSWORD);
        //THEN
        Assertions.assertTrue(result);
    }
    @Test
    void testValidateLoginReturnFalseWithValidUsernameAndInvalidPassword() {
        //GIVEN

        //WHEN
        boolean result = Validation.validateLogin(VALID_USERNAME, INVALID_PASSWORD);
        //THEN
        Assertions.assertFalse(result);
    }
    @Test
    void testValidateLoginReturnFalseWithInvalidUsernameAndValidPassword() {
        //GIVEN

        //WHEN
        boolean result = Validation.validateLogin(INVALID_USERNAME, VALID_PASSWORD);
        //THEN
        Assertions.assertFalse(result);
    }
    @Test
    void testValidateLoginReturnFalseWithInvalidUsernameAndPassword() {
        //GIVEN

        //WHEN
        boolean result = Validation.validateLogin(INVALID_USERNAME, INVALID_PASSWORD);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    void testValidateNewUserReturnTrueWithFreeUsernameAndName() {

        //GIVEN

        //WHEN
        boolean result = Validation.validateNewUser(FREE_USERNAME, FREE_NAME);
        //THEN
        Assertions.assertTrue(result);
    }
    @Test
    void testValidateNewUserReturnFalseWithBusyUsernameAndFreeName() {
        //GIVEN

        //WHEN
        boolean result = Validation.validateNewUser(BUSY_USERNAME, FREE_NAME);
        //THEN
        Assertions.assertFalse(result);
    }
    @Test
    void testValidateNewUserReturnFalseWithFreeUsernameAndBusyName() {
        //GIVEN

        //WHEN
        boolean result = Validation.validateNewUser(FREE_USERNAME, BUSY_NAME);
        //THEN
        Assertions.assertFalse(result);
    }
    @Test
    void testValidateNewUserReturnFalseWithBusyUsernameAndName() {
        //GIVEN

        //WHEN
        boolean result = Validation.validateNewUser(BUSY_USERNAME, BUSY_NAME);
        //THEN
        Assertions.assertFalse(result);
    }
}
