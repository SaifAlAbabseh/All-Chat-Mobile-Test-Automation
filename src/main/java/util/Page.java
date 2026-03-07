package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Page {

    public WebElement findElementBy(By elementSearchCriteria) {
        return Driver.getDriver().findElement(elementSearchCriteria);
    }

    public List<WebElement> findElementsBy(By elementsSearchCriteria) {
        return Driver.getDriver().findElements(elementsSearchCriteria);
    }
}
