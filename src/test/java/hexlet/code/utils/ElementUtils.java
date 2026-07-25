package hexlet.code.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementUtils {

    private final ActionUtils actions;
    private final CheckUtils checks;

    public ElementUtils(WebDriver driver, WebDriverWait wait) {
        this.actions = new ActionUtils(driver, wait);
        this.checks = new CheckUtils(driver, wait);
    }

    // Действия
    public void click(WebElement element, String elementName) {
        actions.click(element, elementName);
    }

    public void type(WebElement element, String text, String elementName) {
        actions.type(element, text, elementName);
    }

    public String getText(WebElement element, String elementName) {
        return actions.getText(element, elementName);
    }

    // Проверки
    public boolean isDisplayed(WebElement element, String elementName) {
        return checks.isDisplayed(element, elementName);
    }

    public boolean isDisplayed(WebElement element, String elementName, Duration timeout) {
        return checks.isDisplayed(element, elementName, timeout);
    }

    public boolean isHidden(WebElement element, String elementName) {
        return checks.isHidden(element, elementName);
    }

    public boolean hasText(WebElement element, String expectedText, String elementName) {
        return checks.hasText(element, expectedText, elementName);
    }

    public boolean attributeContains(WebElement element, String attrName, String partialValue, String elementName) {
        return checks.attributeContains(element, attrName, partialValue, elementName);
    }
}