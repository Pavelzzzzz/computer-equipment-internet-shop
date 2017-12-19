package by.pavelzzzzz.spring.shop.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Selenium {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "/home/pavel/bin/geckodriver");
        driver = new FirefoxDriver();
        baseUrl = "https://www.katalon.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCheckUsers() throws Exception {
        driver.get("http://localhost:8080/shop/");
        driver.findElement(By.linkText("Administration")).click();
        driver.findElement(By.linkText("Users")).click();
        driver.findElement(By.xpath("//table[@id='tableOfUsers']/tbody/tr/td[3]")).click();
        driver.findElement(By.id("userLogin")).click();
        assertEquals("", driver.findElement(By.id("userLogin")).getText());
    }

    @Test
    public void testCheckRoles() throws Exception {
        driver.get("http://localhost:8080/shop/index");
        driver.findElement(By.linkText("Administration")).click();
        driver.findElement(By.linkText("Roles")).click();
        driver.findElement(By.xpath("//table[@id='tableOfRoles']/tbody/tr/td")).click();
        driver.findElement(By.id("role")).click();
        assertEquals("", driver.findElement(By.id("role")).getText());
    }

    @Test
    public void testCheckCategory() throws Exception {
        driver.get("http://localhost:8080/shop/index");
        driver.findElement(By.linkText("Administration")).click();
        driver.findElement(By.linkText("Category")).click();
        driver.findElement(By.xpath("//table[@id='tableOfCategory']/tbody/tr/td[2]")).click();
        driver.findElement(By.xpath("//div[@id='fh5co-blog-section']/div/div/div/div/h1")).click();
        assertEquals("Category editor", driver.findElement(By.xpath("//div[@id='fh5co-blog-section']/div/div/div/div/h1")).getText());
    }

    @Test
    public void testCheckProducts() throws Exception {
        driver.get("http://localhost:8080/shop/index");
        driver.findElement(By.linkText("Administration")).click();
        driver.findElement(By.linkText("Products")).click();
        driver.findElement(By.xpath("//table[@id='tableOfNews']/thead/tr/th")).click();
        assertEquals("ProductId", driver.findElement(By.xpath("//table[@id='tableOfNews']/thead/tr/th")).getText());
    }

    @Test
    public void testRegistration() throws Exception {
        driver.get("http://localhost:8080/shop/index");
        driver.findElement(By.linkText("Registration")).click();
        driver.findElement(By.id("registrationEmail")).clear();
        driver.findElement(By.id("registrationEmail")).sendKeys("123");
        driver.findElement(By.id("registrationPassword1")).clear();
        driver.findElement(By.id("registrationPassword1")).sendKeys("123");
        driver.findElement(By.xpath("//div[@id='fh5co-blog-section']/div/div/div/div/h1")).click();
        assertEquals("Registration", driver.getTitle());
    }

    @Test
    public void testLogon() throws Exception {
        driver.get("http://localhost:8080/shop/index");
        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.id("logonPassword")).clear();
        driver.findElement(By.id("logonPassword")).sendKeys("123");
        driver.findElement(By.xpath("//div[@id='fh5co-blog-section']/div/div/div/div/h1")).click();
        assertEquals("Log in", driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
