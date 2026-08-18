package hexlet.code.page_object.menu.tasks.components;

import hexlet.code.support.helper.ElementHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ColumnComponent {

    @FindBy(css = "h6[class*='subtitle']")
    private WebElement header;

    @FindBy(css = "[data-rfd-draggable-id]")
    private List<WebElement> cardElements;

    private final List<CardComponent> cardComponents;

    private final ElementHelper element;
    private final WebElement rootElement;

    public ColumnComponent(WebElement columnElement, ElementHelper element) {
        PageFactory.initElements(columnElement, this);
        this.rootElement = columnElement;
        this.element = element;
        this.cardComponents = cardElements.stream()
                .map(cardElement -> new CardComponent(cardElement, element))
                .toList();
    }

    WebElement getRootElement() {
        return rootElement;
    }

    public List<CardComponent> getCardComponents() {
        return cardComponents;
    }

    public String getHeaderText() {
        return element.getText(header, "Заголовок колонки");
    }

    public List<String> getAllCardTitles() {
        return cardComponents.stream()
                .map(CardComponent::getTitle)
                .toList();
    }

    public void moveCardToThisColumn(CardComponent card) {
        element.dragAndDrop(card.getRootElement(), this.getRootElement());
    }

    public void waitForCardsStable() {
        element.waitForElementsStable(cardElements, "Карточки в колонке '" + getHeaderText() + "'");
    }
}