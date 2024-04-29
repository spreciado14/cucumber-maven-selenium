package org.ingomohr.cucumberexample;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

    private WebDriver driver;
    
    // This step opens the login page in a new browser window
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver = new ChromeDriver();
        driver.get("https://anupdamoda.github.io/AceOnlineShoePortal/SignIn.html"); // replace with your actual login URL
    }
    
    // This step enters a valid username and password into the respective fields
    @When("the user enters a valid username and password")
    public void the_user_enters_a_valid_username_and_password() {
        WebElement username = driver.findElement(By.id("usr")); // replace with actual username field name
        WebElement password = driver.findElement(By.id("pwd")); // replace with actual password field name
        username.sendKeys("salpre1112"); // replace with actual username
        password.sendKeys("Cocacola24"); // replace with actual password
    }

    // This step simulates a click on the login button
    @And("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        WebElement loginButton = driver.findElement(By.xpath("/html/body/center[1]/div/form/input")); // replace with actual login button name
        loginButton.click();
    }

    // This step checks if the user has been redirected to the homepage after successful login
    @Then("the user should be redirected to the homepage")
    public void the_user_should_be_redirected_to_the_homepage() {
        String expectedUrl = "https://anupdamoda.github.io/AceOnlineShoePortal/ShoeTypes.html"; // replace with your actual home URL
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    // This step checks if a welcome message is displayed after successful login
    @And("a welcome message should be displayed")
    public void a_welcome_message_should_be_displayed() {
        WebElement welcomeMessage = driver.findElement(By.id("ShoePortalTitle")); // replace with actual welcome message element id
        assertTrue(welcomeMessage.isDisplayed());
    }

    // This step closes the browser window after each scenario
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}