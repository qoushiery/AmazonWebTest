package org.amazon.pages;

import org.amazon.Locators;
import org.amazon.SeleniumActions;
import org.amazon.utilities.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutPage {
    WebDriver driver;
    SeleniumActions actions;
    Logger logger;

    public CheckoutPage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.actions = new SeleniumActions(driver, logger);
        this.logger = logger;

    }

    public void proceedToCheckout() {
        actions.scrollToElementAndClickUsingJavaScript(Locators.CHECKOUT_BUTTON);
    }

    public void proceedToBuy()
    {
        actions.click(Locators.PROCEED_TO_BUY_BUTTON);
    }

    public void addNewAddress() {
        actions.click(Locators.ADD_NEW_ADDRESS_BUTTON);
    }

    public String getShoppingCartHeading(){
        return actions.getText(Locators.SHOPPING_CART_HEADING);
    }

    public boolean secureCheckoutButtonIsDisplayed() {
        return actions.isElementPresent(Locators.SECURE_CHECKOUT_BUTTON);
    }

    public boolean addNewAddressPopupIsOpened() {
        return actions.isElementPresent(Locators.ADD_NEW_ADDRESS_POPUP);
    }

    public void enterAddress(String firstName, String lastName,String phone,String streetName,String buildingNameNo,String city,String district) {
        actions.type(Locators.FULL_NAME_FIELD, firstName+" "+lastName);
        actions.type(Locators.MOBILE_NUMBER_FIELD, phone);
        actions.type(Locators.STREET_NAME_FIELD, streetName);
        actions.type(Locators.BUILDING_NAME_NO_FIELD,buildingNameNo);
        actions.type(Locators.CITY_AREA_FIELD, city);
        actions.waitForElementToBeVisible(By.cssSelector("#address-ui-widgets-autocompleteResultsContainer li"));
        actions.scrollToElementAndClickUsingJavaScriptWithRetry(By.cssSelector("#address-ui-widgets-autocompleteResultsContainer li"),3);

        actions.type(Locators.DISTRICT_FIELD,district);
        actions.scrollToElementAndClickUsingJavaScript(Locators.HOME_ADDRESS_TYPE_RADIO_BUTTON);
        actions.scrollToElementAndClickUsingJavaScript(Locators.USE_THIS_ADDRESS_BUTTON);
    }

    public void emptyCartFromAllItems(){
        while (true) {
            List<WebElement> deleteButtons = driver.findElements(Locators.DELETE_ITEM_FROM_CART_BUTTON);
            if (deleteButtons.isEmpty()) {
                logger.log("Cart is already empty.");
                break;
            } else {
                for (WebElement deleteButton : deleteButtons) {
                    try {
                        actions.clickUsingJavaScript(deleteButton);
                        // Wait for the item to be removed from the cart
                        Thread.sleep(1000); // Adjust the sleep time as needed
                    } catch (StaleElementReferenceException e) {
                        logger.logError("StaleElementReferenceException caught. Retrying...", e);
                    } catch (InterruptedException e) {
                        logger.logError("InterruptedException caught.", e);
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        logger.log("All items have been removed from the cart.");
    }

    public void chooseCashOnDelivery() {
        actions.click(Locators.PAYMENT_METHOD_CASH);
    }

    public void placeOrder() {
        actions.click(Locators.PLACE_YOUR_ORDER_BUTTON);
    }

    public double getTotalAmount() {
        String totalAmountText = actions.findElement(Locators.TOTAL_AMOUNT).getText().replace(",", "").replace("EGP", "").trim();
        return Double.parseDouble(totalAmountText);
    }

    public double getShippingFee() {
        if (actions.isElementPresent(Locators.SHIPPING_FEE)) {
            String shippingFeeText = actions.findElement(Locators.SHIPPING_FEE).getText().replace(",", "").replace("EGP", "").trim();
            return Double.parseDouble(shippingFeeText);
        }
        return 0.0;
    }

    public double calculateExpectedTotal(double itemTotal, double shippingFee) {
        return itemTotal + shippingFee;
    }

    public boolean verifyTotalAmount(double expectedTotal) {
        return getTotalAmount() == expectedTotal;
    }
}
