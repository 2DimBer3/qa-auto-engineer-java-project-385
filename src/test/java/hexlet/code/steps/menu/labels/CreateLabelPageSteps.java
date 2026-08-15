package hexlet.code.steps.menu.labels;

import hexlet.code.config.ConfigManager;
import hexlet.code.page_object.menu.labels.CreateLabelPage;
import hexlet.code.page_object.menu.labels.LabelsPage;
import hexlet.code.steps.HomePageSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class CreateLabelPageSteps extends HomePageSteps {

    private CreateLabelPage createLabelPage;

    @Step("Ввести значение в поле `Name`")
    public void fillName(String value) {
        createLabelPage.typeName(value);
    }

    @Step("Заполнить форму создания лейбла и сохранить")
    public LabelsPage fillFormAndSave(String name) {
        fillName(name);
        return clickSave();
    }

    @Step("Нажать на кнопку `Save`")
    public LabelsPage clickSave() {
        createLabelPage.clickSave();
        return openMenuLabels();
    }

    @Step("Проверить, что страница создания лейбла открыта.")
    public void assertCreateLabelPageOpen(CreateLabelPage createLabelPage) {
        this.createLabelPage = createLabelPage;
        boolean isOpen = createLabelPage.getPageUrl()
                .contains(ConfigManager.getConfig()
                        .labelCreateEndpoint());

        Assertions.assertTrue(isOpen, "Страница создания лейбла не открыта");
    }

    @Step("Проверить, что отображаются все элементы формы для создания лейбла.")
    public void assertCreateLabelFormElementsVisible() {
        boolean isVisible = createLabelPage.isNameInputVisible()
                && createLabelPage.isSaveButtonVisible();

        Assertions.assertTrue(isVisible, "Не отображаются все элементы формы для создания лейбла");
    }
}
