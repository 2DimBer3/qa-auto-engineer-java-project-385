package hexlet.code.tests.menu;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.menu.users.CreateUserPage;
import hexlet.code.page_object.menu.users.UsersPage;
import hexlet.code.steps.HomePageSteps;
import hexlet.code.steps.LoginPageSteps;
import hexlet.code.steps.menu.users.CreateUserPageSteps;
import hexlet.code.steps.menu.users.UsersPageSteps;
import hexlet.code.tests.BaseTest;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hexlet.code.steps.CommonPageSteps.assertValueField;

public class UsersTest extends BaseTest {

    private final LoginPageSteps loginPageSteps = new LoginPageSteps();
    private final HomePageSteps homePageSteps = new HomePageSteps();
    private final UsersPageSteps usersSteps = new UsersPageSteps();
    private final CreateUserPageSteps createUserSteps = new CreateUserPageSteps();


    @BeforeEach
    public void loginAndGoToUsers() {
        HomePage homePage = loginPageSteps.openPageAndLogin(config.userLogin(), config.userPassword());
        homePageSteps.assertPageOpen(homePage);
        UsersPage usersPage = homePageSteps.openMenuUsers();
        usersSteps.assertUsersPageOpen(usersPage);
    }

    @Test
    public void testUsersTableContains() {
        // Проверьте, что таблица пользователей загружается полностью.
        usersSteps.assertUserTableFullLoad();

        // Удостоверьтесь, что отображаются ключевые поля: Email, First name, Last name.
        usersSteps.assertRequiredColumnsVisible();
    }

    @Test
    public void testCreateUser() {
        int countBefore = usersSteps.countNumberUsers();

        CreateUserPage createUserPage = usersSteps.openCreateUserPage();
        createUserSteps.assertCreateUserPageOpen(createUserPage);

        // Убедитесь, что форма создания открывается корректно.
        createUserSteps.assertCreateUserFormElementsVisible();

        //Заполните данные нового пользователя и проверьте, что карточка появляется в списке.
        String email = "dima@example.com";
        String firstName = "Dima";
        String lastName = "Bell";
        UsersPage usersPage = createUserSteps.fillFormAndSave(email, firstName, lastName);

        usersSteps.assertUsersPageOpen(usersPage);
        usersSteps.assertNumberUsers(countBefore + 1);
        usersSteps.assertUserExist(email, firstName, lastName);
    }

    @Test
    public void testEditUser() {
        // Создаём нового пользователя
        String initialEmail = "kate@example.com";
        String initialFirstName = "Kate";
        String initialLastName = "Brown";
        createUser(initialEmail, initialFirstName, initialLastName);

        // Откройте форму редактирования и убедитесь, что данные подставляются верно.
        CreateUserPage createUserPage = usersSteps.openEditLastUserForm();
        createUserSteps.assertEditUserPageOpen(createUserPage);

        assertValueField(initialEmail, createUserPage.getEmailValue(), "Email");
        assertValueField(initialFirstName, createUserPage.getFirstNameValue(), "First name");
        assertValueField(initialLastName, createUserPage.getLastNameValue(), "Last name");

        //Измените значения и проверьте, что обновления сохранены.
        String newEmail = "alice@example.com";
        String newFirstName = "Alice";
        String newLastName = "Smith";
        UsersPage usersPage = createUserSteps.fillFormAndSave(newEmail, newFirstName, newLastName);

        usersSteps.assertUsersPageOpen(usersPage);
        usersSteps.assertUserExist(newEmail, newFirstName, newLastName);
        usersSteps.assertUserNotExist(initialEmail, initialFirstName, initialLastName);
    }

    @Test
    public void testEmailValidationOnEdit() {
        // Создаём пользователя, чтобы открыть форму редактирования
        createUser("test@example.com", "Test", "User");

        // Дополнительно проверьте валидацию, в частности формат email.
        CreateUserPage createUserPage = usersSteps.openEditLastUserForm();
        createUserSteps.assertEditUserPageOpen(createUserPage);
        createUserSteps.fillEmailField("efd");
        createUserSteps.clickSave();

        homePageSteps.assertAlertVisibleWithText("The form is not valid. Please check for errors");
        createUserSteps.assertFieldWithError("Email", "Incorrect email format");
    }

    @Test
    public void testDeleteUser() {
        // Создаём нового пользователя
        String email = "max@example.com";
        String firstName = "Max";
        String lastName = "Jordan";
        createUser(email, firstName, lastName);

        // Удалите одного или нескольких пользователей и подтвердите, что их больше нет в списке.
        int countBefore = usersSteps.countNumberUsers();
        usersSteps.deleteUser(countBefore);
        homePageSteps.assertAlertVisibleWithText("Element deleted");

        usersSteps.assertNumberUsers(countBefore - 1);
        usersSteps.assertUserNotExist(email, firstName, lastName);
    }

    @Test
    public void testDeleteAllUsers() {
        // Убедимся, что есть хотя бы один пользователь
        int countUsers = usersSteps.countNumberUsers();
        if (countUsers == 0) {
            createUser("max@example.com", "Max", "Jordan");
        }

        // Выделите всех пользователей целиком.
        // Удалите выбранные записи и проверьте, что список очищен.
        usersSteps.deleteAllUsers();
        usersSteps.assertAllUsersDelete();
    }

    @Step("Создать пользователя")
    private void createUser(String email, String firstName, String lastName) {
        CreateUserPage form = usersSteps.openCreateUserPage();
        createUserSteps.assertCreateUserPageOpen(form);

        UsersPage usersPage = createUserSteps.fillFormAndSave(email, firstName, lastName);
        usersSteps.assertUsersPageOpen(usersPage);
    }
}
