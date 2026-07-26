package hexlet.code.support.utils.element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ActionUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final CheckUtils checkUtils;

    public ActionUtils(WebDriver driver, WebDriverWait wait, CheckUtils checkUtils) {
        this.driver = driver;
        this.wait = wait;
        this.checkUtils = checkUtils;
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
            wait.withMessage(String.format("Элемент '%s' не стал кликабельным за %s секунд",
                            elementName, driver.manage().timeouts().getImplicitWaitTimeout()))
                    .until(d -> element.isEnabled() && element.isDisplayed());

            element.click();
        } catch (Exception e) {
            // Fallback: клик через JS, если обычный клик перехвачен
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void click(List<WebElement> elements, int index, String elementName) {
        if (!checkUtils.waitForCollectionNotEmpty(elements, elementName)) {
            throw new IllegalStateException("Коллекция '" + elementName + "' пуста, невозможно выполнить клик");
        }

        if (elements.size() <= index) {
            throw new IllegalArgumentException(
                    String.format("Элемент с индексом %d не найден в коллекции '%s'. Размер коллекции: %d",
                            index, elementName, elements.size())
            );
        }

        WebElement element = elements.get(index);
        click(element, elementName);
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

    public List<WebElement> findElements(By by, String description) {
        AtomicInteger prevSize = new AtomicInteger(-1);

        return wait.withMessage(
                        String.format("Не удалось дождаться стабильного количества элементов по локатору '%s' (%s)",
                                by, description))
                .until(d -> {
                    List<WebElement> current = d.findElements(by);
                    int size = current.size();

                    if (size == 0) {
                        prevSize.set(-1);
                        return null;
                    }

                    if (size == prevSize.get()) {
                        return current;
                    }

                    prevSize.set(size);
                    return null;
                });
    }
}
