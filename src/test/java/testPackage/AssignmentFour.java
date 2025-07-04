package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AssignmentFour {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testSearch() {
        WebElement searchBox = driver.findElement(By.id("searchbox_input"));
        searchBox.sendKeys("Selenium WebDriver");
        driver.findElement(By.xpath("//button[@aria-label='Search']")).click();

        String actualURL = driver.findElement(By.linkText("WebDriver - Selenium")).getAttribute("href");
        String expectedURL = "https://www.selenium.dev/documentation/webdriver/";
        Assert.assertEquals(actualURL, expectedURL, "The first result link does not match the expected link.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
