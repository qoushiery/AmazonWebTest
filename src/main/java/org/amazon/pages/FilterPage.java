package org.amazon.pages;

import org.amazon.Locators;
import org.amazon.SeleniumActions;
import org.amazon.utilities.Logger;
import org.openqa.selenium.WebDriver;

public class FilterPage {
    WebDriver driver;
    SeleniumActions actions;

    public FilterPage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.actions = new SeleniumActions(driver, logger);
    }

    public void applyFreeShippingFilter() {
        actions.click(Locators.FREE_SHIPPING_CHECKBOX);
    }

    public void applyNewConditionFilter() {
        actions.scrollToElementAndClickUsingJavaScript(Locators.NEW_CONDITION_CHECKBOX);
    }
}
