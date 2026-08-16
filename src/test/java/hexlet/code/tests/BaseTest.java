package hexlet.code.tests;

import hexlet.code.config.ConfigManager;
import hexlet.code.config.TestConfig;
import hexlet.code.driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {

    protected static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);
    protected static TestConfig config;
    protected WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        config = ConfigManager.getConfig();
        LOG.info("Конфигурация загружена. Профиль: {}", System.getProperty("env", "local"));
        LOG.info("Base URL: {}", config.baseUrl());
    }

    @BeforeEach
    public void setupTest() {
        LOG.info("Запуск теста: {}", this.getClass().getSimpleName());
        driver = DriverFactory.createDriver(config);
        LOG.debug("Драйвер создан: {}", driver.getClass().getSimpleName());
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            DriverFactory.removeDriver();
            LOG.debug("Драйвер закрыт: {}", driver.getClass().getSimpleName());
        }
    }
}
