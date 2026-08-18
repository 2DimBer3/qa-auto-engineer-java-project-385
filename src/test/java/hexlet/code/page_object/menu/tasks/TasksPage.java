package hexlet.code.page_object.menu.tasks;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.menu.tasks.components.CardComponent;
import hexlet.code.page_object.menu.tasks.components.ColumnComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TasksPage extends HomePage {

    @FindBy(css = "[id='main-content']")
    private WebElement board;

    @FindBy(css = "a[href='#/tasks/create']")
    private WebElement createTaskButton;

    @FindBy(xpath = "//div[@data-rfd-droppable-id]/..")
    private List<WebElement> columnElements;

    @FindBy(css = "[data-source='assignee_id'] div[role='combobox']")
    private WebElement assigneeFilter;

    @FindBy(css = "[data-source='status_id'] div[role='combobox']")
    private WebElement assigneeStatus;

    @FindBy(css = "[data-source='label_id'] div[role='combobox']")
    private WebElement assigneeLabel;

    @FindBy(css = "li[role='option']")
    private List<WebElement> optionsList;

    @FindBy(xpath = "//div[./div[@data-rfd-droppable-id]]")
    private List<WebElement> columnContainers;

    private List<ColumnComponent> columnComponents;

    public TasksPage(WebDriver driver) {
        super(driver);
        refreshColumnComponents();
    }

    public List<ColumnComponent> getColumnComponents() {
        refreshColumnComponents();
        return columnComponents;
    }

    public CreateTaskPage clickCreateTask() {
        element.click(createTaskButton, "Создать задачу");
        return new CreateTaskPage(driver);
    }

    public void clickAssignee() {
        element.click(assigneeFilter, "Фильтр Assignee");
    }

    public void selectOption(String optionText) {
        element.selectOptionFromList(optionsList, optionText);
        columnComponents.forEach(ColumnComponent::waitForCardsStable);
        refreshColumnComponents();
    }

    public void clickStatus() {
        element.click(assigneeStatus, "Фильтр Status");
    }

    public void clickLabel() {
        element.click(assigneeLabel, "Фильтр Label");
    }

    public boolean isBoardVisible() {
        return element.isDisplayed(board, "Доска задач");
    }

    public ColumnComponent getColumnByTitle(String title) {
        element.waitForElementsStable(columnContainers, "Колонки");
        for (WebElement container : columnContainers) {
            ColumnComponent tempColumn = new ColumnComponent(container, element);
            if (tempColumn.getHeaderText().equals(title)) {
                return tempColumn;
            }
        }
        throw new RuntimeException("Колонка с заголовком '" + title + "' не найдена");
    }

    public CardComponent getCardByTitle(String title) {
        return getColumnComponents().stream()
                .flatMap(column -> column.getCardComponents().stream())
                .filter(card -> card.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Карточка с заголовком '" + title + "' не найдена"));
    }

    private void refreshColumnComponents() {
        columnComponents = columnElements.stream()
                .map(colElement -> new ColumnComponent(colElement, element))
                .toList();
    }
}
