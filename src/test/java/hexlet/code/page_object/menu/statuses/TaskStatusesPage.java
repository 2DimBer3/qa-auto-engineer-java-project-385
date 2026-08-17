package hexlet.code.page_object.menu.statuses;

import hexlet.code.page_object.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TaskStatusesPage extends HomePage {

    @FindBy(css = "[class~='RaDatagrid-table']")
    private WebElement statusesTable;

    @FindBy(css = "[class~='MuiTableRow-head']")
    private WebElement tableHead;

    @FindBy(css = "[class~='RaDatagrid-tbody']")
    private WebElement tableBody;

    @FindBy(css = "[class~='RaDatagrid-selectable']")
    private List<WebElement> tableRows;

    @FindBy(css = "th[class~='column-name']")
    private WebElement nameColumn;

    @FindBy(css = "th[class~='column-slug']")
    private WebElement slugColumn;

    @FindBy(css = "td.column-name")
    private List<WebElement> nameCells;

    @FindBy(css = "td.column-slug")
    private List<WebElement> slugCells;

    @FindBy(css = "[aria-label='Select this row']")
    private List<WebElement> rowCheckbox;

    @FindBy(css = "[href*='/task_statuses/create']")
    private WebElement createStatusButton;

    @FindBy(css = "[aria-label='Select all']")
    private WebElement headCheckbox;

    @FindBy(css = "[aria-label='Delete']")
    private WebElement deleteButton;

    @FindBy(css = ".RaList-noResults")
    private WebElement emptyResultsBlock;

    public TaskStatusesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isStatusTableVisible() {
        return element.isDisplayed(statusesTable, "Таблица статусов");
    }

    public boolean isTableHeadVisible() {
        return element.isDisplayed(tableHead, "Шапка таблицы");
    }

    public boolean isTableBodyVisible() {
        return element.isDisplayed(tableBody, "Тело таблицы");
    }

    public boolean isNameColumnVisible() {
        return element.isDisplayed(nameColumn, "Колонка Name");
    }

    public boolean isSlugColumnVisible() {
        return element.isDisplayed(slugColumn, "Колонка Slug");
    }

    public boolean hasEmptyResultBlockText(String text) {
        return element.containsText(emptyResultsBlock, text, "Пустой результирующий блок");
    }

    public int getStatusesCount() {
        isStatusTableVisible();
        return tableRows.size();
    }

    public String getNameCellText(int numberRow) {
        WebElement nameElement = nameCells.get(numberRow);
        return element.getText(nameElement, "Ячейка Name");
    }

    public String getSlugCellText(int numberRow) {
        WebElement slugElement = slugCells.get(numberRow);
        return element.getText(slugElement, "Ячейка Slug");
    }

    public CreateStatusPage clickCreateStatus() {
        element.click(createStatusButton, "Создать статус");
        return new CreateStatusPage(driver);
    }

    public CreateStatusPage clickStatus(int numberUserRow) {
        element.click(tableRows, numberUserRow - 1, "Статус");
        return new CreateStatusPage(driver);
    }

    public void clickRowCheckBox(int numberUserRow) {
        element.click(rowCheckbox, numberUserRow - 1, "Чек-бокс строки");
    }

    public void clickDelete() {
        element.click(deleteButton, "Удалить");
    }

    public void clickHeadCheckBox() {
        element.click(headCheckbox, "Чек-бокс шапки таблицы");
    }
}
