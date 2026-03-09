package screens;

import helpers.MainHelpers;
import org.openqa.selenium.By;
import util.Screen;

public class MainScreen extends Screen {

    private final By profileButton = By.id("com.example.allchat:id/profilePicture"),
                    logoutButton = By.id("com.example.allchat:id/button5");

    public void clickOnProfile() {
        MainHelpers.waitForVisibilityOfElement(5, profileButton);
        MainHelpers.waitForOfElementToBeClickable(3, profileButton);
        findElementBy(profileButton).click();
    }

    public void clickOnLogoutButton() {
        MainHelpers.waitForVisibilityOfElement(5, logoutButton);
        MainHelpers.waitForOfElementToBeClickable(3, logoutButton);
        findElementBy(logoutButton).click();
    }
}
