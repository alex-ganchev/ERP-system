import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class ValidationTest {
    private static final User testUser = new Employee("Тестов служител");
    private static final Client testClient = new Client("Тестов клиент", "Тестов проект", LocalDate.now());
    private static final String VALID_USERNAME = "user";
    private static final String VALID_PASSWORD = "1234";
    private static final String INVALID_USERNAME = "invalidusername";
    private static final String INVALID_PASSWORD = "invalidpassword";
    private static final String FREE_USERNAME = "freeusername";
    private static final String FREE_NAME = "Свободно Име";
    private static final String BUSY_USERNAME = "ppetrov";
    private static final String BUSY_NAME = "Петър Петров";
    private static final String VALID_DATE_FORMAT = "23/12/2023";
    private static final String INVALID_DATE_FORMAT = "2023-12-23";
    private static final String VALID_TIME = "3.5";
    private static final String INVALID_TIME = "стринг";
    private static final String INVALID_DATE_AFTER_NOW = "31/12/2222";
    private static final String INVALID_DATE_BEFORE_NOW = "01/12/2023";


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

    @Test
    void testIsDateFormatValidReturnTrueWhenFormatIsValid() {
        //GIVEN
        //WHEN
        boolean result = Validation.isDateFormatValid(VALID_DATE_FORMAT);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    void testIsDateFormatValidReturnFalseWhenFormatIsInvalid() {
        //GIVEN
        //WHEN
        boolean result = Validation.isDateFormatValid(INVALID_DATE_FORMAT);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    void testIsTimeValidReturnTrueWhenTimeIsValid() {
        //GIVEN
        User.activeUser = testUser;
        //WHEN
        boolean result = Validation.isTimeValid(VALID_TIME, LocalDate.parse(VALID_DATE_FORMAT, AppConstants.DATE_FORMAT));
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    void testIsTimeValidReturnFalseWhenTimeIsInvalid() {
        //GIVEN
        User.activeUser = testUser;
        //WHEN
        boolean result = Validation.isTimeValid(INVALID_TIME, LocalDate.parse(VALID_DATE_FORMAT, AppConstants.DATE_FORMAT));
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    void testIsDateAfterNowReturnTrueWhenDateIsAfter() {
        //GIVEN
        //WHEN
        boolean result = Validation.isDateAfterNow(INVALID_DATE_AFTER_NOW);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    void testIsDateAfterNowReturnFalseWhenDateIsBefore() {
        //GIVEN
        //WHEN
        boolean result = Validation.isDateAfterNow(INVALID_DATE_BEFORE_NOW);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    void testIsDateAfterClientEndDateReturnTrueWhenDateIsAfter() {
        //GIVEN
        //WHEN
        boolean result = Validation.isDateAfterClientEndDate(LocalDate.now().plusDays(1), testClient);
        //THEN
        Assertions.assertTrue(result);

    }

    @Test
    void testIsDateAfterClientEndDateReturnFalseWhenDateIsBefore() {
        //GIVEN
        //WHEN
        boolean result = Validation.isDateAfterClientEndDate(LocalDate.now().minusDays(1), testClient);
        //THEN
        Assertions.assertFalse(result);

    }

}
