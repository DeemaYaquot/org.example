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

public class Assignment3 {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testLogo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'header_logoHorizontal__KABN4')]//img")));
        WebElement logo = driver.findElement(By.xpath("//a[contains(@class, 'header_logoHorizontal__KABN4')]//img"));
        Assert.assertTrue(logo.isDisplayed(), "Logo is not visible on the page");
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
