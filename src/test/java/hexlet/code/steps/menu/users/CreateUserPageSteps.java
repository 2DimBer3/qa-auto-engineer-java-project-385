package hexlet.code.steps.menu.users;

import hexlet.code.config.ConfigManager;
import hexlet.code.page_object.menu.users.CreateUserPage;
import hexlet.code.page_object.menu.users.UsersPage;
import hexlet.code.steps.HomePageSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.regex.Pattern;

public class CreateUserPageSteps extends HomePageSteps {

    private CreateUserPage createUserPage;

    @Step("Ввести значение в поле `Email`")
    public void fillEmailField(String value) {
        createUserPage.typeEmail(value);
    }

    @Step("Ввести значение в поле `First Name`")
    public void fillFirstName(String value) {
        createUserPage.typeFirstName(value);
    }

    @Step("Ввести значение в поле `Last Name`")
    public void fillLastName(String value) {
        createUserPage.typeLastName(value);
    }

    @Step("Заполнить форму создания пользователя и сохранить")
    public UsersPage fillFormAndSave(String email, String firstName, String lastName) {
        fillEmailField(email);
        fillFirstName(firstName);
        fillLastName(lastName);
        clickSave();
        return createUserPage.clickMenuUsers();
    }

    @Step("Нажать на кнопку `Save`")
    public void clickSave() {
        createUserPage.clickSave();
    }

    @Step("Проверить, что страница создания пользователя открыта.")
    public void assertCreateUserPageOpen(CreateUserPage localCreateUserPage) {
        createUserPage = localCreateUserPage;
        String actualUrl = localCreateUserPage.getPageUrl();
        String expectedEndpoint = ConfigManager.getConfig()
                .userCreateEndpoint();

        boolean isOpen = actualUrl.contains(expectedEndpoint);

        Assertions.assertTrue(isOpen,
                "Страница создания пользователя не открыта: " + actualUrl);
    }

    @Step("Проверить, что страница редактирования пользователя открыта.")
    public void assertEditUserPageOpen(CreateUserPage localCreateUserPage) {
        createUserPage = localCreateUserPage;
        String actualUrl = localCreateUserPage.getPageUrl();

        String expectedEndpoint = ConfigManager.getConfig()
                .userEditEndpoint()
                .replace("{int}", "\\d+");

        boolean isOpen = Pattern.compile(".*" + expectedEndpoint)
                .matcher(actualUrl)
                .matches();

        Assertions.assertTrue(isOpen, "Страница редактирования задачи не открыта: " + actualUrl);
    }

    @Step("Проверить, что отображаются все элементы формы для создания пользователя.")
    public void assertCreateUserFormElementsVisible() {
        boolean isVisible = createUserPage.isEmailInputVisible()
                && createUserPage.isFirstNameInputVisible()
                && createUserPage.isLastNameInputVisible()
                && createUserPage.isSaveButtonVisible();

        Assertions.assertTrue(isVisible,
                "Не отображаются все элементы формы для создания пользователя");
    }

    @Step("Проверить, что поле '{fieldName}' с подписью об ошибке '{expectedText}'.")
    public void assertFieldWithError(String fieldName, String expectedText) {
        boolean hasText = createUserPage.hasErrorBlockText(expectedText);

        Assertions.assertTrue(hasText,
                "Поле '" + fieldName + "' не содержит подпись с ожидаемой ошибкой");
    }
}
