package hexlet.code.page_object.menu.tasks.components;

import hexlet.code.driver.DriverFactory;
import hexlet.code.page_object.menu.tasks.EditTaskPage;
import hexlet.code.page_object.menu.tasks.ShowTaskPage;
import hexlet.code.support.helper.ElementHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CardComponent {

    private final ElementHelper element;
    private final WebElement rootElement;

    @FindBy(css = ".MuiTypography-h5")
    private WebElement title;

    @FindBy(css = "a[aria-label='Edit']")
    private WebElement editButton;

    @FindBy(css = "a[aria-label='Show']")
    private WebElement showButton;

    public CardComponent(WebElement cardElement, ElementHelper element) {
        PageFactory.initElements(cardElement, this);
        this.rootElement = cardElement;
        this.element = element;
    }

    WebElement getRootElement() {
        return rootElement;
    }

    public EditTaskPage clickEdit() {
        element.click(editButton, "Кнопка Edit");
        return new EditTaskPage(DriverFactory.getDriver());
    }

    public ShowTaskPage clickShow() {
        element.click(showButton, "Кнопка Show");
        return new ShowTaskPage(DriverFactory.getDriver());
    }

    public String getTitle() {
        return element.getText(title, "Заголовок карточки");
    }
}
