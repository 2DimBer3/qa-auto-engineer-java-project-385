package hexlet.code.tests.menu;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.menu.tasks.EditTaskPage;
import hexlet.code.page_object.menu.tasks.CreateTaskPage;
import hexlet.code.page_object.menu.tasks.TasksPage;
import hexlet.code.steps.HomePageSteps;
import hexlet.code.steps.LoginPageSteps;
import hexlet.code.steps.menu.tasks.CreateTaskPageSteps;
import hexlet.code.steps.menu.tasks.EditTaskPageSteps;
import hexlet.code.steps.menu.tasks.TasksPageSteps;
import hexlet.code.tests.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TasksTest extends BaseTest {

    private final LoginPageSteps loginSteps = new LoginPageSteps();
    private final HomePageSteps homeSteps = new HomePageSteps();
    private final TasksPageSteps tasksSteps = new TasksPageSteps();
    private final CreateTaskPageSteps createTaskSteps = new CreateTaskPageSteps();
    private final EditTaskPageSteps editTaskSteps = new EditTaskPageSteps();

    @BeforeEach
    public void loginAndGoToTasks() {
        HomePage homePage = loginSteps.openPageAndLogin(config.userLogin(), config.userPassword());
        homeSteps.assertPageOpen(homePage);
        TasksPage tasksPage = homeSteps.openMenuTasks();
        tasksSteps.assertTasksPageOpen(tasksPage);
    }

    @Test
    public void testBoardNotEmpty() {
        tasksSteps.assertTasksBoardNotEmpty();
    }

    @Test
    public void testFilterByStatus() {
        String status = "To Review";
        tasksSteps.filterByStatus(status);
        tasksSteps.assertAllTasksInColumn(status);
    }

    @Test
    public void testFilterByAssignee() {
        String assignee = "peter@outlook.com";
        tasksSteps.filterByAssignee(assignee);
        tasksSteps.assertFilterTasksByAssigner(assignee);
    }

    @Test
    public void testFilterByLabel() {
        String label = "bug";
        tasksSteps.filterByLabel("bug");
        tasksSteps.assertFilterTasksByLabel(label);
    }

    @Test
    public void testCreateTask() {
        int countBefore = tasksSteps.countNumberTasks();

        CreateTaskPage createTaskPage = tasksSteps.openCreateTaskPage();
        createTaskSteps.assertCreateTaskPageOpen(createTaskPage);

        // Проверьте, что форма добавления открывается и отображает нужные поля.
        createTaskSteps.assertCreateTaskFormElementsVisible();

        // Заполните title и status, подтвердите создание и убедитесь, что запись появилась в списке.
        String assigner = "michael@example.com";
        String title = "Create Task";
        String status = "Published";
        TasksPage tasksPage = createTaskSteps.fillRequireFormFieldsAndSave(assigner, title, status);

        tasksSteps.assertTasksPageOpen(tasksPage);
        tasksSteps.assertNumberTasks(countBefore + 1);
        tasksSteps.assertTaskExist(title);
        tasksSteps.assertTaskInColumn(title, status);
    }

    @Test
    public void testEditTask() {
        String initialAssigner = "emily@example.com";
        String initialTitle = "Edit Me";
        String initialStatus = "Draft";
        createTask(initialAssigner, initialTitle, initialStatus);

        // Откройте форму редактирования, измените данные и убедитесь, что обновления сохранены.
        EditTaskPage editTaskPage = tasksSteps.openEditTaskFormByTitle(initialTitle);
        editTaskSteps.assertEditTaskPageOpen(editTaskPage);

        String newAssignee = "sarah@example.com";
        String newTitle = "Update Task";
        editTaskSteps.fillAssignee(newAssignee);
        editTaskSteps.fillTitle(newTitle);
        TasksPage tasksPage = editTaskSteps.clickSave();

        tasksSteps.assertTasksPageOpen(tasksPage);
        tasksSteps.assertTaskExist(newTitle);
        tasksSteps.assertTaskNotExist(initialTitle);
    }

    @Test
    public void testMoveTaskBetweenColumns() {
        // Создать новую задачу
        String assigner = "alice@example.com";
        String title = "Move Me";
        String initialStatus = "To Be Fixed";
        createTask(assigner, title, initialStatus);

        //  Переместить задачу в соседнюю колонку и проверить, что она переместилась
        String newStatus = "To Publish";
        tasksSteps.moveTaskToColumn(title, newStatus);

        tasksSteps.assertTaskInColumn(title, newStatus);
    }

    @Test
    public void testDeleteTask() {
        // Создаём новую задачу
        String assigner = "peter@example.com";
        String title = "Delete Me";
        String status = "To Publish";
        createTask(assigner, title, status);
        int countBefore = tasksSteps.countNumberTasks();

        // Удалите задачу и проверьте, что она исчезла с доски.
        EditTaskPage editTaskPage = tasksSteps.openEditTaskFormByTitle(title);
        editTaskSteps.assertEditTaskPageOpen(editTaskPage);

        TasksPage tasksPage = editTaskSteps.deleteTask();
        tasksSteps.assertTasksPageOpen(tasksPage);

        tasksSteps.assertAlertVisibleWithText("Element deleted");
        tasksSteps.assertNumberTasks(countBefore - 1);
        tasksSteps.assertTaskNotExist(title);
    }

    private void createTask(String assigner, String title, String status) {
        CreateTaskPage createTaskPage = tasksSteps.openCreateTaskPage();
        createTaskSteps.assertCreateTaskPageOpen(createTaskPage);

        TasksPage tasksPage = createTaskSteps.fillRequireFormFieldsAndSave(assigner, title, status);
        tasksSteps.assertTasksPageOpen(tasksPage);
    }
}
