package hexlet.code.page_object.menu.tasks;

import hexlet.code.page_object.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditTaskPage extends HomePage {

    @FindBy(css = "span[class*='field-id'] > span")
    private WebElement id;

    @FindBy(css = "input[name='title']")
    private WebElement titleInput;

    @FindBy(css = ".ra-input-assignee_id [role='combobox']")
    private WebElement assigneeSelect;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    @FindBy(css = "button[aria-label='Delete']")
    private WebElement deleteButton;

    @FindBy(css = "li[role='option']")
    private List<WebElement> optionsList;

    public EditTaskPage(WebDriver driver) {
        super(driver);
    }

    public String getTaskId() {
        return element.getText(id, "Id");
    }

    public void clickAssignee() {
        element.click(assigneeSelect, "Assignee");
    }

    public void typeTitle(String name) {
        element.type(titleInput, name, "Title");
    }

    public void selectOption(String optionText) {
        element.selectOptionFromList(optionsList, optionText);
    }

    public TasksPage clickSave() {
        element.click(saveButton, "Save");
        return new TasksPage(driver);
    }

    public TasksPage clickDelete() {
        element.click(deleteButton, "Delete");
        return new TasksPage(driver);
    }
}
