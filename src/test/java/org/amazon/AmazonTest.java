package org.amazon;

import org.amazon.pages.*;
import org.amazon.utilities.ConfigLoader;
import org.amazon.utilities.ExtentReportManager;
import org.amazon.utilities.Logger;
import org.amazon.utilities.WebDriverHandler;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AmazonTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    FilterPage filterPage;
    SortPage sortPage;
    ProductPage productPage;
    CheckoutPage checkoutPage;
    MyAddressesPage myAddressesPage;
    ConfigLoader config;
    Logger logger;
    SoftAssert softAssert;
    @BeforeSuite
    public void setUpSuite() {
        ExtentReportManager.setUp("AmazonTestReport");
    }

    @BeforeClass
    public void setUp() {
        config = new ConfigLoader("src/main/resources/config.properties");
        String browser = config.getBrowser();
        boolean headless = config.isHeadless();

        WebDriverHandler handler = new WebDriverHandler();
        driver = handler.initializeDriver(browser, headless);

        // Initialize logger
        logger = new Logger(AmazonTest.class);

        driver.get(config.getUrl());
        loginPage = new LoginPage(driver, logger);
        homePage = new HomePage(driver, logger);
        filterPage = new FilterPage(driver, logger);
        sortPage = new SortPage(driver, logger);
        productPage = new ProductPage(driver, logger);
        checkoutPage = new CheckoutPage(driver, logger);
        myAddressesPage = new MyAddressesPage(driver, logger);
        softAssert = new SoftAssert();

    }

    @Test
    public void testLogin() {
        ExtentReportManager.createTest("testLogin");
        try {
            loginPage.openLoginPage();
            Assert.assertTrue(loginPage.isEmailFieldDisplayed(), "Login page did not open successfully");
            loginPage.enterEmail(config.getMobilePhoneNumber());
            //Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Enter Password page did not open successfully");
            loginPage.enterPassword(config.getPassword());
            Assert.assertEquals(homePage.getLoggedInUserName(),"Hello, "+ config.getFirstName(), "Login failed");
            System.out.println(config.getFirstName() + " logged in successfully");
            ExtentReportManager.getTest().pass("Login test passed");

        } catch (Exception e) {
            ExtentReportManager.logFailure(driver, "Login test failed");
            logger.logError("Login test failed", e);
            softAssert.fail("Login test failed", e);
        }
    }

    @Test(dependsOnMethods = "testLogin")
    public void testHomePageNavigation() {
        ExtentReportManager.createTest("testHomePageNavigation");
        try {
            //Validate My Cart Is Empty or Remove All Items if Existed
            checkoutPage.proceedToCheckout();
            checkoutPage.emptyCartFromAllItems();
            ExtentReportManager.getTest().info("Cart Is Empty!");

            //Remove any existing address
            homePage.navigateToMyAccountSetting();
            myAddressesPage.navigateToMyAddresses();
            myAddressesPage.deleteSavedAddress();

            //Navigate to Home Page
            homePage.navigateToHomePage();
            //Open All Menu
            homePage.openAllMenu();
            Assert.assertEquals(homePage.getHeaderMenuCustomerName(), "Hello, "+ config.getFirstName(), "Failed To Open All Menu");
            homePage.clickSeeAll();
            homePage.clickVideoGames();
            softAssert.assertEquals(homePage.getSubMenuHeading(), "Video Games", "Failed To Click on Video Games Link from the Main Menu");
            homePage.clickAllVideoGames();
            softAssert.assertEquals(homePage.getPageBanner(), "Video Games", "Failed To Click on All Video Games Link from the Sub Menu");
            ExtentReportManager.getTest().pass("HomePage navigation test passed");
        } catch (Exception e) {
            ExtentReportManager.logFailure(driver, "HomePage navigation test failed");
            logger.logError("HomePage navigation test failed", e);
            softAssert.fail("HomePage navigation test failed", e);
        }
    }

    @Test(dependsOnMethods = "testHomePageNavigation")
    public void testFilterApplication() {
        ExtentReportManager.createTest("testFilterApplication");
        try {
            filterPage.applyFreeShippingFilter();
            filterPage.applyNewConditionFilter();
            ExtentReportManager.getTest().pass("Filter application test passed");
        } catch (Exception e) {
            ExtentReportManager.logFailure(driver, "Filter application test failed");
            logger.logError("Filter application test failed", e);
            softAssert.fail("Filter application test failed", e);
        }
    }

    @Test(dependsOnMethods = "testFilterApplication")
    public void testSorting() {
        ExtentReportManager.createTest("testSorting");
        try {
            sortPage.sortByPriceHighToLow();
            ExtentReportManager.getTest().pass("Sorting test passed");
        } catch (Exception e) {
            ExtentReportManager.logFailure(driver, "Sorting test failed");
            logger.logError("Sorting test failed", e);
            softAssert.fail("Sorting test failed", e);
        }
    }

    @Test(dependsOnMethods = "testSorting")
    public void testProductAddition() {
        ExtentReportManager.createTest("testProductAddition");
        try {

            productPage.addProductsBelow15kEGP();
            softAssert.assertTrue(productPage.verifyProductsInCart(), "No products were added to the cart.");
            ExtentReportManager.getTest().pass("Product addition test passed");
        } catch (Exception e) {
            ExtentReportManager.logFailure(driver, "Product addition test failed");
            logger.logError("Product addition test failed", e);
            softAssert.fail("Product addition test failed", e);
        }
    }

    @Test(dependsOnMethods = "testProductAddition")
    public void testCheckoutProcess() {
        ExtentReportManager.createTest("testCheckoutProcess");
        try {
            checkoutPage.proceedToCheckout();
            softAssert.assertEquals(checkoutPage.getShoppingCartHeading().trim(), "Shopping Cart", "Failed to proceed to checkout");
            checkoutPage.proceedToBuy();
            softAssert.assertTrue(checkoutPage.secureCheckoutButtonIsDisplayed(), "Failed to proceed to buy");
            checkoutPage.addNewAddress();
            softAssert.assertTrue(checkoutPage.addNewAddressPopupIsOpened(), "Failed to add new address");
            checkoutPage.enterAddress(config.getFirstName(), config.getLastName(), config.getMobilePhoneNumber(), config.getStreetName(), config.getBuildingNumber(), config.getCityArea(), config.getDistrict());
            checkoutPage.chooseCashOnDelivery();
            ExtentReportManager.getTest().pass("Checkout process test passed");
        } catch (Exception e) {
            ExtentReportManager.logFailure(driver, "Checkout process test failed");
            logger.logError("Checkout process test failed", e);
            softAssert.fail("Checkout process test failed", e);
        }
    }

    @Test(dependsOnMethods = "testCheckoutProcess")
    public void testTotalAmountVerification() {
        ExtentReportManager.createTest("testTotalAmountVerification");
        try {
            double totalAmount = checkoutPage.getTotalAmount();
            double shippingFee = checkoutPage.getShippingFee();
            double expectedTotal = checkoutPage.calculateExpectedTotal(totalAmount, shippingFee);
            Assert.assertTrue(checkoutPage.verifyTotalAmount(expectedTotal), "The total amount is incorrect.");
            //checkoutPage.placeOrder();
            ExtentReportManager.getTest().pass("Total amount verification test passed");
        } catch (Exception e) {
            ExtentReportManager.logFailure(driver, "Total amount verification test failed");
            logger.logError("Total amount verification test failed", e);
            softAssert.fail("Total amount verification test failed", e);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ExtentReportManager.flush();
    }
}
