package hooks;

import io.cucumber.java.*;
import util.Driver;
import util.EnvConfig;
import util.ScreenActions;

public class BaseHooks {

    @Before
    public static void setUp() {
        try {
            Driver.initDriver();
        }
        catch(Exception e) {
            System.err.println("Could not start web driver, ERROR: " + e.getMessage());
        }
        //Start screen recording
        ScreenActions.startScreenRecording();
    }

    @After(order = 1)
    public static void createScreenshotOnFailure(Scenario scenario) {
        if(scenario.isFailed()) {
            String platform = EnvConfig.get("platform").toLowerCase();
            ScreenActions.takeScreenshot(platform, scenario.getName());
        }
    }

    @After(order = 0)
    public static void tearDown() {
        //Stop screen recording
        ScreenActions.stopScreenRecording();
        Driver.quitDriver();
    }
}
