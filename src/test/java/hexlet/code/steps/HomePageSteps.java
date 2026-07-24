package hexlet.code.steps;

import hexlet.code.config.ConfigManager;
import hexlet.code.driver.DriverFactory;
import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageSteps {

    @Step("Открыть главную страницу")
    public static void openHomePage(String url) {
        DriverFactory.getDriver().get(url);
    }

    @Step("Авторизоваться под учётной записью: {login} / {password}")
    public static HomePage performLogin(String login, String password) {
        WebDriver driver = DriverFactory.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(ConfigManager.getConfig()
                .baseUrl());
        return loginPage.login(login, password);
    }

    @Step("Проверить заголовок страницы, ожидается: '{expectedTitle}'")
    public static void assertPageTitle(String expectedTitle) {
        String actualTitle = DriverFactory.getDriver()
                .getTitle();
        assertEquals(expectedTitle, actualTitle, "Заголовок страницы не совпадает");
    }

    @Step("Выйти из системы")
    public static LoginPage logout(HomePage homePage) {
        return homePage.logout();
    }

    @Step("Проверить, что открыта страница входа")
    public static void assertLoginPageDisplayed(LoginPage loginPage) {
        assertTrue(loginPage.isLoginPageDisplayed(), "Страница входа не открыта");
    }
}