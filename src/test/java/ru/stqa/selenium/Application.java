package ru.stqa.selenium;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Application {

    public WebDriver driver;
    public Properties prop;
    public WebDriverWait wait;
    public static int seconds = 10;

    private final HomePage home;
    private final ProductPage product;
    private final CartPage cart;

    public Application() throws IOException {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        home = new HomePage(driver);
        product = new ProductPage(driver);
        cart = new CartPage(driver);
    }
    public void quit() {
        driver.quit();
    }
    public void openHomePage() throws IOException {
        home.openHome();
    }

    public void productSelection() {
        home.addProductSelection().click();
    }

    public void addProductToCart(int iteration) {
        product.addProduct();
        product.waitingForAnItem(iteration);
        product.back();
    }

    public void removingProductFromTheCart() {
        cart.openCart();
        cart.addButton();
        int iteration = cart.addButton().size();
        for (int i = 0; i < iteration; i++) {
            cart.getItem();
            cart.remove().click();
            cart.checkingForMissingElement();
        }
        Assert.assertTrue(isElementsPresent(driver, By.cssSelector("div#checkout-cart-wrapper a")));
    }



    boolean isElementsPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
    }
    boolean isNotElementsPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return driver.findElements(locator).size() == 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        }
    }
}
