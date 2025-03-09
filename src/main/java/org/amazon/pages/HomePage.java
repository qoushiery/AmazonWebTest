package org.amazon.pages;

import org.amazon.Locators;
import org.amazon.SeleniumActions;
import org.amazon.utilities.Logger;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    SeleniumActions actions;

    public HomePage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.actions = new SeleniumActions(driver, logger);
    }

    public void openAllMenu() {
        actions.click(Locators.ALL_MENU_BUTTON);
    }

    public String getHeaderMenuCustomerName()
    {
        return actions.getText(Locators.HEADER_MENU_CUSTOMER_NAME);
    }
    public void clickSeeAll() {
        actions.waitForElementToBeVisible(Locators.SEE_ALL_LINK);
        actions.waitForElementToBeClickable(Locators.SEE_ALL_LINK);
        actions.scrollToElementAndClickUsingJavaScriptWithRetry(Locators.SEE_ALL_LINK, 3);
    }

    public void clickVideoGames() {
        actions.waitForElementToBeVisible(Locators.VIDEO_GAMES_LINK);
        actions.scrollToElementAndClickUsingJavaScript(Locators.VIDEO_GAMES_LINK);
    }

    public void clickAllVideoGames() {
        actions.click(Locators.ALL_VIDEO_GAMES_LINK);
    }
    public String getLoggedInUserName() {
        return actions.getText(Locators.SIGN_IN_BUTTON);
    }

    public String getSubMenuHeading() {
        return actions.getText(Locators.SUB_MENU_HEADING).trim();
    }

    public boolean isVideoGameHeadingPresent() {
        return actions.isElementPresent(Locators.VIDEO_GAME_HEADING);
    }
}
