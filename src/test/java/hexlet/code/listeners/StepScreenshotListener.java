package hexlet.code.listeners;

import hexlet.code.driver.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

import static io.qameta.allure.model.Status.BROKEN;
import static io.qameta.allure.model.Status.FAILED;

public class StepScreenshotListener implements StepLifecycleListener {

    @Override
    public void beforeStepStop(StepResult result) {
        // 1. Проверяем, что шаг завершается с ошибкой
        if ((result.getStatus() == FAILED) || (result.getStatus() == BROKEN)) {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    // 2. Добавляем скриншот к текущему (ещё активному) шагу
                    Allure.addAttachment(
                            "Скриншот при падении",
                            "image/png",
                            new ByteArrayInputStream(screenshot),
                            ".png"
                    );
                } catch (Exception e) {
                    // Логируем ошибку, если не удалось сделать скриншот
                    System.err.println("Не удалось сделать скриншот: " + e.getMessage());
                }
            }
        }
    }
}
