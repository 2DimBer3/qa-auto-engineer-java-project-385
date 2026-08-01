package hexlet.code.page_object.menu.users;

import hexlet.code.page_object.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@SuppressWarnings("unused")
public class UserCreatePage extends HomePage {

    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ALERT = ".MuiSnackbarContent-message";

    @FindBy(css = "[name='email']")
    private WebElement emailInput;

    @FindBy(css = "[name='firstName']")
    private WebElement firstNameInput;

    @FindBy(css = "[name='lastName']")
    private WebElement lastNameInput;

    @FindBy(css = "[aria-label='Save']")
    private WebElement saveButton;

    @FindBy(css = "p.Mui-error")
    private WebElement errorBlock;

    public UserCreatePage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmailInputVisible() {
        return element.isDisplayed(emailInput, "Email");
    }

    public boolean isFirstNameInputVisible() {
        return element.isDisplayed(firstNameInput, "First name");
    }

    public boolean isLastNameInputVisible() {
        return element.isDisplayed(lastNameInput, "Last name");
    }

    public boolean isSaveButtonVisible() {
        return element.isDisplayed(saveButton, "Save");
    }

    public void typeEmail(String email) {
        element.type(saveButton, email, "Email");
    }

    public void typeFirstName(String firstName) {
        element.type(firstNameInput, firstName, "First name");
    }

    public void typeLastName(String lastName) {
        element.type(lastNameInput, lastName, "Last name");
    }

    public void clickSave() {
        element.click(saveButton, "Save");
    }

    public String getEmailValue() {
        return element.getValue(emailInput, "Email");
    }

    public String getFirstNameValue() {
        return element.getValue(firstNameInput, "First name");
    }

    public String getLastNameValue() {
        return element.getValue(lastNameInput, "Last name");
    }

    public boolean isErrorBlockVisible() {
        return element.isDisplayed(errorBlock, "Блок с ошибкой");
    }

    public boolean hasErrorBlockText(String expectedText) {
        return element.hasText(errorBlock, expectedText, "Блок с ошибкой");
    }
}
