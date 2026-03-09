package base;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import util.Driver;
import util.Screen;
import util.ScreenActions;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class TestBase {

    protected final Screen screen = new Screen();

    @Parameters({"platform"})
    @BeforeClass(description = "Initialize the web driver, load the test data, and start recording a video")
    public void setUp(String platform) throws MalformedURLException {
        new Driver(platform);
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
