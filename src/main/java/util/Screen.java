package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Screen {

    public WebElement findElementBy(By elementSearchCriteria) {
        try {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(elementSearchCriteria));
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(elementSearchCriteria));
            return Driver.getDriver().findElement(elementSearchCriteria);
        }
        catch(Exception e) {
            System.err.println("Could not find this element: " + elementSearchCriteria + " \n Error: " + e.getMessage());
        }
        return null;
    }

    public List<WebElement> findElementsBy(By elementsSearchCriteria) {
        return Driver.getDriver().findElements(elementsSearchCriteria);
    }
}
