package hexlet.code.steps;

import hexlet.code.driver.DriverFactory;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommonPageSteps {

    @Step("Проверить заголовок страницы, ожидается: '{expectedTitle}'")
    public static void assertPageTitle(String expectedTitle) {
        String actualTitle = DriverFactory.getDriver()
                .getTitle();
        assertEquals(expectedTitle, actualTitle, "Заголовок страницы не совпадает");
    }
}
