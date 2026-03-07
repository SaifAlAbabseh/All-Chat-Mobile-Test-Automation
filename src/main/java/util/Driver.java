package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;
import java.time.Duration;

public class Driver {

    private static AppiumDriver driver;

    public Driver(String platform, URL serverPath, String avd, String appName) {
        if(driver != null) {
            return;
        }
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName("UiAutomator2");
        options.setAvdLaunchTimeout(Duration.ofSeconds(120));
        if (platform.equals("android")) {
            options.setDeviceName("Android Emulator");
            options.setPlatformName("Android");
            options.setAvd(avd);
            options.setApp(String.format("src/main/resources/apps/%s", appName));
            driver = new AndroidDriver(serverPath, options);
        }
    }

    public static AppiumDriver getDriver() {
        return driver;
    }
}
