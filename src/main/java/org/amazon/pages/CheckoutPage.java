package org.amazon.pages;

import org.amazon.Locators;
import org.amazon.SeleniumActions;
import org.amazon.utilities.Logger;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;
    SeleniumActions actions;

    public CheckoutPage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.actions = new SeleniumActions(driver, logger);
    }

    public void proceedToCheckout() {
        actions.click(Locators.CHECKOUT_BUTTON);
    }

    public void enterAddress(String name, String city, String state, String zip, String phone) {
        actions.type(Locators.FULL_NAME_FIELD, name);
        actions.type(Locators.CITY_AREA_FIELD, city);
        actions.type(Locators.DISTRICT_FIELD, state);
        actions.type(Locators.BUILDING_NAME_NO_FIELD, zip);
        actions.type(Locators.MOBILE_NUMBER_FIELD, phone);
        actions.click(Locators.USE_THIS_ADDRESS_BUTTON);
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
