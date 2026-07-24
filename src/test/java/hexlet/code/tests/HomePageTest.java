package hexlet.code.tests;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.LoginPage;
import org.junit.jupiter.api.Test;

import static hexlet.code.steps.HomePageSteps.assertPageTitle;
import static hexlet.code.steps.HomePageSteps.openHomePage;
import static hexlet.code.steps.HomePageSteps.performLogin;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends BaseTest {

    @Test
    public void testHomePageTitle() {
        openHomePage(config.baseUrl());
        assertPageTitle("Task manager");
    }

    @Test
    public void testLogout() {
        // Сначала входим
        HomePage homePage = performLogin(config.userLogin(), config.userPassword());
        LOG.debug("Авторизация прошла успешно");

        // Затем выходим
        LoginPage returnedLoginPage = homePage.logout();

        assertTrue(returnedLoginPage.isLoginPageDisplayed(), "Страница входа не открыта");
    }
}
