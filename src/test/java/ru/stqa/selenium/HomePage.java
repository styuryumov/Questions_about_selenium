package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class HomePage extends Page {
    Properties prop;
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHome() throws IOException {
        prop = new Properties();
        prop.load(Application.class.getResourceAsStream("/testData.properties"));
        driver.get((prop.getProperty("baseUrl")) + "/" + (prop.getProperty("langu") + "/"));
    }

    public WebElement addProductSelection() {
        List<WebElement> product = driver.findElements(By.cssSelector("div#box-most-popular li.product"));
        return product.get(0);
    }
}
