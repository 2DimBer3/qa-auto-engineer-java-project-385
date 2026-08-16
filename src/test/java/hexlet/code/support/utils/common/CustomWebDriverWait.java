package hexlet.code.support.utils.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomWebDriverWait extends WebDriverWait {

    private final Duration timeout;

    public CustomWebDriverWait(WebDriver driver, Duration timeout, Duration sleep) {
        super(driver, timeout, sleep);
        this.timeout = timeout;
    }

    public Duration getTimeout() {
        return timeout;
    }
}
