package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class Driver {

    private static AppiumDriver driver;

    public static void initDriver() {
        if(driver != null) {
            return;
        }
        String avd = EnvConfig.get(String.format("AC_%s_AVD", DriverManager.platform));
        String appName = EnvConfig.get(String.format("AC_%s_APP_NAME", DriverManager.platform));
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName("UiAutomator2");
        options.setAvdLaunchTimeout(Duration.ofSeconds(120));
        URL serverUrl = generateAppiumServerUrl(DriverManager.platform);
        assert serverUrl != null;
        if (DriverManager.platform.equalsIgnoreCase("android")) {
            options.setDeviceName("Android Emulator");
            options.setPlatformName("Android");
            options.setAvd(avd);
            options.setApp(String.format("src/main/resources/apps/%s", appName));
            driver = new AndroidDriver(serverUrl, options);
        }
        else if(DriverManager.platform.equalsIgnoreCase("ios")) {
            options.setDeviceName("IOS Emulator");
            options.setPlatformName("ios");
            options.setAvd(avd);
            options.setApp(String.format("src/main/resources/apps/%s", appName));
            driver = new IOSDriver(serverUrl, options);
        }
    }

    public static URL generateAppiumServerUrl(String platform) {
        try {
            String serverHost = EnvConfig.get(String.format("AC_%s_SERVER_HOST", platform));
            String serverPort = EnvConfig.get(String.format("AC_%s_SERVER_PORT", platform));
            URI uri = new URI(String.format("http://%s:%s/", serverHost, serverPort));
            return uri.toURL();
        }
        catch(Exception e) {
            System.err.println("Error generating appium server URL: " + e.getMessage());
        }
        return null;
    }

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
