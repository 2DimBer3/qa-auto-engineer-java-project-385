package hexlet.code.tests;

import hexlet.code.config.ConfigManager;
import hexlet.code.config.TestConfig;
import hexlet.code.driver.DriverFactory;
import hexlet.code.page_object.HomePage;
import hexlet.code.page_object.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected static TestConfig config;
    protected WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        config = ConfigManager.getConfig();
    }

    @BeforeEach
    public void setupTest() {
        driver = DriverFactory.createDriver(config);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected HomePage performLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(config.baseUrl());
        return loginPage.login(config.userLogin(), config.userPassword());
    }
}