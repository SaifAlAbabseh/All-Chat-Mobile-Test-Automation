package base;

import helpers.MainHelpers;
import io.appium.java_client.screenrecording.BaseStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import util.Driver;
import util.EnvConfig;
import util.Page;
import util.ScreenActions;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    protected final Page page = new Page();

    @Parameters({"platform", "includeAudio"})
    @BeforeClass(description = "Initialize the web driver, load the test data, and start recording a video")
    public void setUp(String platform, boolean includeAudio) throws MalformedURLException {
        URL serverUrl = MainHelpers.generateAppiumServerUrl();
        String avd = EnvConfig.get("AC_ANDROID_AVD");
        String appName = EnvConfig.get("AC_ANDROID_APP_NAME");
        new Driver(platform, serverUrl, avd, appName);
        //Start screen recording
        ScreenActions.startScreenRecording();
    }

    @Parameters({"platform"})
    @AfterMethod(description = "Take a screenshot for every failed test case")
    public void createScreenshotOnFailure(ITestResult result, Method method, String platform) {
        if(!result.isSuccess()) {
            ScreenActions.takeScreenshot(platform, method.getName());
        }
    }

    @AfterClass(description = "Stop the video record and quit from the web driver")
    public void tearDown() {
        //Stop screen recording
        ScreenActions.stopScreenRecording();
        Driver.getDriver().quit();
    }
}
