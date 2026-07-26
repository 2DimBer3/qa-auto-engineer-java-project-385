package hexlet.code.support.utils.common;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;

public final class AnnotationUtils {

    /**
     * Возвращает CSS-селектор из аннотации @FindBy поля.
     * Если аннотация не содержит css, возвращает исключение.
     */
    public static String getCss(Field field) {
        FindBy findBy = getFindBy(field);
        if (findBy.css().isEmpty()) {
            throw new IllegalArgumentException("У поля " + field.getName() + " нет @FindBy с атрибутом css");
        }
        return findBy.css();
    }

    /**
     * Возвращает By для поля, помеченного аннотацией @FindBy с атрибутом css.
     * Если аннотация не содержит css, возвращает исключение.
     */
    public static By getByCss(Field field) {
        String css = getCss(field);
        return By.cssSelector(css);
    }

    // ========== Приватные вспомогательные методы ==========

    private static FindBy getFindBy(Field field) {
        FindBy findBy = field.getAnnotation(FindBy.class);
        if (findBy == null) {
            throw new IllegalArgumentException("Поле " + field.getName() + " не помечено аннотацией @FindBy");
        }
        return findBy;
    }
}