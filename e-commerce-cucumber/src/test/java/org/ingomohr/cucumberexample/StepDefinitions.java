package org.ingomohr.cucumberexample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;

import dev.failsafe.internal.util.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;

public class StepDefinitions {

    private WebDriver driver;

    @Given("the user is logged in and on the product page")
    public void userIsLoggedInAndOnProductPage() {
        driver = new ChromeDriver();
        driver.get("https://www.etsy.com/login");
        WebElement username = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/div[1]/div/div[2]/input")); // replace with actual username field name
        WebElement password = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/div[1]/div/div[3]/input")); // replace with actual password field name
        username.sendKeys("SafelgPreciado@gmail.com"); // replace with actual username
        password.sendKeys("Cocacola25"); // replace with actual password
        WebElement loginButton = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/div[1]/div/div[7]/div/button")); // replace with actual login button name
        loginButton.click();

    }

    @When("the user adds {int} of {string} to the cart")
    public void addToCart(int quantity, String product) {
        // Search for the product
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='search_query']")));
        searchBox.sendKeys(product);
        searchBox.submit();
    
        // Wait for the search results to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".search-listing-group"))); // replace with actual search results container
    
        // Click on the first product
        WebElement firstProduct = driver.findElement(By.cssSelector(".search-listing-group .listing-link")); // replace with actual product link selector
        firstProduct.click();
    
        // Add the product to the cart
        for (int i = 0; i < quantity; i++) {
            WebElement addToCartButton = driver.findElement(By.name("add-to-cart")); // replace with actual add to cart button name
            addToCartButton.click();
        }
    }
    
    @Then("the cart should contain {int} of {string}")
    public void verifyCartContents(int quantity, String product) {  
        // Navigate to the cart
        driver.get("https://www.etsy.com/cart");

        // Find the cart item
        WebElement cartItemElement = driver.findElement(By.name(product)); // replace with actual cart item name
        WebElement quantityElement = cartItemElement.findElement(By.name("quantity")); // replace with actual quantity field name

        // Verify the quantity
        int actualQuantity = Integer.parseInt(quantityElement.getText());
        assertEquals(quantity, actualQuantity);
    }
}