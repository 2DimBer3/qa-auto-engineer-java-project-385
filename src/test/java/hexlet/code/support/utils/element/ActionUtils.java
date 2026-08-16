package hexlet.code.support.utils.element;

import hexlet.code.support.utils.common.CustomWebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class ActionUtils {

    private final WebDriver driver;
    private final CustomWebDriverWait wait;

    public ActionUtils(WebDriver driver, CustomWebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Клик по элементу с безопасным скроллом и обработкой перехвата.
     * Используется pollingEvery, чтобы чаще проверять готовность элемента.
     */
    public void click(WebElement element, String elementName) {
        System.out.println("timeout here: " + wait.getTimeout());
        try {
            wait.withMessage(String.format("Элемент '%s' не стал кликабельным за %s секунд",
                            elementName, wait.getTimeout()))
                    .until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", element
            );
            element.click();
        } catch (Exception e) {
            // Fallback: клик через JS, если обычный клик перехвачен
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void click(List<WebElement> elements, int index, String elementName) {
        if (!waitForCollectionNotEmpty(elements, elementName)) {
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

    public List<String> getText(List<WebElement> collection, String elementName) {
        return collection.stream()
                .map(element -> getText(element, elementName))
                .toList();
    }

    public String getValue(WebElement element, String elementName) {
        wait.withMessage(String.format("Элемент '%s' не отобразился для получения значения из value", elementName))
                .until(d -> element.isDisplayed());
        return Objects.requireNonNull(element.getAttribute("value")).trim();
    }

    public WebElement findElementByText(List<WebElement> elements, String expectedText, String description) {
        return wait.withMessage(
                        String.format("В коллекции '%s' не найден элемент с текстом '%s' за %s секунд",
                                description, expectedText, wait.getTimeout()))
                .until(d -> {
                    for (WebElement element : elements) {
                        if (element.getText().trim().equals(expectedText)) {
                            return element;
                        }
                    }
                    return null;
                });
    }

    public boolean waitForCollectionNotEmpty(List<WebElement> elements, String description) {
        try {
            wait.withMessage(String.format("Коллекция '%s' не стала непустой за %s секунд",
                            description, wait.getTimeout()))
                    .until(d -> !elements.isEmpty());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitForElementsStable(List<WebElement> elements, String description) {
        AtomicInteger prevSize = new AtomicInteger(-1);
        wait.withMessage(String.format("Список элементов '%s' не стабилизировался за %s секунд",
                        description, wait.getTimeout()))
                .until(d -> {
                    int size = elements.size();
                    if (size == prevSize.get()) {
                        return true;
                    }
                    prevSize.set(size);
                    return false;
                });
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public void selectOptionFromList(List<WebElement> options, String optionText) {
        WebElement option = findElementByText(options, optionText, "Опции списка");
        click(option, "Опция '" + optionText + "'");
    }

    public void dragAndDrop(WebElement source, WebElement target) {
        wait.withMessage("Исходный или целевой элемент не видимы для перетаскивания")
                .until(d -> source.isDisplayed() && target.isDisplayed());

        new Actions(driver)
                .clickAndHold(source)
                .moveToElement(target)
                .release()
                .perform();
    }
}