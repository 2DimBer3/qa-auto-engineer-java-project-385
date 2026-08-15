package hexlet.code.page_object.menu.tasks;

import hexlet.code.page_object.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateTaskPage extends HomePage {

    @FindBy(css = "input[name='title']")
    private WebElement titleInput;

    @FindBy(css = "textarea[name='content']")
    private WebElement contentTextarea;

    @FindBy(css = ".ra-input-assignee_id [role='combobox']")
    private WebElement assigneeSelect;

    @FindBy(css = ".ra-input-status_id [role='combobox']")
    private WebElement statusSelect;

    @FindBy(css = ".ra-input-label_id [role='combobox']")
    private WebElement labelSelect;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    @FindBy(css = "li[role='option']")
    private List<WebElement> optionsList;

    public CreateTaskPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAssigneeSelectVisible() {
        return element.isDisplayed(assigneeSelect, "Assignee");
    }

    public boolean isTitleInputVisible() {
        return element.isDisplayed(titleInput, "Title");
    }

    public boolean isContentTextareaVisible() {
        return element.isDisplayed(contentTextarea, "Content");
    }

    public boolean isStatusSelectVisible() {
        return element.isDisplayed(statusSelect, "Status");
    }

    public boolean isLabelSelectVisible() {
        return element.isDisplayed(labelSelect, "Label");
    }

    public boolean isSaveButtonVisible() {
        return element.isDisplayed(saveButton, "Save");
    }

    public void clickAssignee() {
        element.click(assigneeSelect, "Assignee");
    }

    public void typeTitle(String name) {
        element.type(titleInput, name, "Title");
    }

    public void clickStatus() {
        element.click(statusSelect, "Status");
    }

    public void selectOption(String optionText) {
        element.selectOptionFromList(optionsList, optionText);
    }

    public void clickSave() {
        element.click(saveButton, "Save");
    }
}
