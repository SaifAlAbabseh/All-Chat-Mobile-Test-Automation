package util;


import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.BaseStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.io.FileUtils;
import org.apache.maven.surefire.shared.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class ScreenActions {

    public static ATUTestRecorder recorder;

    public static void takeScreenshot(String platform, String testCaseName) {
        File file = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("src/main/screenshots/" + platform + "-" + testCaseName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startScreenRecording() {
        ((CanRecordScreen) Driver.getDriver()).startRecordingScreen(
                new AndroidStartScreenRecordingOptions()
                        .withTimeLimit(Duration.ofMinutes(10))
        );
    }

    public static void stopScreenRecording() {
        String base64Video = ((CanRecordScreen) Driver.getDriver()).stopRecordingScreen();
        byte[] videoBytes = Base64.decodeBase64(base64Video);
        File videoFile = new File("src/main/recordings/test.mp4");
        try {
            FileOutputStream stream = new FileOutputStream(videoFile);
            stream.write(videoBytes);
            stream.close();
        }
        catch(Exception e) {
            System.err.println("Could not save the screen recording: \n" + e.getMessage());
        }
    }
}
