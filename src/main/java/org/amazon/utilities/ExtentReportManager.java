package org.amazon.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter htmlReporter;
    private static ExtentTest test;

    public static void setUp(String reportName) {
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + reportName + ".html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Functional Testing");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Test User");
    }

    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void logFailure(WebDriver driver, String message) {
        test.fail(message);
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, test.getModel().getName());
        // Use relative path to add the screenshot to the report
        test.addScreenCaptureFromPath(screenshotPath, "Screenshot on failure");
    }

    public static void flush() {
        extent.flush();
    }
}
