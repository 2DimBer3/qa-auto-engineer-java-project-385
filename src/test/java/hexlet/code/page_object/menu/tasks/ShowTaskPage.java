package hexlet.code.page_object.menu.tasks;

import hexlet.code.page_object.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShowTaskPage extends HomePage {

    @FindBy(css = "a[href*='/users'][href*='/show'] span")
    private WebElement assigner;

    @FindBy(css = "a[href*='/labels'] span[class*='label']")
    private List<WebElement> labels;

    public ShowTaskPage(WebDriver driver) {
        super(driver);
    }

    public String getAssigner() {
        return element.getText(assigner, "Assigner");
    }

    public List<String> getLabels() {
        return element.getText(labels, "Label");
    }
}
