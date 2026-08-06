package hexlet.code.steps;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.LoginPage;
import hexlet.code.page_object.menu.labels.LabelsPage;
import hexlet.code.page_object.menu.statuses.TaskStatusesPage;
import hexlet.code.page_object.menu.users.UsersPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;


public class HomePageSteps {

    private HomePage homePage;

    @Step("Открыть раздел 'Users'")
    public UsersPage openMenuUsers() {
        return homePage.openMenuUsers();
    }

    @Step("Открыть раздел 'Task Statuses'")
    public TaskStatusesPage openMenuTaskStatuses() {
        return homePage.openMenuTaskStatuses();
    }

    @Step("Открыть раздел 'Labels'")
    public LabelsPage openMenuLabels() {
        return homePage.openMenuLabels();
    }

    @Step("Выйти из системы")
    public LoginPage logout() {
        homePage.clickProfile();
        return homePage.clickLogout();
    }

    @Step("Проверить, что открыта главная страница")
    public void assertPageOpen(HomePage homePage) {
        this.homePage = homePage;
        boolean isOpen = homePage.isUserLoggedIn();

        Assertions.assertTrue(isOpen,
                "Главная страницы не открыта");
    }

    @Step("Проверить, что отобразилось оповещение с текстом {alertText}")
    public void assertAlertVisibleWithText(String alertText) {
        boolean isVisible = homePage.isAlertVisible();
        boolean hasText = homePage.hasAlertText(alertText);

        Assertions.assertTrue(isVisible && hasText,
                "Оповещение не отобразилось или текст не соответствует");
    }
}
