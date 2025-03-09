package org.amazon;

import org.openqa.selenium.By;

public class Locators {
    public static final By SIGN_IN_BUTTON = By.id("nav-link-accountList-nav-line-1");
    public static final By EMAIL_FIELD = By.id("ap_email");
    public static final By CONTINUE_BUTTON = By.id("continue");
    public static final By PASSWORD_FIELD = By.id("ap_password");
    public static final By SUBMIT_BUTTON = By.id("signInSubmit");
    public static final By ALL_MENU_BUTTON = By.id("nav-hamburger-menu");
    public static final By SEE_ALL_LINK = By.xpath("//*[@id='hmenu-content']/ul[1]/li[14]/a[1]");
    public static final By HEADER_MENU_CUSTOMER_NAME = By.xpath("//*[@id='hmenu-customer-name']/b");
    public static final By SUB_MENU_HEADING = By.xpath("//*[@id='hmenu-content']/ul[16]/li[2]/div");
    public static final By VIDEO_GAME_HEADING = By.xpath("//h1[text()='Video Games']");
    public static final By VIDEO_GAMES_LINK = By.xpath("//*[@id='hmenu-content']/ul[1]/ul/li[11]/a");
    public static final By ALL_VIDEO_GAMES_LINK = By.xpath("//*[@id='hmenu-content']/ul[32]/li[3]/a");
    public static final By FREE_SHIPPING_CHECKBOX = By.xpath("//span[text()='Free Shipping']/preceding-sibling::div");
    public static final By NEW_CONDITION_CHECKBOX = By.xpath("//span[text()='New']");
    public static final By SORT_MENU = By.id("s-result-sort-select");
    public static final By PRODUCT_PRICE = By.cssSelector(".a-price-whole");
    public static final By NEXT_PAGE_BUTTON = By.cssSelector(".s-pagination-next");
    public static final By ADD_TO_CART_BUTTON = By.id("add-to-cart-button");
    public static final By CART_ITEM_COUNT = By.id("nav-cart-count");
    public static final By CHECKOUT_BUTTON = By.id("hlb-ptc-btn-native");
    public static final By FULL_NAME_FIELD = By.id("address-ui-widgets-enterAddressFullName");
    public static final By STREET_NAME_FIELD = By.id("address-ui-widgets-enterAddressLine1");
    public static final By CITY_AREA_FIELD = By.id("address-ui-widgets-enterAddressCity");
    public static final By DISTRICT_FIELD = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    public static final By BUILDING_NAME_NO_FIELD = By.id("address-ui-widgets-enter-building-name-or-number");
    public static final By MOBILE_NUMBER_FIELD = By.id("address-ui-widgets-enterAddressPhoneNumber");
    public static final By HOME_ADDRESS_TYPE_RADIO_BUTTON = By.id("address-ui-widgets-addr-details-res-radio-input");
    public static final By USE_THIS_ADDRESS_BUTTON = By.cssSelector("input[name='shipToThisAddress']");
    public static final By PAYMENT_METHOD_CASH = By.xpath("//input[@value='Cash on Delivery']");
    public static final By PLACE_YOUR_ORDER_BUTTON = By.name("placeYourOrder1");
    public static final By TOTAL_AMOUNT = By.id("sc-subtotal-amount-buybox");
    public static final By SHIPPING_FEE = By.xpath("//span[@id='sc-subtotal-amount-buybox']//following::span[contains(text(),'Shipping')]");
}
