package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.Color;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public static WebDriver driver;
    public Properties prop;
    public static WebDriverWait wait;
    public Random rdm = new Random();
    public static int seconds = 10;

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

    public void checkbox(WebDriver driver, By locator, int number) {
        List<WebElement> list = driver.findElements(locator);
        for (WebElement chec : list) {
            if (chec.isSelected() == true) {
                chec.click();
            }
        }
        list.get(number).click();
    }

    public int size(String string) {
        String[] strArr = string.split("");
        if (Objects.equals(strArr[1], ".")) {
            strArr[1] = strArr[2];
        }
        return Integer.parseInt(strArr[0] + strArr[1]);
    }

    boolean sizeComparison(int size1, int size2) {
        return size1 < size2;
    }

    boolean color(Color color) {
        int red = color.getColor().getRed();
        int green = color.getColor().getGreen();
        int blue = color.getColor().getBlue();

        if ((red == green) && (green == blue)) {
            return true;
        } else if ((red != green) && (green == blue)) {
            return true;
        } else {
            return false;
        }
    }

    @Before
    public void start() throws IOException {
        prop = new Properties();
        prop.load(TestBase.class.getResourceAsStream("/testData.properties"));

        try {
            driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"), new ChromeOptions());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            return;
        }
        if ("chrome".equals(prop.getProperty("browser"))) {
            /*DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "chrome");
            сapabilities.setCapability("platform", "WINDOWS");
            //capabilities.setCapability("browserVersion", "96");*/
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-fullscreen");

            /*try {
                driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 10);
        } else if ("firefox".equals(prop.getProperty("browser"))) {
            FirefoxOptions options = new FirefoxOptions();
            //options.addArguments("start-fullscreen");
            //options.getPlatform();
            /*try {
                driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
            driver = new FirefoxDriver();
            wait = new WebDriverWait(driver, 10);
        } else if ("ie".equals(prop.getProperty("browser"))) {
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.addCommandSwitches("start-fullscreen");
            driver = new InternetExplorerDriver(options);
            wait = new WebDriverWait(driver, 10);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            driver.quit();
            driver = null;
        }));
    }

    @After
    public void stop() {
        //driver.quit();
        //driver = null;
    }
}
