package helpers;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class MainHelpers {

    public static void waitForVisibilityOfElement(int seconds, By elementSelector) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementSelector));
    }

    public static void waitForOfElementToBeClickable(int seconds, By elementSelector) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(elementSelector));
    }

    public static String getDataTableValueAsString(DataTable table, int row, String header) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        return data.get(row).get(header);
    }
}
