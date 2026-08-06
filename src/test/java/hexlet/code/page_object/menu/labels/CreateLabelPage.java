package hexlet.code.page_object.menu.labels;

import hexlet.code.page_object.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@SuppressWarnings("unused")
public class CreateLabelPage extends HomePage {

    @FindBy(css = "input[name='name']")
    private WebElement nameInput;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    public CreateLabelPage(WebDriver driver) {
        super(driver);
    }

    public void typeName(String name) {
        element.type(nameInput, name, "Name");
    }

    public boolean isNameInputVisible() {
        return element.isDisplayed(nameInput, "Name");
    }

    public boolean isSaveButtonVisible() {
        return element.isDisplayed(saveButton, "Save");
    }

    public void clickSave() {
        element.click(saveButton, "Save");
    }
}
