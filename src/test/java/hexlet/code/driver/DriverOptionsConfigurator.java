package hexlet.code.driver;

import hexlet.code.config.TestConfig;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

@SuppressWarnings("unused")
public class DriverOptionsConfigurator {

    public static ChromeOptions getChromeOptions(TestConfig config) {
        ChromeOptions options = new ChromeOptions();
        if (config.headless()) {
            options.addArguments("--headless");
        }
        options.addArguments("--no-sandbox");
        return options;
    }

    public static FirefoxOptions getFirefoxOptions(TestConfig config) {
        FirefoxOptions options = new FirefoxOptions();
        if (config.headless()) {
            options.addArguments("--headless");
        }
        return options;
    }

    public static EdgeOptions getEdgeOptions(TestConfig config) {
        EdgeOptions options = new EdgeOptions();
        if (config.headless()) {
            options.addArguments("--headless");
        }
        return options;
    }
}