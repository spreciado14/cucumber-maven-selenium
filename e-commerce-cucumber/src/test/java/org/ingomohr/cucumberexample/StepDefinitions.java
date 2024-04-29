package org.ingomohr.cucumberexample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.util.List;

public class StepDefinitions {

    private WebDriver driver;
    private String browserType = "chrome"; // default browser

    @Before("@chrome")
    public void setUpChrome() {
        browserType = "chrome";
    }

    @Before("@firefox")
    public void setUpFirefox() {
        browserType = "firefox";
    }

    @Before
    public void setUp() {
        driver = getDriver(browserType);
        driver.manage().deleteAllCookies();
        driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
    }

    private WebDriver getDriver(String browserType) {
        switch (browserType) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                // return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }


    @Given("the user is logged in and on the product page")
    public void userIsLoggedInAndOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username =  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_email")));
        username.sendKeys("email"); // replace with actual username
        WebElement continueButton = driver.findElement(By.id("continue")); // replace with actual continue button name
        continueButton.click();
        WebElement password =  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_password")));
        password.sendKeys("password!"); // replace with actual password
        WebElement loginButton = driver.findElement(By.id("signInSubmit")); // replace with actual login button name
        loginButton.click();
        // Wait for the page to load and check for the "Accounts & Lists" dropdown
        WebElement accountsDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-link-accountList")));
        assertNotNull(accountsDropdown, "Login failed.");
    }

    @When("the user adds {int} of {string} to the cart")
    public void addToCart(int quantity, String product) {
        // Search for the product
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys(product);
        searchBox.submit();
    
        // Wait for the search results to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".s-result-list"))); 
    
        // Click on the first product
        WebElement firstProduct = driver.findElement(By.cssSelector(".s-result-list .s-result-item .a-link-normal.a-text-normal")); 
        firstProduct.click();
    
        // Add the product to the cart
        for (int i = 0; i < quantity; i++) {
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button"))); 
            addToCartButton.click();
            // Go back to the product page to add more items
            driver.navigate().back();
        }
    }
    @Then("the cart should contain {int} of {string}")
    public void verifyCartContents(int quantity, String product) {  
        // Navigate to the cart
        driver.get("https://www.amazon.com/gp/cart/view.html");

        // Find the cart item
        // Note: Amazon does not provide a straightforward way to find a specific item in the cart by name.
        // This is a simplified example and may not work perfectly for all products and situations.
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".sc-list-item-content"));
        boolean found = false;
        for (WebElement cartItem : cartItems) {
            if (cartItem.getText().contains(product)) {
                WebElement quantityElement = cartItem.findElement(By.cssSelector(".a-dropdown-prompt"));
                int actualQuantity = Integer.parseInt(quantityElement.getText());
                assertEquals(quantity, actualQuantity);
                found = true;
                break;
            }
        }
        assertTrue(found, "The product was not found in the cart.");
    }

    @Given("the user is logged in and on the cart page")
    public void theUserIsLoggedInAndOnTheCartPage() {
        // code to log in and navigate to the cart page
        // this should throw an exception or fail an assertion if it doesn't succeed
        fail("Not yet implemented");
    }

    @When("the user updates the shipping detail")
    public void theUserUpdatesTheShippingDetail() {
        // code to update the shipping detail
        // this should throw an exception or fail an assertion if it doesn't succeed
        fail("Not yet implemented");
    }

    @Then("check if the update is valid on the cart page")
    public void checkIfTheUpdateIsValidOnTheCartPage() {
        // code to check the shipping detail on the cart page
        // this should throw an exception or fail an assertion if the check fails
        fail("Not yet implemented");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            fail("Failed");
            // code to take screenshot
        }
        driver.quit();
    }
}
