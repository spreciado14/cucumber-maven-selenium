package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleTest {
  
  @Test
  public void testGoogle() {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
    driver.get("https://www.google.com");
    assertEquals(driver.getTitle(), "Google");
    System.out.println("Title of page is ==> " + driver.getTitle());
    driver.quit();
  }
}
