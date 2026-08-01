package hexlet.code.tests;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.LoginPage;
import hexlet.code.steps.HomePageSteps;
import hexlet.code.steps.LoginPageSteps;
import org.junit.jupiter.api.Test;

public class HomePageTest extends BaseTest {
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();
    private final HomePageSteps homePageSteps = new HomePageSteps();

    @Test
    public void testLogout() {
        // Сначала входим
        HomePage homePage = loginPageSteps.openPageAndLogin(config.userLogin(), config.userPassword());
        homePageSteps.assertPageOpen(homePage);

        // Затем выходим
        LoginPage loginPage = homePageSteps.logout();
        loginPageSteps.assertPageOpen(loginPage);
    }
}
