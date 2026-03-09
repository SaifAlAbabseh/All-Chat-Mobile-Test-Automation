package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class Driver {

    private static AppiumDriver driver;

    public Driver(String platform) {
        if(driver != null) {
            return;
        }
        String avd = EnvConfig.get("AC_ANDROID_AVD");
        String appName = EnvConfig.get("AC_ANDROID_APP_NAME");
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName("UiAutomator2");
        options.setAvdLaunchTimeout(Duration.ofSeconds(120));
        URL serverUrl = generateAppiumServerUrl();
        assert serverUrl != null;
        if (platform.equals("android")) {
            options.setDeviceName("Android Emulator");
            options.setPlatformName("Android");
            options.setAvd(avd);
            options.setApp(String.format("src/main/resources/apps/%s", appName));
            driver = new AndroidDriver(serverUrl, options);
        }
    }

    public static URL generateAppiumServerUrl() {
        try {
            String serverHost = EnvConfig.get("AC_ANDROID_SERVER_HOST");
            String serverPort = EnvConfig.get("AC_ANDROID_SERVER_PORT");
            URI uri = new URI(String.format("http://%s:%s/", serverHost, serverPort));
            return uri.toURL();
        }
        catch(Exception e) {
            System.err.println("Error generating appium server URL: \n" + e.getMessage());
        }
        return null;
    }

    public static AppiumDriver getDriver() {
        return driver;
    }
}
