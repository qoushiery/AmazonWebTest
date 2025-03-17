package org.amazon.pages;

import org.amazon.Locators;
import org.amazon.SeleniumActions;
import org.amazon.utilities.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyAddressesPage {
    WebDriver driver;
    SeleniumActions actions;
    Logger logger;

    public MyAddressesPage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.actions = new SeleniumActions(driver, logger);
        this.logger = logger;
    }

    public void navigateToMyAddresses(){
        actions.click(Locators.YOUR_ADDRESSES_CARD);
    }
    public void deleteSavedAddress(){
        List<WebElement> savedAddresses = driver.findElements(Locators.SAVED_ADDRESS_CARD);
        if (savedAddresses.isEmpty()) {
            logger.log("No saved addresses found.");
        } else {
            for (WebElement address : savedAddresses) {
                try {
                    actions.click(Locators.REMOVE_SAVED_ADDRESS_BUTTON);
                    actions.clickUsingJavaScript(Locators.CONFIRM_ADDRESS_DELETION);
                    // Wait for the address to be removed
                    Thread.sleep(1000); // Adjust the sleep time as needed
                } catch (Exception e) {
                    logger.logError("Failed to delete saved address.", e);
                }
            }
            logger.log("All saved addresses have been removed.");
        }
    }
}
