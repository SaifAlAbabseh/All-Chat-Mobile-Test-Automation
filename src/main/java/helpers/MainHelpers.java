package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Driver;
import util.EnvConfig;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class MainHelpers {

    public static void waitForVisibilityOfElement(int seconds, By elementSelector) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementSelector));
    }

    public static void waitForOfElementToBeClickable(int seconds, By elementSelector) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(elementSelector));
    }

    public static URL generateAppiumServerUrl() {
        try {
            String serverHost = EnvConfig.get("APPIUM_HOST");
            String serverPort = EnvConfig.get("APPIUM_PORT");
            URI uri = new URI(String.format("http://%s:%s/", serverHost, serverPort));
            return uri.toURL();
        }
        catch(Exception e) {
            System.err.println("Error generating appium server URL: \n" + e.getMessage());
        }
        return null;
    }
}
