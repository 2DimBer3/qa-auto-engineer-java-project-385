package hexlet.code.steps;

import hexlet.code.page_object.menu.users.CreateUserPage;
import hexlet.code.page_object.menu.users.UsersPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class UsersPageSteps extends HomePageSteps {

    private UsersPage usersPage;

    @Step("Запомнить количество пользователей в таблице")
    public int rememberUsersCount() {
        int userCount = usersPage.getUsersCount();
        Allure.addAttachment("Количество пользователей", String.valueOf(userCount));
        return userCount;
    }

    @Step("Открыть форму создания пользователя")
    public CreateUserPage openCreateUserPage() {
        return usersPage.clickCreateUser();
    }

    @Step("Открыть форму редактирования пользователя {userNumber}")
    public CreateUserPage openEditUserForm(int userNumber) {
        return usersPage.clickUser(userNumber);
    }
    
    @Step("Открыть форму редактирования последнего пользователя")
    public CreateUserPage openEditLastUserForm() {
        int userCount = usersPage.getUsersCount();
        return openEditUserForm(userCount);
    }

    @Step("Удалить пользователя со строки под номером {numberRow}")
    public void deleteUser(int numberRow) {
        usersPage.clickRowCheckBox(numberRow);
        usersPage.clickDelete();
    }

    @Step("Удалить всех пользователей")
    public void deleteAllUsers() {
        usersPage.clickHeadCheckBox();
        usersPage.clickDelete();
    }

    @Step("Проверить, что страница Users открыта.")
    public void assertUsersPageOpen(UsersPage usersPage) {
        this.usersPage = usersPage;
        boolean isOpen = usersPage.isUserTableVisible();

        Assertions.assertTrue(isOpen,
                "Страница Users не открыта");
    }

    @Step("Проверить, что таблица пользователей загружается полностью.")
    public void assertUserTableFullLoad() {
        boolean isLoad = usersPage.isUserTableVisible()
                && usersPage.isTableHeadVisible()
                && usersPage.isTableBodyVisible();

        Assertions.assertTrue(isLoad, "Таблица полностью не загружена");
    }

    @Step("Проверить, что отображаются ключевые поля: Email, First name, Last name.")
    public void assertRequiredColumnsVisible() {
        boolean isVisible = usersPage.isEmailColumnVisible()
                && usersPage.isFirstNameColumnVisible()
                && usersPage.isLastNameColumnVisible();

        Assertions.assertTrue(isVisible, "Отображаются не все ключевые поля");
    }

    @Step("Проверить, что в таблице нет пользователя")
    public void assertUserNotExist(String email, String firstName, String lastName) {
        int userCount = usersPage.getUsersCount();
        boolean isNotExist = true;
        for (int i = 1; i <= userCount; i++) {
            String actualEmail = usersPage.getEmailCellText(i);
            String actualFirstName = usersPage.getFirstNameCellText(i);
            String actualLastName = usersPage.getLastNameCellText(i);

            if (email.equals(actualEmail) && firstName.equals(actualFirstName) && lastName.equals(actualLastName)) {
                isNotExist = false;
            }
        }

        Assertions.assertTrue(isNotExist, "В таблице есть проверяемый пользователь");
    }

    @Step("Проверить, что в таблице есть пользователь")
    public void assertUserExist(String email, String firstName, String lastName) {
        int userCount = usersPage.getUsersCount();
        boolean isExist = false;
        for (int i = 1; i <= userCount; i++) {
            String actualEmail = usersPage.getEmailCellText(i);
            String actualFirstName = usersPage.getFirstNameCellText(i);
            String actualLastName = usersPage.getLastNameCellText(i);

            if (email.equals(actualEmail) && firstName.equals(actualFirstName) && lastName.equals(actualLastName)) {
                isExist = true;
                break;
            }
        }

        Assertions.assertTrue(isExist, "В таблице нет проверяемого пользователя");
    }

    @Step("Проверить, что количество пользователей = {expectedCount}")
    public void assertNumberUsers(int expectedCount) {
        int actualCount = usersPage.getUsersCount();

        Assertions.assertEquals(expectedCount, actualCount,
                "Количество пользователей = " + actualCount);
    }

    @Step("Проверить, что все пользователи удалены")
    public void assertAllUsersDelete() {
        boolean hasText = usersPage.hasEmptyResultBlockText("No Users yet.")
                && usersPage.hasEmptyResultBlockText("Do you want to add one?");

        Assertions.assertTrue(hasText,
                "Удалены не все пользователи");
    }
}
