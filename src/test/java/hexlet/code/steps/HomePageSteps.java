package hexlet.code.steps;

import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.LoginPage;
import io.qameta.allure.Step;


public class HomePageSteps {

    @Step("Выйти из системы")
    public static LoginPage logout(HomePage homePage) {
        return homePage.logout();
    }
}
