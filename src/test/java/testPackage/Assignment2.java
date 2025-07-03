package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Assignment2 {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testPageTitle() {
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        Assert.assertNotEquals(actualTitle, expectedTitle, "Page title is incorrect!");
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
