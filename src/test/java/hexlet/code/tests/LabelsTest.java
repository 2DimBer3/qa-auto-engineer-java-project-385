package hexlet.code.tests;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.menu.labels.CreateLabelPage;
import hexlet.code.page_object.menu.labels.LabelsPage;
import hexlet.code.steps.HomePageSteps;
import hexlet.code.steps.LoginPageSteps;
import hexlet.code.steps.labels.CreateLabelPageSteps;
import hexlet.code.steps.labels.LabelsPageSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LabelsTest extends BaseTest {

    private final LoginPageSteps loginPageSteps = new LoginPageSteps();
    private final HomePageSteps homePageSteps = new HomePageSteps();
    private final LabelsPageSteps labelsSteps = new LabelsPageSteps();
    private final CreateLabelPageSteps createLabelSteps = new CreateLabelPageSteps();

    @BeforeEach
    public void loginAndGoToLabels() {
        HomePage homePage = loginPageSteps.openPageAndLogin(config.userLogin(), config.userPassword());
        homePageSteps.assertPageOpen(homePage);
        LabelsPage labelPage = homePageSteps.openMenuLabels();
        labelsSteps.assertLabelsPageOpen(labelPage);
    }

    @Test
    public void testLabelsTableContains() {
        // Проверьте, что таблица меток корректно отображает все записи.
        labelsSteps.assertLabelsTableFullLoad();
        labelsSteps.assertRequiredColumnsVisible();
    }

    @Test
    public void testCreateLabel() {
        int countBefore = labelsSteps.countNumberLabels();

        CreateLabelPage createLabelPage = labelsSteps.openCreateLabelPage();
        createLabelSteps.assertCreateLabelPageOpen(createLabelPage);

        // Проверьте, что форма добавления открывается и отображает нужные поля.
        createLabelSteps.assertCreateLabelFormElementsVisible();

        // Создайте новую метку и проверьте, что она появляется в списке.
        String name = "create";
        LabelsPage labelsPage = createLabelSteps.fillFormAndSave(name);

        labelsSteps.assertLabelsPageOpen(labelsPage);
        labelsSteps.assertNumberLabels(countBefore + 1);
        labelsSteps.assertLabelExist(name);
    }

    @Test
    public void testEditLabel() {
        // Создаём новую метку
        String initialName = "new";
        createLabel(initialName);

        // Измените существующую метку и подтвердите, что обновления сохранены.
        CreateLabelPage createLabelPage = labelsSteps.openEditLastLabelForm();
        createLabelSteps.assertCreateLabelPageOpen(createLabelPage);

        String newName = "update";
        LabelsPage labelsPage = createLabelSteps.fillFormAndSave(newName);

        labelsSteps.assertLabelsPageOpen(labelsPage);
        labelsSteps.assertLabelExist(newName);
        labelsSteps.assertLabelNotExist(initialName);
    }

    @Test
    public void testDeleteLabel() {
        // Создаём новую метку
        String name = "ToDelete";
        createLabel(name);

        // Удалите одну или несколько меток и убедитесь, что они исчезли из списка.
        int countBefore = labelsSteps.countNumberLabels();
        labelsSteps.deleteLabel(countBefore);
        homePageSteps.assertAlertVisibleWithText("Element deleted");

        labelsSteps.assertNumberLabels(countBefore - 1);
        labelsSteps.assertLabelNotExist(name);
    }

    private void createLabel(String name) {
        CreateLabelPage createLabelPage = labelsSteps.openCreateLabelPage();
        createLabelSteps.assertCreateLabelPageOpen(createLabelPage);

        LabelsPage labelsPage = createLabelSteps.fillFormAndSave(name);
        labelsSteps.assertLabelsPageOpen(labelsPage);
    }
}
