package hexlet.code.steps;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.LoginPage;
import hexlet.code.page_object.menu.labels.LabelsPage;
import hexlet.code.page_object.menu.statuses.TaskStatusesPage;
import hexlet.code.page_object.menu.tasks.TasksPage;
import hexlet.code.page_object.menu.users.UsersPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;


public class HomePageSteps {

    private HomePage homePage;

    @Step("Открыть раздел 'Users'")
    public UsersPage openMenuUsers() {
        return homePage.clickMenuUsers();
    }

    @Step("Открыть раздел 'Task Statuses'")
    public TaskStatusesPage openMenuTaskStatuses() {
        return homePage.clickMenuTaskStatuses();
    }

    @Step("Открыть раздел 'Labels'")
    public LabelsPage openMenuLabels() {
        return homePage.clickMenuLabels();
    }

    @Step("Открыть раздел 'Tasks'")
    public TasksPage openMenuTasks() {
        return homePage.clickMenuTasks();
    }

    @Step("Выйти из системы")
    public LoginPage logout() {
        homePage.clickProfile();
        return homePage.clickLogout();
    }

    @Step("Проверить, что открыта главная страница")
    public void assertPageOpen(HomePage localHomePage) {
        homePage = localHomePage;
        boolean isOpen = homePage.isProfileButtonVisible();

        Assertions.assertTrue(isOpen,
                "Главная страницы не открыта");
    }

    @Step("Проверить, что отобразилось оповещение с текстом '{alertText}'")
    public void assertAlertVisibleWithText(String alertText) {
        boolean isVisible = homePage.isAlertVisible();
        boolean hasText = homePage.hasAlertText(alertText);

        Assertions.assertTrue(isVisible,
                "Оповещение не отобразилось");
        Assertions.assertTrue(hasText,
                "В оповещении текст не соответствует ожидаемому");
    }
}
