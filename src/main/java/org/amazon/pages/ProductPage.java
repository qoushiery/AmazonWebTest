package org.amazon.pages;

import org.amazon.Locators;
import org.amazon.SeleniumActions;
import org.amazon.utilities.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {
    WebDriver driver;
    SeleniumActions actions;
    Logger logger;
    public ProductPage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.actions = new SeleniumActions(driver, logger);
        this.logger = logger;
    }

    public void addProductsBelow15kEGP() {
        boolean productsFound = false;
        do {
            List<WebElement> prices = actions.findElements(Locators.PRODUCT_PRICE);
            logger.log("Found " + prices.size() + " products on the page.");
            for (WebElement price : prices) {
                System.out.println(price.getText());
            }
            for (WebElement price : prices) {
                String priceText = price.getText().replace(",", "");
                int priceValue;

                try {
                    priceValue = Integer.parseInt(priceText);
                } catch (NumberFormatException e) {
                    logger.logError("Failed to parse price: " + priceText, e);
                    continue;
                }

                logger.log("Product price: " + priceValue);

                if (priceValue < 15000) {
                    try {
                        WebElement productContainer = price.findElement(By.xpath("ancestor::div[@data-asin]"));
                        WebElement addToCartButton = productContainer.findElement(By.xpath(".//input[contains(@name, 'addToCart')]"));                        if (addToCartButton.isDisplayed()) {
                            actions.scrollToElementAndClickUsingJavaScript(addToCartButton);
                            productsFound = true;
                            logger.log("Added product with price: " + priceText + " to cart.");
                        }
                        else {
                            logger.log("Add to Cart button is not displayed for product with price: " + priceText);
                        }
                    } catch (NoSuchElementException e) {
                        logger.logError("Add to Cart button not found for product with price: " + priceText);
                    }
                }
                }
                if (!productsFound && actions.isElementPresent(Locators.NEXT_PAGE_BUTTON)) {
                    logger.log("Navigating to the next page.");
                    actions.click(Locators.NEXT_PAGE_BUTTON);
                } else {
                    break;
                }
            }
            while (!productsFound) ;
        }
    public boolean verifyProductsInCart() {
        String cartCountText = actions.findElement(Locators.CART_ITEM_COUNT).getText();
        return Integer.parseInt(cartCountText) > 0;
    }
}
