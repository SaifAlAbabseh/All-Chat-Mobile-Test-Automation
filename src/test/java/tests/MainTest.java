package tests;

import base.TestBase;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MainScreen;
import util.EnvConfig;
import static org.testng.Assert.*;


public class MainTest extends TestBase {

    private LoginScreen loginScreen;
    private MainScreen mainScreen;

    @Test(priority = 1, description = "Test the login screen")
    public void testLogin() {
        loginScreen = new LoginScreen();
        loginScreen.typeUsername(EnvConfig.get("AC_USERNAME"));
        loginScreen.typePassword(EnvConfig.get("AC_PASSWORD"));
        mainScreen = loginScreen.clickLoginButton();
    }

    @Test(priority = 2, description = "Test logout")
    public void testLogout() {
        mainScreen.clickOnProfile();
        mainScreen.clickOnLogoutButton();
    }
}
