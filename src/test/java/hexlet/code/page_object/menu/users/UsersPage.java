package hexlet.code.page_object.menu.users;

import hexlet.code.page_object.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UsersPage extends HomePage {

    private static final String TABLE_ROWS_CSS = "[class~='RaDatagrid-selectable']";
    private static final String ROW_CHECKBOX_CSS = "[aria-label='Select this row']";

    private static final String EMAIL_CELL_CSS = "td.column-email";
    private static final String FIRST_NAME_CELL_CSS = "td.column-firstName";
    private static final String LAST_NAME_CELL_CSS = "td.column-lastName";

    @FindBy(css = "[class~='RaDatagrid-table']")
    private WebElement usersTable;

    @FindBy(css = "[class~='MuiTableRow-head']")
    private WebElement tableHead;

    @FindBy(css = "[class~='RaDatagrid-tbody']")
    private WebElement tableBody;

    @FindBy(css = TABLE_ROWS_CSS)
    private List<WebElement> tableRows;

    @FindBy(css = "th[class~='column-email']")
    private WebElement emailColumn;

    @FindBy(css = "th[class~='column-firstName']")
    private WebElement firstNameColumn;

    @FindBy(css = "th[class~='column-lastName']")
    private WebElement lastNameColumn;

    @FindBy(css = "[href*='/users/create']")
    private WebElement createUserButton;

    @FindBy(css = ".select-all")
    private WebElement headCheckbox;

    @FindBy(css = "[aria-label='Delete']")
    private WebElement deleteButton;

    @FindBy(css = ".RaList-noResults")
    private WebElement emptyResultsBlock;

    @FindBy(css = ROW_CHECKBOX_CSS)
    private List<WebElement> rowCheckbox;

    @FindBy(css = "td.column-email")
    private List<WebElement> emailCells;

    @FindBy(css = "td.column-firstName")
    private List<WebElement> firstNameCells;

    @FindBy(css = "td.column-lastName")
    private List<WebElement> lastNameCells;

    public UsersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserTableVisible() {
        return element.isDisplayed(usersTable, "Таблица пользователей");
    }

    public boolean isTableHeadVisible() {
        return element.isDisplayed(tableHead, "Шапка таблицы");
    }

    public boolean isTableBodyVisible() {
        return element.isDisplayed(tableBody, "Тело таблицы");
    }

    public boolean isEmailColumnVisible() {
        return element.isDisplayed(emailColumn, "Колонка Email");
    }

    public boolean isFirstNameColumnVisible() {
        return element.isDisplayed(firstNameColumn, "Колонка имён");
    }

    public boolean isLastNameColumnVisible() {
        return element.isDisplayed(lastNameColumn, "Колонка фамилий");
    }

    public boolean hasEmptyResultBlockText(String text) {
        return element.hasText(emptyResultsBlock, text, "Пустой результирующий блок");
    }

    public int getUsersCount() {
        isUserTableVisible();
        return tableRows.size();
    }

    public String getEmailCellText(int numberRow) {
        WebElement emailElement = emailCells.get(numberRow);
        return element.getText(emailElement, "Ячейка Email");
    }

    public String getFirstNameCellText(int numberRow) {
        WebElement firstNameElement = firstNameCells.get(numberRow);
        return element.getText(firstNameElement, "Ячейка First Name");
    }

    public String getLastNameCellText(int numberRow) {
        WebElement lastNameElement = lastNameCells.get(numberRow);
        return element.getText(lastNameElement, "Ячейка Last Name");
    }

    public UserCreatePage clickCreateUser() {
        element.click(createUserButton, "Создать пользователя");
        return new UserCreatePage(driver);
    }

    public UserCreatePage clickUser(int numberUserRow) {
        element.click(tableRows, numberUserRow - 1, "Пользователь");
        return new UserCreatePage(driver);
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
