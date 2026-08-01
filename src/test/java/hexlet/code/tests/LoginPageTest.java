package hexlet.code.tests;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.LoginPage;
import hexlet.code.steps.HomePageSteps;
import hexlet.code.steps.LoginPageSteps;
import org.junit.jupiter.api.Test;

import static hexlet.code.steps.CommonPageSteps.assertPageTitle;

public class LoginPageTest extends BaseTest {

    private final LoginPageSteps loginPageSteps = new LoginPageSteps();
    private final HomePageSteps homePageSteps = new HomePageSteps();

    @Test
    public void testLoginPageTitle() {
        loginPageSteps.openPage();
        assertPageTitle("Task manager");
    }

    @Test
    public void testLoginPageDisplayed() {
        LoginPage loginPage = loginPageSteps.openPage();
        loginPageSteps.assertPageOpen(loginPage);
    }

    @Test
    public void testSuccessfulLogin() {
        HomePage homePage = loginPageSteps.openPageAndLogin(config.userLogin(), config.userPassword());
        homePageSteps.assertPageOpen(homePage);
    }
}
