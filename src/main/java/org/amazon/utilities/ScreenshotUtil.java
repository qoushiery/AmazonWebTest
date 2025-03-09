package org.amazon.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String pathDestination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
        File destination = new File(pathDestination);
        try {
            // Create the screenshots directory if it does not exist
            if (!destination.getParentFile().exists()) {
                destination.getParentFile().mkdirs();
            }
            FileHandler.copy(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathDestination;
    }
}
