package hexlet.code.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "[name='username']")
    private WebElement usernameField;

    @FindBy(css = "[name='password']")
    private WebElement passwordField;

    @FindBy(css = "[class~='RaLoginForm-button']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(String baseUrl) {
        driver.get(baseUrl);
    }

    public boolean isLoginPageDisplayed() {
        return element.isDisplayed(loginButton, "Кнопка входа");
    }

    public void typeUsername(String username) {
        element.type(usernameField, username, "Поле логина");
    }

    public void typePassword(String password) {
        element.type(passwordField, password, "Поле пароля");
    }

    public HomePage clickLogin() {
        element.click(loginButton, "Кнопка входа");
        return new HomePage(driver);
    }
}
