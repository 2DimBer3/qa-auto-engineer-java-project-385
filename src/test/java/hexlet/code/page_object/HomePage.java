package hexlet.code.page_object;

import hexlet.code.page_object.menu.labels.LabelsPage;
import hexlet.code.page_object.menu.statuses.TaskStatusesPage;
import hexlet.code.page_object.menu.tasks.TasksPage;
import hexlet.code.page_object.menu.users.UsersPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@SuppressWarnings("unused")
public class HomePage extends BasePage {

    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ACTIVE_MENU_ITEM_CLASS = "RaMenuItemLink-active";

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

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserLoggedIn() {
        return element.isDisplayed(profileButton, "Кнопка профиля");
    }

    public LoginPage logout() {
        element.click(profileButton, "Кнопка профиля");
        element.click(logoutButton, "Кнопка выхода");

        return new LoginPage(driver);
    }

    public UsersPage openMenuUsers() {
        element.click(menuUsersButton, "Пункт меню 'Пользователи'");
        element.attributeContains(menuUsersButton, ATTRIBUTE_CLASS,
                ACTIVE_MENU_ITEM_CLASS, "Пункт меню 'Пользователи'");

        return new UsersPage(driver);
    }

    public TaskStatusesPage openMenuTaskStatuses() {
        element.click(menuTaskStatusesButton, "Пункт меню 'Статусы задач'");
        element.attributeContains(menuTaskStatusesButton, ATTRIBUTE_CLASS,
                ACTIVE_MENU_ITEM_CLASS, "Пункт меню 'Статусы задач'");

        return new TaskStatusesPage(driver);
    }

    public LabelsPage openMenuLabels() {
        element.click(menuLabelsButton, "Пункт меню 'Лейблы'");
        element.attributeContains(menuLabelsButton, ATTRIBUTE_CLASS,
                ACTIVE_MENU_ITEM_CLASS, "Пункт меню 'Лейблы'");

        return new LabelsPage(driver);
    }

    public TasksPage openMenuTasks() {
        element.click(menuTasksButton, "Пункт меню 'Задачи'");
        element.attributeContains(menuTasksButton, ATTRIBUTE_CLASS,
                ACTIVE_MENU_ITEM_CLASS, "Пункт меню 'Задачи'");

        return new TasksPage(driver);
    }
}
