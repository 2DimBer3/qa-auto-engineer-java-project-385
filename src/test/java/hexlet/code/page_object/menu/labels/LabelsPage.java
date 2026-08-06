package hexlet.code.page_object.menu.labels;

import hexlet.code.page_object.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LabelsPage extends HomePage {

    @FindBy(css = "[class~='RaDatagrid-table']")
    private WebElement labelsTable;

    @FindBy(css = "[class~='MuiTableRow-head']")
    private WebElement tableHead;

    @FindBy(css = "[class~='RaDatagrid-tbody']")
    private WebElement tableBody;

    @FindBy(css = "[class~='RaDatagrid-selectable']")
    private List<WebElement> tableRows;

    @FindBy(css = "th[class~='column-name']")
    private WebElement nameColumn;

    @FindBy(css = "th[class~='column-id']")
    private WebElement idColumn;

    @FindBy(css = "th[class~='column-createdAt']")
    private WebElement createdAtColumn;

    @FindBy(css = "td.column-name")
    private List<WebElement> nameCells;

    @FindBy(css = "[href*='/labels/create']")
    private WebElement createLabelButton;

    @FindBy(css = "[aria-label='Select this row']")
    private List<WebElement> rowCheckbox;

    @FindBy(css = "[aria-label='Delete']")
    private WebElement deleteButton;

    public LabelsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLabelTableVisible() {
        return element.isDisplayed(labelsTable, "Таблица лейблов");
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

    public boolean isIdColumnVisible() {
        return element.isDisplayed(idColumn, "Колонка Id");
    }

    public boolean isCreatedAtColumnVisible() {
        return element.isDisplayed(createdAtColumn, "Колонка Created at");
    }

    public int getLabelsCount() {
        isLabelTableVisible();
        return tableRows.size();
    }

    public String getNameCellText(int numberRow) {
        WebElement nameElement = nameCells.get(numberRow);
        return element.getText(nameElement, "Ячейка Name");
    }

    public CreateLabelPage clickCreateLabel() {
        element.click(createLabelButton, "Создать лейбл");
        return new CreateLabelPage(driver);
    }

    public CreateLabelPage clickLabel(int numberRow) {
        element.click(tableRows, numberRow - 1, "Лейбл");
        return new CreateLabelPage(driver);
    }

    public void clickRowCheckBox(int numberUserRow) {
        element.click(rowCheckbox, numberUserRow - 1, "Чек-бокс строки");
    }

    public void clickDelete() {
        element.click(deleteButton, "Удалить");
    }
}
