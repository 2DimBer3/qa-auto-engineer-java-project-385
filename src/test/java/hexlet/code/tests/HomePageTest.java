package hexlet.code.tests;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.LoginPage;
import hexlet.code.steps.LoginPageSteps;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends BaseTest {
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();

    @Test
    public void testLogout() {
        // Сначала входим
        HomePage homePage = loginPageSteps.performLogin(config.userLogin(), config.userPassword());
        LOG.debug("Авторизация прошла успешно");

        // Затем выходим
        LoginPage returnedLoginPage = homePage.logout();

        assertTrue(returnedLoginPage.isLoginPageDisplayed(), "Страница входа не открыта");
    }
}
