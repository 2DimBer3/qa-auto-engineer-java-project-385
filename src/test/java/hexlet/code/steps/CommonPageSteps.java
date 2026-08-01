package hexlet.code.steps;

import hexlet.code.driver.DriverFactory;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommonPageSteps {

    @Step("Проверить заголовок страницы, ожидается: '{expectedTitle}'")
    public static void assertPageTitle(String expectedTitle) {
        String actualTitle = DriverFactory.getDriver()
                .getTitle();
        assertEquals(expectedTitle, actualTitle, "Заголовок страницы не совпадает");
    }

    @Step("Проверить, что поле '{fieldName}' заполнено текстом {expectedText}.")
    public static void assertValueField(String expectedText, String actualText, String fieldName) {
        assertEquals(expectedText, actualText,
                "Поле '" + fieldName + "' не заполнено ожидаемым значением");
    }
}
