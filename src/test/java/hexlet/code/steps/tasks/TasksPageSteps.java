package hexlet.code.steps.tasks;

import hexlet.code.page_object.menu.tasks.EditTaskPage;
import hexlet.code.page_object.menu.tasks.ShowTaskPage;
import hexlet.code.page_object.menu.tasks.CreateTaskPage;
import hexlet.code.page_object.menu.tasks.TasksPage;
import hexlet.code.page_object.menu.tasks.components.CardComponent;
import hexlet.code.page_object.menu.tasks.components.ColumnComponent;
import hexlet.code.steps.HomePageSteps;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class TasksPageSteps extends HomePageSteps {

    private TasksPage tasksPage;

    @Step("Открыть форму создания задачи")
    public CreateTaskPage openCreateTaskPage() {
        return tasksPage.clickCreateTask();
    }

    @Step("Открыть форму редактирования задачи с заголовком '{title}'")
    public EditTaskPage openEditTaskFormByTitle(String title) {
        CardComponent cardComponent = tasksPage.getColumnComponents()
                .stream()
                .flatMap(column -> column.getCardComponents().stream())
                .filter(card -> card.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Карточка с заголовком '" + title + "' не найдена"));

        return cardComponent.clickEdit();
    }

    @Step("Получить все названия задач на доске")
    public List<String> getAllTaskNames() {
        return tasksPage.getColumnComponents()
                .stream()
                .flatMap(column -> column.getAllCardTitles().stream())
                .toList();
    }

    @Step("Посчитать количество задач на доске")
    public int countNumberTasks() {
        int tasksCount = tasksPage.getColumnComponents()
                .stream()
                .flatMap(column -> column.getCardComponents().stream())
                .toList()
                .size();
        Allure.addAttachment("Количество задач", String.valueOf(tasksCount));
        return tasksCount;
    }

    @Step("Получить все названия задач из колонки '{columnName}'")
    public List<String> getAllTaskNamesFromColumn(String columnName) {
        return tasksPage.getColumnByTitle(columnName).getAllCardTitles();
    }

    @Step("Переместить задачу '{taskTitle}' в колонку '{targetColumnName}'")
    public void moveTaskToColumn(String taskTitle, String targetColumnName) {
        CardComponent cardComponent = tasksPage.getCardByTitle(taskTitle);
        ColumnComponent targetColumn = tasksPage.getColumnByTitle(targetColumnName);
        targetColumn.moveCardToThisColumn(cardComponent);
    }

    @Step("Отфильтровать доску по статусу задачи")
    public void filterByStatus(String statusName) {
        tasksPage.clickStatus();
        tasksPage.selectOption(statusName);
    }

    @Step("Отфильтровать доску по назначению")
    public void filterByAssignee(String assigner) {
        tasksPage.clickAssignee();
        tasksPage.selectOption(assigner);
    }

    @Step("Отфильтровать доску по лейблу")
    public void filterByLabel(String label) {
        tasksPage.clickLabel();
        tasksPage.selectOption(label);
    }

    @Step("Проверить, что страница Tasks открыта.")
    public void assertTasksPageOpen(TasksPage tasksPage) {
        this.tasksPage = tasksPage;
        boolean isOpen = tasksPage.isBoardVisible();

        Assertions.assertTrue(isOpen, "Страница Tasks не открыта");
    }

    @Step("Проверить, что доска Tasks не пустая и содержит задачи.")
    public void assertTasksBoardNotEmpty() {
        List<String> allTasks = getAllTaskNames();
        boolean isNotEmpty = !allTasks.isEmpty();

        Assertions.assertTrue(isNotEmpty, "Доска пустая, задач нет");
    }

    @Step("Проверить, что в колонке '{columnName}' есть задачи")
    public void assertColumnIsNotEmpty(String columnName) {
        List<CardComponent> cards = tasksPage.getColumnByTitle(columnName).getCardComponents();
        boolean isNotEmpty = !cards.isEmpty();

        Assertions.assertTrue(isNotEmpty, "В указанной колонке нет задач");
    }

    @Step("Проверить, что все задачи находятся в колонке '{columnName}'.")
    public void assertAllTasksInColumn(String columnName) {
        assertColumnIsNotEmpty(columnName);
        int columnTasksCount = getAllTaskNamesFromColumn(columnName).size();
        int allTasksCount = getAllTaskNames().size();

        boolean isSizeListEqual = columnTasksCount == allTasksCount;

        Assertions.assertTrue(isSizeListEqual, "На доске не все задачи находятся в указанной колонке");
    }

    @Step("Проверить, на доске отфильтровались задачи по expectedAssigner = {expectedAssigner}")
    public void assertFilterTasksByAssigner(String expectedAssigner) {
        List<CardComponent> cards = tasksPage.getColumnComponents()
                .stream()
                .flatMap(column -> column.getCardComponents().stream())
                .toList();

        cards.forEach(card -> {
            ShowTaskPage showTaskPage = card.clickShow();
            String actualAssigner = showTaskPage.getAssigner();

            Assertions.assertEquals(expectedAssigner, actualAssigner,
                    "На доске не все задачи отфильтровались по указанному assigner");
        });
    }

    @Step("Проверить, на доске отфильтровались задачи по label = {expectedLabel}")
    public void assertFilterTasksByLabel(String expectedLabel) {
        List<CardComponent> cards = tasksPage.getColumnComponents()
                .stream()
                .flatMap(column -> column.getCardComponents().stream())
                .toList();

        cards.forEach(card -> {
            ShowTaskPage showTaskPage = card.clickShow(); // шаг открытия просмотра карточки?
            List<String> actualLabels = showTaskPage.getLabels(); // шаг получения все лейблов карточки?
            boolean containsExpectedLabel = actualLabels.contains(expectedLabel);

            Assertions.assertTrue(containsExpectedLabel,
                    "На доске не все задачи отфильтровались по указанному label.\n"
                            + "Лейблы проверяемой карточки:\n"
                            + actualLabels);
        });
    }

    @Step("Проверить, что в таблице нет задачи с заголовком {title}")
    public void assertTaskNotExist(String title) {
        boolean isNotExist = !getAllTaskNames().contains(title);

        Assertions.assertTrue(isNotExist, "На доске есть задача с указанным заголовком");
    }

    @Step("Проверить, что в таблице есть задача с заголовком {title}")
    public void assertTaskExist(String title) {
        boolean isExist = getAllTaskNames().contains(title);

        Assertions.assertTrue(isExist, "На доске нет задачи с указанным заголовком");
    }

    @Step("Проверить, что количество задач = {expectedCount}")
    public void assertNumberTasks(int expectedCount) {
        int actualCount = countNumberTasks();

        Assertions.assertEquals(expectedCount, actualCount, "Количество статусов = " + actualCount);
    }

    @Step("Проверить, что задача с заголовком '{taskTitle}' в колонке '{columnName}'")
    public void assertTaskInColumn(String taskTitle, String columnName) {
        boolean isContains = getAllTaskNamesFromColumn(columnName).contains(taskTitle);

        Assertions.assertTrue(isContains, "Задача не находится в указанной колонке");
    }
}
