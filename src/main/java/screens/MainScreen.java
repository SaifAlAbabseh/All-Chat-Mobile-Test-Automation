package screens;

import helpers.MainHelpers;
import org.openqa.selenium.By;
import util.Page;

public class MainScreen extends Page {

    private final By profileButton = By.id("com.example.allchat:id/profilePicture"),
                    logoutButton = By.id("com.example.allchat:id/button5");

    public void clickOnProfile() {
        MainHelpers.waitForOfElementToBeClickable(3, profileButton);
        findElementBy(profileButton).click();
    }

    public void clickOnLogoutButton() {
        MainHelpers.waitForOfElementToBeClickable(3, logoutButton);
        findElementBy(logoutButton).click();
    }
}
