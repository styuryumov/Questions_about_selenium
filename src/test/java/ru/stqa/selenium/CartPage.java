package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends Page{
    WebElement element;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        driver.findElement(By.cssSelector("div#cart a.link")).click();
    }

    public List<WebElement> addButton() {
        return driver.findElements(By.cssSelector("div#box-checkout-cart [name=remove_cart_item]"));
    }

    public void getItem(){
       element = driver.findElement(By.cssSelector("div#checkout-summary-wrapper tr"));
    }

    public WebElement remove() {
       return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#box-checkout-cart [name=remove_cart_item]")));
    }

    public void checkingForMissingElement() {
        wait.until(ExpectedConditions.stalenessOf(element));
    }
}
