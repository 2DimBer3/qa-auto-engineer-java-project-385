package hexlet.code.page_object.menu.statuses;

import hexlet.code.page_object.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateStatusPage extends HomePage {

    @FindBy(css = "input[name='name']")
    private WebElement nameInput;

    @FindBy(css = "input[name='slug']")
    private WebElement slugInput;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    public CreateStatusPage(WebDriver driver) {
        super(driver);
    }

    public void typeName(String name) {
        element.type(nameInput, name, "Name");
    }

    public void typeSlug(String slug) {
        element.type(slugInput, slug, "Slug");
    }

    public TaskStatusesPage clickSave() {
        element.click(saveButton, "Сохранить");
        return new TaskStatusesPage(driver);
    }

    public boolean isNameInputVisible() {
        return element.isDisplayed(nameInput, "Name");
    }

    public boolean isSlugInputVisible() {
        return element.isDisplayed(slugInput, "Slug");
    }

    public boolean isSaveButtonVisible() {
        return element.isDisplayed(saveButton, "Save");
    }
}
