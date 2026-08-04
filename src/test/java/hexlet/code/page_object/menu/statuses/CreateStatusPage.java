package hexlet.code.page_object.menu.statuses;

import hexlet.code.page_object.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public void clickSave() {
        element.click(saveButton, "Сохранить");
    }

    @Deprecated
    public void verifyFormElementsVisible() {
        checkVisibility(nameInput, "Name");
        checkVisibility(slugInput, "Slug");
        checkVisibility(saveButton, "Save");
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

    @Deprecated
    public void verifySuccessCreateMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ALERT)));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(ALERT), "Element created"));
    }

    @Deprecated
    public void verifySuccessEditMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ALERT)));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(ALERT), "Element updated"));
    }

    @Deprecated
    public TaskStatusesPage createStatusAndGoToList(String name, String slug) {
        typeName(name);
        typeSlug(slug);
        clickSave();
        verifySuccessCreateMessage();
        return openMenuTaskStatuses();
    }

    @Deprecated
    public TaskStatusesPage editStatusAndGoToList(String newName, String newSlug) {
        typeName(newName);
        typeSlug(newSlug);
        clickSave();
        verifySuccessEditMessage();
        return new TaskStatusesPage(driver);
    }
}
