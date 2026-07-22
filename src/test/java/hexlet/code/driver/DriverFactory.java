package hexlet.code.driver;

import hexlet.code.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

    public static WebDriver createDriver(TestConfig config) {
        String browser = config.browser().toLowerCase();
        return switch (browser) {
            case "firefox" -> new FirefoxDriver(DriverOptionsConfigurator.getFirefoxOptions(config));
            case "edge" -> new EdgeDriver(DriverOptionsConfigurator.getEdgeOptions(config));
            default -> new ChromeDriver(DriverOptionsConfigurator.getChromeOptions(config));
        };
    }
}
