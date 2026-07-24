package hexlet.code.tests;

import hexlet.code.page_object.HomePage;
import hexlet.code.steps.LoginPageSteps;
import org.junit.jupiter.api.Test;

import static hexlet.code.steps.CommonPageSteps.assertPageTitle;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest extends BaseTest {

    private final LoginPageSteps loginPageSteps = new LoginPageSteps();

    @Test
    public void testLoginPageTitle() {
        loginPageSteps.openPage();
        assertPageTitle("Task manager");
    }

    @Test
    public void testLoginPageDisplayed() {
        loginPageSteps.openPage();
        loginPageSteps.assertPageDisplayed();
    }

    @Test
    public void testSuccessfulLogin() {
        HomePage homePage = loginPageSteps.openPageAndLogin(config.userLogin(), config.userPassword());
        assertTrue(homePage.isUserLoggedIn(), "Пользователь не находится на главной странице");
    }
}
