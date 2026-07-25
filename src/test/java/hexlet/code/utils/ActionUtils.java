package hexlet.code.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ActionUtils(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Клик по элементу с безопасным скроллом и обработкой перехвата.
     * Используется pollingEvery, чтобы чаще проверять готовность элемента.
     */
    public void click(WebElement element, String elementName) {
        System.out.println("timeout here: " + driver.manage().timeouts().getImplicitWaitTimeout());
        try {
            // Скроллим к элементу, чтобы избежать проблем с видимостью
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", element
            );

            // Ожидаем кликабельности с кастомным интервалом опроса и сообщением
            wait.pollingEvery(Duration.ofMillis(200))
                    .withMessage(String.format("Элемент '%s' не стал кликабельным за %s секунд",
                            elementName, driver.manage().timeouts().getImplicitWaitTimeout()))
                    .until(d -> element.isEnabled() && element.isDisplayed());

            element.click();
        } catch (Exception e) {
            // Fallback: клик через JS, если обычный клик перехвачен
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void type(WebElement element, String text, String elementName) {
        wait.withMessage(String.format("Поле '%s' не стало видимым для ввода", elementName))
                .until(d -> element.isDisplayed() && element.isEnabled());
        //InputHelper.inputValue(driver, element, text);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element, String elementName) {
        wait.withMessage(String.format("Элемент '%s' не отобразился для получения текста", elementName))
                .until(d -> element.isDisplayed());
        return element.getText().trim();
    }
}
