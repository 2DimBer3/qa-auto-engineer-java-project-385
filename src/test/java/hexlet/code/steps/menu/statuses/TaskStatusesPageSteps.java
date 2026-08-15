package hexlet.code.steps.menu.statuses;

import hexlet.code.page_object.menu.statuses.CreateStatusPage;
import hexlet.code.page_object.menu.statuses.TaskStatusesPage;
import hexlet.code.steps.HomePageSteps;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class TaskStatusesPageSteps extends HomePageSteps {

    private TaskStatusesPage taskStatusesPage;

    @Step("Посчитать количество статусов в таблице")
    public int countNumberStatuses() {
        int statusesCount = taskStatusesPage.getStatusesCount();
        Allure.addAttachment("Количество статусов", String.valueOf(statusesCount));
        return statusesCount;
    }

    @Step("Открыть форму создания статуса")
    public CreateStatusPage openCreateUserPage() {
        return taskStatusesPage.clickCreateStatus();
    }

    @Step("Открыть форму редактирования статуса со строки №{userNumber}")
    public CreateStatusPage openEditStatusForm(int rowNumber) {
        return taskStatusesPage.clickStatus(rowNumber);
    }
    
    @Step("Открыть форму редактирования последнего статуса")
    public CreateStatusPage openEditLastStatusForm() {
        int statusesCount = taskStatusesPage.getStatusesCount();
        return openEditStatusForm(statusesCount);
    }

    @Step("Удалить статус со строки под номером {numberRow}")
    public void deleteStatus(int numberRow) {
        taskStatusesPage.clickRowCheckBox(numberRow);
        taskStatusesPage.clickDelete();
    }

    @Step("Удалить все статусы")
    public void deleteAllStatuses() {
        taskStatusesPage.clickHeadCheckBox();
        taskStatusesPage.clickDelete();
    }

    @Step("Проверить, что страница Task Statuses открыта.")
    public void assertTaskStatusesPageOpen(TaskStatusesPage taskStatusesPage) {
        this.taskStatusesPage = taskStatusesPage;
        boolean isOpen = taskStatusesPage.isStatusTableVisible();

        Assertions.assertTrue(isOpen, "Страница Task Statuses не открыта");
    }

    @Step("Проверить, что таблица статусов задач загружается полностью.")
    public void assertTaskStatusesTableFullLoad() {
        boolean isLoad = taskStatusesPage.isStatusTableVisible()
                && taskStatusesPage.isTableHeadVisible()
                && taskStatusesPage.isTableBodyVisible();

        Assertions.assertTrue(isLoad, "Таблица полностью не загружена");
    }

    @Step("Проверить, что отображаются ключевые поля: Name, Slug.")
    public void assertRequiredColumnsVisible() {
        boolean isVisible = taskStatusesPage.isNameColumnVisible()
                && taskStatusesPage.isSlugColumnVisible();

        Assertions.assertTrue(isVisible, "Отображаются не все ключевые поля");
    }

    @Step("Проверить, что в таблице нет статуса")
    public void assertStatusNotExist(String name, String slug) {
        int statusesCount = taskStatusesPage.getStatusesCount();
        boolean isNotExist = true;
        for (int i = 1; i <= statusesCount; i++) {
            String actualName = taskStatusesPage.getNameCellText(i);
            String actualSlug = taskStatusesPage.getSlugCellText(i);

            if (name.equals(actualName) && slug.equals(actualSlug)) {
                isNotExist = false;
                break;
            }
        }

        Assertions.assertTrue(isNotExist, "В таблице есть проверяемый пользователь");
    }

    @Step("Проверить, что в таблице есть статус")
    public void assertStatusExist(String name, String slug) {
        int statusesCount = taskStatusesPage.getStatusesCount();
        boolean isExist = false;
        for (int i = 1; i <= statusesCount; i++) {
            String actualName = taskStatusesPage.getNameCellText(i);
            String actualSlug = taskStatusesPage.getSlugCellText(i);

            if (name.equals(actualName) && slug.equals(actualSlug)) {
                isExist = true;
                break;
            }
        }

        Assertions.assertTrue(isExist, "В таблице нет проверяемого пользователя");
    }

    @Step("Проверить, что количество статусов = {expectedCount}")
    public void assertNumberStatuses(int expectedCount) {
        int actualCount = taskStatusesPage.getStatusesCount();

        Assertions.assertEquals(expectedCount, actualCount, "Количество статусов = " + actualCount);
    }

    @Step("Проверить, что все статусы удалены")
    public void assertAllStatusesDelete() {
        boolean hasText = taskStatusesPage.hasEmptyResultBlockText("No Task statuses yet.")
                && taskStatusesPage.hasEmptyResultBlockText("Do you want to add one?");

        Assertions.assertTrue(hasText, "Удалены не все статусы");
    }
}
