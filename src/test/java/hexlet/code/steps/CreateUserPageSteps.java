package hexlet.code.steps;

import hexlet.code.config.ConfigManager;
import hexlet.code.page_object.menu.users.UserCreatePage;
import hexlet.code.page_object.menu.users.UsersPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class CreateUserPageSteps extends HomePageSteps {

    private UserCreatePage userCreatePage;

    @Step("Ввести значение в поле `Email`")
    public void fillEmailField(String value) {
        userCreatePage.typeEmail(value);
    }

    @Step("Ввести значение в поле `First Name`")
    public void fillFirstName(String value) {
        userCreatePage.typeFirstName(value);
    }

    @Step("Ввести значение в поле `Last Name`")
    public void fillLastName(String value) {
        userCreatePage.typeLastName(value);
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
        userCreatePage.clickSave();
        return openMenuUsers();
    }

    @Step("Проверить, что страница создания пользователя открыта.")
    public void assertCreateUserPageOpen(UserCreatePage userCreatePage) {
        this.userCreatePage = userCreatePage;
        boolean isOpen = userCreatePage.getPageUrl()
                .contains(ConfigManager.getConfig()
                        .userCreateEndpoint());

        Assertions.assertTrue(isOpen,
                "Страница создания пользователя не открыта");
    }

    @Step("Проверить, что отображаются все элементы формы для создания пользователя.")
    public void assertCreateUserFormElementsVisible() {
        boolean isVisible = userCreatePage.isEmailInputVisible()
                && userCreatePage.isFirstNameInputVisible()
                && userCreatePage.isLastNameInputVisible();

        Assertions.assertTrue(isVisible,
                "Не отображаются все элементы формы для создания пользователя");
    }

    @Step("Проверить, что поле '{fieldName}' с подписью об ошибке '{expectedText}'.")
    public void assertFieldWithError(String fieldName, String expectedText) {
        boolean hasText = userCreatePage.hasErrorBlockText(expectedText);

        Assertions.assertTrue(hasText,
                "Поле '" + fieldName + "' не содержит подпись с ожидаемой ошибкой");
    }
}
