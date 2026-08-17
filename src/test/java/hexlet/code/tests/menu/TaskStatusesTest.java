package hexlet.code.tests.menu;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.menu.statuses.CreateStatusPage;
import hexlet.code.page_object.menu.statuses.TaskStatusesPage;
import hexlet.code.steps.HomePageSteps;
import hexlet.code.steps.LoginPageSteps;
import hexlet.code.steps.menu.statuses.CreateStatusPageSteps;
import hexlet.code.steps.menu.statuses.TaskStatusesPageSteps;
import hexlet.code.tests.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskStatusesTest extends BaseTest {

    private final LoginPageSteps loginPageSteps = new LoginPageSteps();
    private final HomePageSteps homePageSteps = new HomePageSteps();
    private final TaskStatusesPageSteps statusSteps = new TaskStatusesPageSteps();
    private final CreateStatusPageSteps createStatusSteps = new CreateStatusPageSteps();

    @BeforeEach
    public void loginAndGoToStatuses() {
        HomePage homePage = loginPageSteps.openPageAndLogin(config.userLogin(), config.userPassword());
        homePageSteps.assertPageOpen(homePage);
        TaskStatusesPage statusPage = homePageSteps.openMenuTaskStatuses();
        statusSteps.assertTaskStatusesPageOpen(statusPage);
    }

    @Test
    public void testStatusesTableContains() {
        // Убедитесь, что таблица загружает все статусы
        statusSteps.assertTaskStatusesTableFullLoad();

        // Проверьте отображение ключевых полей: название и slug
        statusSteps.assertRequiredColumnsVisible();
    }

    @Test
    public void testCreateStatus() {
        int countBefore = statusSteps.countNumberStatuses();

        CreateStatusPage createStatusPage = statusSteps.openCreateUserPage();
        createStatusSteps.assertCreateStatusPageOpen(createStatusPage);

        // Проверьте, что форма добавления открывается и отображает нужные поля.
        createStatusSteps.assertCreateStatusFormElementsVisible();

        // Заполните название и slug, подтвердите создание и убедитесь, что запись появилась в списке.
        String name = "In Progress";
        String slug = "in_progress";
        createStatusSteps.fillFormAndSave(name, slug);
        TaskStatusesPage taskStatusesPage = homePageSteps.openMenuTaskStatuses();

        statusSteps.assertTaskStatusesPageOpen(taskStatusesPage);
        statusSteps.assertNumberStatuses(countBefore + 1);
        statusSteps.assertStatusExist(name, slug);
    }

    @Test
    public void testEditStatus() {
        // Создаём новый статус
        String initialName = "New";
        String initialSlug = "new";
        createStatus(initialName, initialSlug);

        // Откройте форму редактирования, измените данные и убедитесь, что обновления сохранены.
        CreateStatusPage createStatusPage = statusSteps.openEditLastStatusForm();
        createStatusSteps.assertEditStatusPageOpen(createStatusPage);

        String newName = "Updated";
        String newSlug = "updated";
        createStatusSteps.fillFormAndSave(newName, newSlug);
        TaskStatusesPage taskStatusesPage = homePageSteps.openMenuTaskStatuses();

        statusSteps.assertTaskStatusesPageOpen(taskStatusesPage);
        statusSteps.assertStatusExist(newName, newSlug);
        statusSteps.assertStatusNotExist(initialName, initialSlug);
    }

    @Test
    public void testDeleteStatus() {
        // Создаём новый статус
        String name = "To Delete";
        String slug = "to_delete";
        createStatus(name, slug);
        int countBefore = statusSteps.countNumberStatuses();

        // Удалите один или несколько статусов и проверьте, что они исчезли из списка.
        statusSteps.deleteStatus(countBefore);

        homePageSteps.assertAlertVisibleWithText("Element deleted");
        statusSteps.assertNumberStatuses(countBefore - 1);
        statusSteps.assertStatusNotExist(name, slug);
    }

    @Test
    public void testDeleteAllStatuses() {
        // Убедимся, что есть хотя бы один пользователь
        int countStatuses = statusSteps.countNumberStatuses();
        if (countStatuses == 0) {
            createStatus("All Delete", "all_delete");
        }

        // Выделите все статусы и выполните групповое удаление, затем убедитесь, что список пуст.
        statusSteps.deleteAllStatuses();
        statusSteps.assertAllStatusesDelete();
    }

    private void createStatus(String name, String slug) {
        CreateStatusPage createStatusPage = statusSteps.openCreateUserPage();
        createStatusSteps.assertCreateStatusPageOpen(createStatusPage);

        createStatusSteps.fillFormAndSave(name, slug);
        TaskStatusesPage taskStatusesPage = homePageSteps.openMenuTaskStatuses();
        statusSteps.assertTaskStatusesPageOpen(taskStatusesPage);
    }
}
