package hexlet.code.steps.statuses;

import hexlet.code.config.ConfigManager;
import hexlet.code.page_object.menu.statuses.CreateStatusPage;
import hexlet.code.page_object.menu.statuses.TaskStatusesPage;
import hexlet.code.steps.HomePageSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class CreateStatusPageSteps extends HomePageSteps {

    private CreateStatusPage createStatusPage;

    @Step("Ввести значение в поле `Name`")
    public void fillName(String value) {
        createStatusPage.typeName(value);
    }

    @Step("Ввести значение в поле `Slug`")
    public void fillSlug(String value) {
        createStatusPage.typeSlug(value);
    }

    @Step("Заполнить форму создания статуса задач и сохранить")
    public TaskStatusesPage fillFormAndSave(String name, String slug) {
        fillName(name);
        fillSlug(slug);
        return clickSave();
    }

    @Step("Нажать на кнопку `Save`")
    public TaskStatusesPage clickSave() {
        createStatusPage.clickSave();
        return openMenuTaskStatuses();
    }

    @Step("Проверить, что страница создания статуса открыта.")
    public void assertCreateStatusPageOpen(CreateStatusPage createStatusPage) {
        this.createStatusPage = createStatusPage;
        boolean isOpen = createStatusPage.getPageUrl()
                .contains(ConfigManager.getConfig()
                        .statusCreateEndpoint());

        Assertions.assertTrue(isOpen, "Страница создания статуса не открыта");
    }

    @Step("Проверить, что отображаются все элементы формы для создания статуса задач.")
    public void assertCreateStatusFormElementsVisible() {
        boolean isVisible = createStatusPage.isNameInputVisible()
                && createStatusPage.isSlugInputVisible()
                && createStatusPage.isSaveButtonVisible();

        Assertions.assertTrue(isVisible, "Не отображаются все элементы формы для создания статуса задач");
    }
}
