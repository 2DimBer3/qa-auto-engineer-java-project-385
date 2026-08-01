package hexlet.code.tests;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.menu.users.UserCreatePage;
import hexlet.code.page_object.menu.users.UsersPage;
import hexlet.code.steps.HomePageSteps;
import hexlet.code.steps.LoginPageSteps;
import hexlet.code.steps.CreateUserPageSteps;
import hexlet.code.steps.UsersPageSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hexlet.code.steps.CommonPageSteps.assertValueField;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersTest extends BaseTest {

    private final LoginPageSteps loginPageSteps = new LoginPageSteps();
    private final HomePageSteps homePageSteps = new HomePageSteps();
    private final UsersPageSteps usersPageSteps = new UsersPageSteps();
    private final CreateUserPageSteps createUserPageSteps = new CreateUserPageSteps();


    @BeforeEach
    public void loginAndGoToUsers() {
        HomePage homePage = loginPageSteps.openPageAndLogin(config.userLogin(), config.userPassword());
        homePageSteps.assertPageOpen(homePage);
        UsersPage usersPage = homePageSteps.openMenuUsers();
        usersPageSteps.assertUsersPageOpen(usersPage);
    }

    @Test
    public void testUsersTableContains() {
        // Проверьте, что таблица пользователей загружается полностью.
        usersPageSteps.assertUserTableFullLoad();

        // Удостоверьтесь, что отображаются ключевые поля: Email, First name, Last name.
        usersPageSteps.assertRequiredColumnsVisible();
    }

    @Test
    public void testCreateUser() {
        int countBefore = usersPageSteps.rememberUsersCount();

        UserCreatePage userCreatePage = usersPageSteps.openCreateUserPage();
        createUserPageSteps.assertCreateUserPageOpen(userCreatePage);

        // Убедитесь, что форма создания открывается корректно.
        createUserPageSteps.assertCreateUserFormElementsVisible();

        //Заполните данные нового пользователя и проверьте, что карточка появляется в списке.
        String email = "dima@example.com";
        String firstName = "Dima";
        String lastName = "Bell";
        UsersPage usersPage = createUserPageSteps.fillFormAndSave(email, firstName, lastName);

        usersPageSteps.assertUsersPageOpen(usersPage);
        usersPageSteps.assertNumberUsers(countBefore + 1);
        usersPageSteps.assertUserExist(email, firstName, lastName);
    }

    @Test
    public void testEditUser() {
        // Создаём нового пользователя
        String initialEmail = "kate@example.com";
        String initialFirstName = "Kate";
        String initialLastName = "Brown";
        createUser(initialEmail, initialFirstName, initialLastName);

        // Откройте форму редактирования и убедитесь, что данные подставляются верно.
        UserCreatePage userCreatePage = usersPageSteps.openEditLastUserForm();

        assertValueField(initialEmail, userCreatePage.getEmailValue(), "Email");
        assertValueField(initialFirstName, userCreatePage.getFirstNameValue(), "First name");
        assertValueField(initialLastName, userCreatePage.getLastNameValue(), "Last name");

        //Измените значения и проверьте, что обновления сохранены.
        String newEmail = "alice@example.com";
        String newFirstName = "Alice";
        String newLastName = "Smith";
        UsersPage usersPage = createUserPageSteps.fillFormAndSave(newEmail, newFirstName, newLastName);

        usersPageSteps.assertUsersPageOpen(usersPage);
        usersPageSteps.assertUserExist(newEmail, newFirstName, newLastName);
        usersPageSteps.assertUserNotExist(initialEmail, initialFirstName, initialLastName);
    }

    @Test
    public void testEmailValidationOnEdit() {
        // Создаём пользователя, чтобы открыть форму редактирования
        createUser("test@example.com", "Test", "User");

        // Дополнительно проверьте валидацию, в частности формат email.
        UserCreatePage userCreatePage = usersPageSteps.openEditLastUserForm();
        createUserPageSteps.assertCreateUserPageOpen(userCreatePage);
        createUserPageSteps.fillEmailField("efd");
        createUserPageSteps.clickSave();

        createUserPageSteps.assertAlertVisibleWithText("The form is not valid. Please check for errors");
        createUserPageSteps.assertFieldWithError("Email", "Incorrect email format");
    }

    @Test
    public void testDeleteUser() {
        // Создаём нового пользователя
        String email = "max@example.com";
        String firstName = "Max";
        String lastName = "Jordan";
        createUser(email, firstName, lastName);

        // Удалите одного или нескольких пользователей и подтвердите, что их больше нет в списке.
        int countBefore = usersPageSteps.rememberUsersCount();
        usersPageSteps.deleteUser(countBefore);
        homePageSteps.assertAlertVisibleWithText("Element deleted");

        usersPageSteps.assertNumberUsers(countBefore - 1);
        usersPageSteps.assertUserNotExist(email, firstName, lastName);
    }

    @Test
    public void testDeleteAllUsers() {
        // Убедимся, что есть хотя бы один пользователь
        int countUsers = usersPageSteps.rememberUsersCount();
        if (countUsers == 0) {
            createUser("max@example.com", "Max", "Jordan");
        }

        // Выделите всех пользователей целиком.
        // Удалите выбранные записи и проверьте, что список очищен.
        usersPageSteps.deleteAllUsers();
        usersPageSteps.assertAllUsersDelete();
    }

    @Step("Создать пользователя")
    private void createUser(String email, String firstName, String lastName) {
        UserCreatePage form = usersPageSteps.openCreateUserPage();
        createUserPageSteps.assertCreateUserPageOpen(form);
        UsersPage usersPage = createUserPageSteps.fillFormAndSave(email, firstName, lastName);
        usersPageSteps.assertUsersPageOpen(usersPage);
    }
}
