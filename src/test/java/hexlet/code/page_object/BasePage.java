package hexlet.code.page_object;

import hexlet.code.config.ConfigManager;
import hexlet.code.config.TestConfig;
import hexlet.code.support.helper.ElementHelper;
import hexlet.code.support.utils.common.CustomWebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final CustomWebDriverWait wait;
    protected final ElementHelper element;

    private static final TestConfig CONFIG = ConfigManager.getConfig();

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new CustomWebDriverWait(driver,
                Duration.ofSeconds(CONFIG.defaultTimeout()),
                Duration.ofMillis(CONFIG.defaultSleep()));
        this.element = new ElementHelper(driver, wait);
        PageFactory.initElements(driver, this);
    }
}
