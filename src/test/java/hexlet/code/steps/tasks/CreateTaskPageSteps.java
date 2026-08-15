package hexlet.code.steps.tasks;

import hexlet.code.config.ConfigManager;
import hexlet.code.page_object.menu.tasks.CreateTaskPage;
import hexlet.code.page_object.menu.tasks.TasksPage;
import hexlet.code.steps.HomePageSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class CreateTaskPageSteps extends HomePageSteps {

    private CreateTaskPage createTaskPage;

    @Step("Заполнить значением поле `Assignee`")
    public void fillAssignee(String value) {
        createTaskPage.clickAssignee();
        createTaskPage.selectOption(value);
    }

    @Step("Заполнить значением поле `Title`")
    public void fillTitle(String value) {
        createTaskPage.typeTitle(value);
    }

    @Step("Заполнить значением поле `Status`")
    public void fillStatus(String value) {
        createTaskPage.clickStatus();
        createTaskPage.selectOption(value);
    }

    @Step("Заполнить обязательные поля формы создания задач и сохранить")
    public TasksPage fillRequireFormFieldsAndSave(String assignee, String title, String status) {
        fillAssignee(assignee);
        fillTitle(title);
        fillStatus(status);
        return clickSave();
    }

    @Step("Нажать на кнопку `Save`")
    public TasksPage clickSave() {
        createTaskPage.clickSave();
        return openMenuTasks();
    }

    @Step("Проверить, что страница создания задачи открыта.")
    public void assertCreateTaskPageOpen(CreateTaskPage createTaskPage) {
        this.createTaskPage = createTaskPage;
        boolean isOpen = createTaskPage.getPageUrl()
                .contains(ConfigManager.getConfig()
                        .taskCreateEndpoint());

        Assertions.assertTrue(isOpen, "Страница создания задачи не открыта");
    }

    @Step("Проверить, что отображаются все элементы формы для создания задачи.")
    public void assertCreateTaskFormElementsVisible() {
        boolean isVisible = createTaskPage.isAssigneeSelectVisible()
                && createTaskPage.isTitleInputVisible()
                && createTaskPage.isContentTextareaVisible()
                && createTaskPage.isStatusSelectVisible()
                && createTaskPage.isLabelSelectVisible()
                && createTaskPage.isSaveButtonVisible();

        Assertions.assertTrue(isVisible, "Не отображаются все элементы формы для создания задачи");
    }
}
