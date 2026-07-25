package hexlet.code.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckUtils(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isDisplayed(WebElement element, String elementName) {
        return isDisplayed(element, elementName, driver.manage().timeouts().getImplicitWaitTimeout());
    }

    public boolean isDisplayed(WebElement element, String elementName, Duration timeout) {
        try {
            WebDriverWait flexibleWait = new WebDriverWait(driver, timeout);
            flexibleWait.withMessage(String.format("Элемент '%s' не отобразился за %d секунд",
                            elementName, timeout.getSeconds()))
                    .until(d -> element.isDisplayed());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHidden(WebElement element, String elementName) {
        try {
            wait.withMessage(String.format("Элемент '%s' не стал скрытым за %s секунд",
                            elementName, driver.manage().timeouts().getImplicitWaitTimeout()))
                    .until(d -> !element.isDisplayed());
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean hasText(WebElement element, String expectedText, String elementName) {
        try {
            wait.withMessage(String.format("Элемент '%s' не содержит текст '%s'", elementName, expectedText))
                    .until(d -> element.isDisplayed()
                            && element.getText().trim().equals(expectedText));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasTextIgnoringCase(WebElement element, String expectedText, String elementName) {
        try {
            wait.withMessage(String.format("Элемент '%s' не содержит текст '%s'", elementName, expectedText))
                    .until(d -> element.isDisplayed()
                            && element.getText().trim().equalsIgnoreCase(expectedText));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean attributeContains(WebElement element, String attrName, String partialValue, String elementName) {
        try {
            wait.withMessage(String.format("Атрибут '%s' элемента '%s' не содержит '%s'",
                            attrName, elementName, partialValue))
                    .until(d -> {
                        String actual = element.getAttribute(attrName);
                        return actual != null && actual.contains(partialValue);
                    });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
