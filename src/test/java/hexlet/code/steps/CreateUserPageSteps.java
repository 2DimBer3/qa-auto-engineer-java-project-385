package hexlet.code.steps;

import hexlet.code.config.ConfigManager;
import hexlet.code.page_object.menu.users.CreateUserPage;
import hexlet.code.page_object.menu.users.UsersPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

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
        return clickSave();
    }

    @Step("Нажать на кнопку `Save`")
    public UsersPage clickSave() {
        createUserPage.clickSave();
        return openMenuUsers();
    }

    @Step("Проверить, что страница создания пользователя открыта.")
    public void assertCreateUserPageOpen(CreateUserPage createUserPage) {
        this.createUserPage = createUserPage;
        boolean isOpen = createUserPage.getPageUrl()
                .contains(ConfigManager.getConfig()
                        .userCreateEndpoint());

        Assertions.assertTrue(isOpen,
                "Страница создания пользователя не открыта");
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
