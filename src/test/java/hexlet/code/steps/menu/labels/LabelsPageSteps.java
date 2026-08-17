package hexlet.code.steps.menu.labels;

import hexlet.code.page_object.menu.labels.CreateLabelPage;
import hexlet.code.page_object.menu.labels.LabelsPage;
import hexlet.code.steps.HomePageSteps;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class LabelsPageSteps extends HomePageSteps {

    private LabelsPage labelPage;

    @Step("Посчитать количество лейблов в таблице")
    public int countNumberLabels() {
        int labelsCount = labelPage.getLabelsCount();
        Allure.addAttachment("Количество лейблов", String.valueOf(labelsCount));
        return labelsCount;
    }

    @Step("Открыть форму создания лейбла")
    public CreateLabelPage openCreateLabelPage() {
        return labelPage.clickCreateLabel();
    }

    @Step("Открыть форму редактирования лейбла со строки №{labelNumber}")
    public CreateLabelPage openEditLabelForm(int rowNumber) {
        return labelPage.clickLabel(rowNumber);
    }
    
    @Step("Открыть форму редактирования последнего лейбла")
    public CreateLabelPage openEditLastLabelForm() {
        int statusesCount = labelPage.getLabelsCount();
        return openEditLabelForm(statusesCount);
    }

    @Step("Удалить лейбл со строки под номером {numberRow}")
    public void deleteLabel(int numberRow) {
        labelPage.clickRowCheckBox(numberRow);
        labelPage.clickDelete();
    }

    @Step("Проверить, что страница Labels открыта.")
    public void assertLabelsPageOpen(LabelsPage labelPage) {
        this.labelPage = labelPage;
        boolean isOpen = labelPage.isLabelTableVisible();

        Assertions.assertTrue(isOpen, "Страница Labels не открыта");
    }

    @Step("Проверить, что таблица лейблов загружается полностью.")
    public void assertLabelsTableFullLoad() {
        boolean isLoad = labelPage.isLabelTableVisible()
                && labelPage.isTableHeadVisible()
                && labelPage.isTableBodyVisible();

        Assertions.assertTrue(isLoad, "Таблица полностью не загружена");
    }

    @Step("Проверить, что отображаются ключевые поля: Id, Name, Create at.")
    public void assertRequiredColumnsVisible() {
        boolean isVisible = labelPage.isIdColumnVisible()
                && labelPage.isNameColumnVisible()
                && labelPage.isCreatedAtColumnVisible();

        Assertions.assertTrue(isVisible, "Отображаются не все ключевые поля");
    }

    @Step("Проверить, что в таблице нет лейбла")
    public void assertLabelNotExist(String name) {
        int labelsCount = labelPage.getLabelsCount();
        boolean isNotExist = true;
        for (int i = 0; i < labelsCount; i++) {
            String actualName = labelPage.getNameCellText(i);

            if (name.equals(actualName)) {
                isNotExist = false;
                break;
            }
        }

        Assertions.assertTrue(isNotExist, "В таблице есть проверяемый лейбл");
    }

    @Step("Проверить, что в таблице есть лейбл")
    public void assertLabelExist(String name) {
        int labelsCount = labelPage.getLabelsCount();
        boolean isExist = false;
        for (int i = 1; i <= labelsCount; i++) {
            String actualName = labelPage.getNameCellText(i);

            if (name.equals(actualName)) {
                isExist = true;
                break;
            }
        }

        Assertions.assertTrue(isExist, "В таблице нет проверяемого лейбла");
    }

    @Step("Проверить, что количество лейблов = {expectedCount}")
    public void assertNumberLabels(int expectedCount) {
        int actualCount = labelPage.getLabelsCount();

        Assertions.assertEquals(expectedCount, actualCount, "Количество лейблов = " + actualCount);
    }
}
