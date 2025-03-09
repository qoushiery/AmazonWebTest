package org.amazon;

import org.amazon.utilities.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumActions {
    WebDriver driver;
    WebDriverWait wait;
    Logger logger;

    public SeleniumActions(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.logger = logger;
    }

    public void click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.log("Clicked on element with locator: " + locator);
        } catch (Exception e) {
            logger.logError("Failed to click on element with locator: " + locator, e);
        }
    }
    public void click(WebElement webElement) {
        try {
            webElement.click();
            logger.log("Clicked on element : " + webElement);
        } catch (Exception e) {
            logger.logError("Failed to click on element: " + webElement, e);
        }
    }

    public void clickUsingJavaScript(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            logger.log("Clicked on element with locator: " + locator + " using JavaScript.");
        } catch (Exception e) {
            logger.logError("Failed to click on element with locator: " + locator + " using JavaScript.", e);
        }
    }

    public void scrollToElementAndClickUsingJavaScript(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            logger.log("Scrolled to and clicked on element with locator: " + locator + " using JavaScript.");
        } catch (Exception e) {
            logger.logError("Failed to scroll to and click on element with locator: " + locator + " using JavaScript.", e);
        }
    }

    public void scrollToElementAndClickUsingJavaScript(WebElement webElement) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
            logger.log("Scrolled to and clicked on element with web Element: " + webElement + " using JavaScript.");
        } catch (Exception e) {
            logger.logError("Failed to scroll to and click on element with Web Element: " + webElement + " using JavaScript.", e);
        }
    }

    public void scrollToElementAndClickUsingJavaScriptWithRetry(By locator, int retries) {
        for (int i = 0; i < retries; i++) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                wait.until(ExpectedConditions.elementToBeClickable(locator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                logger.log("Scrolled to and clicked on element with locator: " + locator + " using JavaScript.");
                return;
            } catch (Exception e) {
                logger.logError("Attempt " + (i + 1) + " failed to scroll to and click on element with locator: " + locator + " using JavaScript.", e);
            }
        }
        logger.logError("Failed to scroll to and click on element with locator: " + locator + " using JavaScript after " + retries + " attempts.");
    }

    public void type(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.click();
            element.sendKeys(text);
            logger.log("Typed text '" + text + "' into element with locator: " + locator);
        } catch (Exception e) {
            logger.logError("Failed to type text '" + text + "' into element with locator: " + locator, e);
        }
    }

    public void selectByVisibleText(By locator, String visibleText) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.sendKeys(visibleText);
            logger.log("Selected visible text '" + visibleText + "' in element with locator: " + locator);
        } catch (Exception e) {
            logger.logError("Failed to select visible text '" + visibleText + "' in element with locator: " + locator, e);
        }
    }

    public List<WebElement> findElements(By locator) {
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            logger.log("Found elements with locator: " + locator);
            return elements;
        } catch (Exception e) {
            logger.logError("Failed to find elements with locator: " + locator, e);
            return null;
        }
    }

    public WebElement findElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.log("Found element with locator: " + locator);
            return element;
        } catch (Exception e) {
            logger.logError("Failed to find element with locator: " + locator, e);
            return null;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            boolean isPresent = !driver.findElements(locator).isEmpty();
            logger.log("Checked presence of element with locator: " + locator + ". Presence: " + isPresent);
            return isPresent;
        } catch (Exception e) {
            logger.logError("Failed to check presence of element with locator: " + locator, e);
            return false;
        }
    }

    public String getText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = element.getText();
            logger.log("Retrieved text from element with locator: " + locator + ". Text: " + text);
            return text;
        } catch (Exception e) {
            logger.logError("Failed to retrieve text from element with locator: " + locator, e);
            return null;
        }
    }

    public void waitForElementToBeVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.log("Element with locator: " + locator + " is visible");
        } catch (Exception e) {
            logger.logError("Failed to wait for element with locator: " + locator + " to be visible", e);
        }
    }

    public void waitForElementToBeClickable(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            logger.log("Element with locator: " + locator + " is clickable");
        } catch (Exception e) {
            logger.logError("Failed to wait for element with locator: " + locator + " to be clickable", e);
        }


    }

    public void selectByIndex(By locator, int index) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(element);
            select.selectByIndex(index);
            logger.log("Selected index '" + index + "' in element with locator: " + locator);
        } catch (Exception e) {
            logger.logError("Failed to select index '" + index + "' in element with locator: " + locator, e);
        }
    }
}
