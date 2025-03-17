package org.amazon.pages;

import org.amazon.Locators;
import org.amazon.SeleniumActions;
import org.amazon.utilities.Logger;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    SeleniumActions actions;

    public LoginPage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.actions = new SeleniumActions(driver, logger);
    }

    public void openLoginPage() {
        actions.click(Locators.SIGN_IN_BUTTON);
    }

    public void enterEmail(String email) {
        actions.type(Locators.EMAIL_FIELD, email);
        actions.click(Locators.CONTINUE_BUTTON);
    }

    public void enterPassword(String password) {
        actions.type(Locators.PASSWORD_FIELD, password);
        actions.click(Locators.SUBMIT_BUTTON);
    }
    public boolean isEmailFieldDisplayed() {
        return actions.isElementPresent(Locators.EMAIL_FIELD);
    }

    public boolean isPasswordFieldDisplayed() {
        return actions.isElementPresent(Locators.PASSWORD_FIELD);
    }
}
