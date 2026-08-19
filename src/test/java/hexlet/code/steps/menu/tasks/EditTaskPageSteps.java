package hexlet.code.steps.menu.tasks;

import hexlet.code.config.ConfigManager;
import hexlet.code.page_object.menu.tasks.EditTaskPage;
import hexlet.code.page_object.menu.tasks.TasksPage;
import hexlet.code.steps.HomePageSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class EditTaskPageSteps extends HomePageSteps {

    private EditTaskPage editTaskPage;

    @Step("Заполнить значением поле `Assignee`")
    public void fillAssignee(String value) {
        editTaskPage.clickAssignee();
        editTaskPage.selectOption(value);
    }

    @Step("Заполнить значением поле `Title`")
    public void fillTitle(String value) {
        editTaskPage.typeTitle(value);
    }

    @Step("Нажать на кнопку `Save`")
    public TasksPage clickSave() {
        return editTaskPage.clickSave();
    }

    @Step("Удалить задачу")
    public TasksPage deleteTask() {
        return editTaskPage.clickDelete();
    }

    @Step("Проверить, что страница редактирования задачи открыта.")
    public void assertEditTaskPageOpen(EditTaskPage editTaskPage) {
        this.editTaskPage = editTaskPage;
        String taskId = editTaskPage.getTaskId();

        String expectedUrl = ConfigManager.getConfig()
                .taskEditEndpoint()
                .replace("{int}", String.valueOf(taskId));

        boolean isOpen = editTaskPage.getPageUrl()
                .contains(expectedUrl);

        Assertions.assertTrue(isOpen, "Страница редактирования задачи не открыта");
    }
}
