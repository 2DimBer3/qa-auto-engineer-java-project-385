package hexlet.code.driver;

import hexlet.code.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    public static WebDriver createDriver(TestConfig config) {
        String browser = config.browser().toLowerCase();
        WebDriver driver = switch (browser) {
            case "firefox" -> new FirefoxDriver(DriverOptionsConfigurator.getFirefoxOptions(config));
            case "edge" -> new EdgeDriver(DriverOptionsConfigurator.getEdgeOptions(config));
            default -> new ChromeDriver(DriverOptionsConfigurator.getChromeOptions(config));
        };
        DRIVER_THREAD_LOCAL.set(driver);
        return driver;
    }

    public static WebDriver getDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }

    public static void removeDriver() {
        DRIVER_THREAD_LOCAL.remove(); // очищаем после завершения теста
    }
}
