package hexlet.code.steps;

import hexlet.code.config.ConfigManager;
import hexlet.code.driver.DriverFactory;
import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageSteps {

    private LoginPage loginPage;

    @Step("Открыть страницу авторизации")
    public void openPage() {
        WebDriver driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.open(ConfigManager.getConfig()
                .baseUrl());
    }

    @Step("Авторизоваться под учётной записью")
    public HomePage performLogin(String login, String password) {
        return loginPage.login(login, password);
    }

    @Step("Открыть страницу авторизации и войти под учётной записью")
    public HomePage openPageAndLogin(String login, String password) {
        openPage();
        return performLogin(login, password);
    }

    @Step("Проверить, что открыта страница авторизации")
    public void assertPageDisplayed() {
        assertTrue(loginPage.isLoginPageDisplayed(), "Страница входа не открыта");
    }


}
