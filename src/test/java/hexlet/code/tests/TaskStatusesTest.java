package hexlet.code.tests;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.menu.statuses.CreateStatusPage;
import hexlet.code.page_object.menu.statuses.TaskStatusesPage;
import hexlet.code.page_object.menu.users.CreateUserPage;
import hexlet.code.steps.HomePageSteps;
import hexlet.code.steps.LoginPageSteps;
import hexlet.code.steps.TaskStatusesPageSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskStatusesTest extends BaseTest {

    private final LoginPageSteps loginPageSteps = new LoginPageSteps();
    private final HomePageSteps homePageSteps = new HomePageSteps();
    private final TaskStatusesPageSteps statusSteps = new TaskStatusesPageSteps();
    private final CreateStatusPageSteps createStatusSteps = new CreateStatusPageSteps();

    TaskStatusesPage statusesPage;

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
        int countBefore = statusSteps.rememberStatusesCount();

        CreateStatusPage createStatusPage = statusSteps.openCreateUserPage();
        createStatusSteps.assertCreateUserPageOpen(createStatusPage);

        // Проверьте, что форма добавления открывается и отображает нужные поля.
        statusForm.verifyFormElementsVisible();

        // Заполните название и slug, подтвердите создание и убедитесь, что запись появилась в списке.
        String name = "In Progress";
        String slug = "in_progress";
        statusesPage = statusForm.createStatusAndGoToList(name, slug);

        statusSteps.assertTaskStatusesPageOpen(statusesPage);
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
        CreateStatusPage statusForm = statusesPage.openLastStatus();

        String newName = "Updated";
        String newSlug = "updated";
        statusesPage = statusForm.editStatusAndGoToList(newName, newSlug);

        statusSteps.assertTaskStatusesPageOpen(statusesPage);
        statusSteps.assertStatusExist(newName, newSlug);
        statusSteps.assertStatusNotExist(initialName, initialSlug);
    }

    @Test
    public void testDeleteStatus() {
        // Создаём новый статус
        String name = "To Delete";
        String slug = "to_delete";
        createStatus(name, slug);

        // Удалите один или несколько статусов и проверьте, что они исчезли из списка.
        int countBefore = statusSteps.rememberStatusesCount();
        statusSteps.deleteStatus(countBefore);
        homePageSteps.assertAlertVisibleWithText("Element deleted");

        statusSteps.assertNumberStatuses(countBefore - 1);
        statusSteps.assertStatusNotExist(name, slug);
    }

    @Test
    public void testDeleteAllStatuses() {
        // Убедимся, что есть хотя бы один пользователь
        int countBefore = statusesPage.getStatusesCount();
        if (countBefore == 0) {
            createStatus("All Delete", "all_delete");
        }

        // Выделите все статусы и выполните групповое удаление, затем убедитесь, что список пуст.
        statusSteps.deleteAllStatuses();
        statusSteps.assertAllStatusesDelete();
    }

    private void createStatus(String name, String slug) {
        CreateStatusPage createStatusPage = statusSteps.openCreateUserPage();
        createStatusSteps.assertCreateUserPageOpen(createStatusPage);

        statusesPage = form.createStatusAndGoToList(name, slug);
    }
}
