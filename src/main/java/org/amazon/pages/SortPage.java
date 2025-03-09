package org.amazon.pages;

import org.amazon.Locators;
import org.amazon.SeleniumActions;
import org.amazon.utilities.Logger;
import org.openqa.selenium.WebDriver;

public class SortPage {

    WebDriver driver;
    SeleniumActions actions;

    public SortPage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.actions = new SeleniumActions(driver, logger);
    }

    public void sortByPriceHighToLow() {
        actions.waitForElementToBeVisible(Locators.SORT_MENU);
        actions. selectByIndex(Locators.SORT_MENU, 2);

    }
}
