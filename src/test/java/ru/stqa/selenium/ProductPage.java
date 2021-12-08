package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class ProductPage extends Page {
    Application app = new Application();

    public ProductPage(WebDriver driver) throws IOException {
        super(driver);
    }

    public void addProduct() {
       checkingTheSizeFieldOfTheProduct(driver, By.cssSelector("div#box-product select"), By.cssSelector("div#box-product [name=add_cart_product]"), "Small");
    }

    public void waitingForAnItem(int iteration) {
       wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#cart span.quantity"), String.valueOf(iteration + 1)));
    }

    public void back() {
        driver.navigate().back();
    }

    public void checkingTheSizeFieldOfTheProduct(WebDriver driver, By locatorSize, By locatorAdd, String value) {
        if (app.isNotElementsPresent(driver, locatorSize)) {
            driver.findElement(locatorAdd).click();
        } else {
            Select select = new Select(driver.findElement(locatorSize));
            select.selectByValue(value);
            driver.findElement(locatorAdd).click();
        }
    }
}
