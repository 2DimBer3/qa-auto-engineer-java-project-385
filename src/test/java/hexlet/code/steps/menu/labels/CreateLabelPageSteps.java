package hexlet.code.steps.menu.labels;

import hexlet.code.config.ConfigManager;
import hexlet.code.page_object.menu.labels.CreateLabelPage;
import hexlet.code.steps.HomePageSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.regex.Pattern;

public class CreateLabelPageSteps extends HomePageSteps {

    private CreateLabelPage createLabelPage;

    @Step("Ввести значение в поле `Name`")
    public void fillName(String value) {
        createLabelPage.typeName(value);
    }

    @Step("Заполнить форму создания лейбла и сохранить")
    public void fillFormAndSave(String name) {
        fillName(name);
        clickSave();
    }

    @Step("Нажать на кнопку `Save`")
    public void clickSave() {
        createLabelPage.clickSave();
    }

    @Step("Проверить, что страница создания лейбла открыта.")
    public void assertCreateLabelPageOpen(CreateLabelPage localCreateLabelPage) {
        createLabelPage = localCreateLabelPage;
        boolean isOpen = localCreateLabelPage.getPageUrl()
                .contains(ConfigManager.getConfig()
                        .labelCreateEndpoint());

        Assertions.assertTrue(isOpen, "Страница создания лейбла не открыта");
    }

    @Step("Проверить, что страница редактирования лейбла открыта.")
    public void assertEditLabelPageOpen(CreateLabelPage localCreateLabelPage) {
        createLabelPage = localCreateLabelPage;
        String actualUrl = localCreateLabelPage.getPageUrl();

        String expectedEndpoint = ConfigManager.getConfig()
                .labelEditEndpoint()
                .replace("{int}", "\\d+");

        boolean isOpen = Pattern.compile(".*" + expectedEndpoint)
                .matcher(actualUrl)
                .matches();

        Assertions.assertTrue(isOpen, "Страница редактирования лейбла не открыта: " + actualUrl);
    }

    @Step("Проверить, что отображаются все элементы формы для создания лейбла.")
    public void assertCreateLabelFormElementsVisible() {
        boolean isVisible = createLabelPage.isNameInputVisible()
                && createLabelPage.isSaveButtonVisible();

        Assertions.assertTrue(isVisible, "Не отображаются все элементы формы для создания лейбла");
    }
}
