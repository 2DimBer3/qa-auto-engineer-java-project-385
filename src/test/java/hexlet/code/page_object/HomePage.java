package hexlet.code.page_object;

import hexlet.code.page_object.menu.labels.LabelsPage;
import hexlet.code.page_object.menu.statuses.TaskStatusesPage;
import hexlet.code.page_object.menu.tasks.TasksPage;
import hexlet.code.page_object.menu.users.UsersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "[class~='RaUserMenu-userButton']")
    private WebElement profileButton;

    @FindBy(css = "[class~='logout']")
    private WebElement logoutButton;

    @FindBy(css = "[href$='users']")
    private WebElement menuUsersButton;

    @FindBy(css = "[href$='task_statuses']")
    private WebElement menuTaskStatusesButton;

    @FindBy(css = "[href$='labels']")
    private WebElement menuLabelsButton;

    @FindBy(css = "[href$='tasks']")
    private WebElement menuTasksButton;

    @FindBy(css = ".MuiSnackbarContent-message")
    protected WebElement alert;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isAlertVisible() {
        return element.isDisplayed(alert, "Оповещение");
    }

    public boolean hasAlertText(String text) {
        return element.hasText(alert, text, "Оповещение");
    }

    public boolean isProfileButtonVisible() {
        return element.isDisplayed(profileButton, "Кнопка профиля");
    }

    public String getPageUrl() {
        return element.getPageUrl();
    }

    public void clickProfile() {
        element.click(profileButton, "Кнопка профиля");
    }

    public LoginPage clickLogout() {
        element.click(logoutButton, "Кнопка выхода");
        return new LoginPage(driver);
    }

    public UsersPage clickMenuUsers() {
        element.click(menuUsersButton, "Пункт меню 'Пользователи'");
        return new UsersPage(driver);
    }

    public TaskStatusesPage clickMenuTaskStatuses() {
        element.click(menuTaskStatusesButton, "Пункт меню 'Статусы задач'");
        return new TaskStatusesPage(driver);
    }

    public LabelsPage clickMenuLabels() {
        element.click(menuLabelsButton, "Пункт меню 'Лейблы'");
        return new LabelsPage(driver);
    }

    public TasksPage clickMenuTasks() {
        element.click(menuTasksButton, "Пункт меню 'Задачи'");
        return new TasksPage(driver);
    }
}
