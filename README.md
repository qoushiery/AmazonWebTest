# Amazon Automation Project

This project automates various functionalities on the Amazon Egypt website using Selenium WebDriver.

## Project Structure

- `src/main/java/org/amazon/pages/`: Contains page classes for different pages on the Amazon website.
- `src/main/resources/`: Contains configuration files.
- `src/test/java/org/amazon/`: Contains test classes.
- `src/main/java/org/amazon/utilities/`: Contains utility classes for common functionalities.

## Prerequisites

- Java 11 or higher
- Maven
- ChromeDriver (or the WebDriver for your preferred browser)

## Configuration

Update the `src/main/resources/config.properties` file with your credentials and other configuration details:

```ini
url=https://www.amazon.eg/
mobilePhoneNumber=your-mobile-number
password=your-password
firstName=your-first-name
lastName=your-last-name
streetName=your-street-name
buildingNumber=your-building-number
cityArea=your-city-area
district=your-district
browser=chrome
headless=false
```

## Running the Tests

1. Clone the repository.
   git clone <your-repository-url>
   cd <your-repository-directory>
2. Install the dependencies.
   mvn clean install

3. Run the tests.
   mvn test 
4. update the config.properties file with your credentials and other configuration details.

## Test Cases

1. Login Test: Verifies the login functionality.
2. Home Page Navigation Test: Verifies navigation through the home page.
3. Filter Application Test: Verifies the application of filters.
4. Sorting Test: Verifies sorting functionality.
5. Product Addition Test: Verifies adding products below 15,000 EGP to the cart.
6. Checkout Process Test: Verifies the checkout process.
7. Total Amount Verification Test: Verifies the total amount during checkout.

## Utilities

The utilities package contains utility classes that provide common functionalities used across the project. These classes help in managing configurations, logging, and other reusable operations.

#### ConfigLoader.java

The `ConfigLoader` class is responsible for loading configuration properties from a file. It provides methods to retrieve various configuration values needed for the automation tests.

#### ScreenshotUtil.java

The ScreenshotUtil class provides methods to capture screenshots during test execution. It saves the screenshots in the `screenshots` directory.

#### WebDriverHandler.java

The `WebDriverHandler` class is responsible for initializing the WebDriver based on the browser specified in the configuration. It provides methods to get the WebDriver instance and close the WebDriver after test execution.

## Logging and Reporting

### Logger
Logs detailed information about the test execution.

### Extent Reports
Generates HTML reports with detailed information about the test execution, including test status, logs, and screenshots.

