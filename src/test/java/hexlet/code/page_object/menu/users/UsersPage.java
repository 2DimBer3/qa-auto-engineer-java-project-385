package hexlet.code.page_object.menu.users;

import hexlet.code.page_object.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class UsersPage extends HomePage {

    private static final String TABLE_ROWS_CSS = "[class~='RaDatagrid-selectable']";
    private static final String ROW_CHECKBOX_CSS = "[aria-label='Select this row']";

    private static final String EMAIL_CELL_CSS = "td.column-email";
    private static final String FIRST_NAME_CELL_CSS = "td.column-firstName";
    private static final String LAST_NAME_CELL_CSS = "td.column-lastName";

    private static final String ALERT_CSS = ".MuiSnackbarContent-message";

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

    @FindBy(css = ALERT_CSS)
    private WebElement alert;

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
        return element.isDisplayed(usersTable, "Таблица пользователей")
                && element.isDisplayed(tableHead, "Шапка таблицы")
                && element.isDisplayed(tableBody, "Тело таблицы");
    }

    @Deprecated
    public boolean isRequiredColumnsVisible() {
        return element.isDisplayed(emailColumn, "Колонка Email")
                && element.isDisplayed(firstNameColumn, "Колонка имён")
                && element.isDisplayed(lastNameColumn, "Колонка фамилий");
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

    @Deprecated
    public boolean verifySuccessRowDeleteMessage() {
        isAlertVisible();
        return element.hasText(alert, "Element deleted", "Оповещение");
    }

    public boolean isAlertVisible() {
        return element.isDisplayed(alert, "Оповещение");
    }

    public boolean hasAlertText(String text) {
        return element.hasText(alert, text, "Оповещение");
    }

    @Deprecated
    public void verifySuccessSomeDeleteMessage(int numberRows) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ALERT_CSS)));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(ALERT_CSS), numberRows + " elements deleted"));
    }

    @Deprecated
    public void verifySuccessAllUsersDelete(int numberRows) {
        verifySuccessSomeDeleteMessage(numberRows);

        checkVisibility(emptyResultsBlock, "noResultsBlock");
        wait.until(ExpectedConditions.textToBePresentInElement(emptyResultsBlock, "No Users yet."));
        wait.until(ExpectedConditions.textToBePresentInElement(emptyResultsBlock, "Do you want to add one?"));
    }

    public boolean isEmptyResultBlockVisible() {
        return element.isDisplayed(emptyResultsBlock, "Пустой результирующий блок");
    }

    public boolean hasEmptyResultBlockText(String text) {
        return element.hasText(emptyResultsBlock, text, "Пустой результирующий блок");
    }

    public int getUsersCount() {
        isUserTableVisible();
        return tableRows.size();
    }

    @Deprecated
    public boolean isUserExist(String email, String firstName, String lastName) {
        List<WebElement> rows = element.findElements(By.cssSelector(TABLE_ROWS_CSS),
                "Таблица пользователей (строки)");
        for (WebElement row : rows) {
            String actualEmail = getCellText(row, EMAIL_CELL_CSS);
            String actualFirstName = getCellText(row, FIRST_NAME_CELL_CSS);
            String actualLastName = getCellText(row, LAST_NAME_CELL_CSS);

            if (email.equals(actualEmail) && firstName.equals(actualFirstName) && lastName.equals(actualLastName)) {
                return true;
            }
        }

        return false;
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

    @Deprecated
    public UserFormPage openCreateUserForm() {
        wait.until(ExpectedConditions.elementToBeClickable(createUserButton))
                .click();
        wait.until(ExpectedConditions.invisibilityOf(usersTable));
        return new UserFormPage(driver);
    }

    public UserFormPage clickCreateUser() {
        element.click(createUserButton, "Создать пользователя");
        return new UserFormPage(driver);
    }

    public boolean isUserTableHidden() {
        return element.isHidden(usersTable, "Таблица пользователей")
                && element.isHidden(tableHead, "Шапка таблицы")
                && element.isHidden(tableBody, "Тело таблицы");
    }

    @Deprecated
    public UserFormPage openLastUser() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(TABLE_ROWS_CSS), 0));

        WebElement lastRow = tableRows.getLast();
        wait.until(ExpectedConditions.elementToBeClickable(lastRow))
                .click();
        return new UserFormPage(driver);
    }

    public UserFormPage clickUser(int numberUserRow) {
        element.click(tableRows, numberUserRow - 1, "Пользователь");
        return new UserFormPage(driver);
    }

    @Deprecated
    public void deleteLastUser() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(TABLE_ROWS_CSS), 0));

        WebElement lastRow = tableRows.getLast();
        WebElement checkbox = lastRow.findElement(By.cssSelector(rowCheckboxCss));

        clickElementSafely(checkbox);
        clickElementSafely(deleteButton);
    }

    public void clickRowCheckBox(int numberUserRow) {
        element.click(rowCheckbox, numberUserRow - 1, "Чек-бокс строки");
    }

    public void clickDelete() {
        element.click(deleteButton, "Удалить");
    }

    @Deprecated
    public void deleteAllUsers() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(TABLE_ROWS_CSS), 0));

        clickElementSafely(headCheckbox);
        clickElementSafely(deleteButton);
    }

    public void clickHeadCheckBox(int numberUserRow) {
        element.click(headCheckbox, "Чек-бокс шапки таблицы");
    }
}
