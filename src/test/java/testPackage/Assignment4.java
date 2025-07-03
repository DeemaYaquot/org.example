package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Assignment4 {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testSearch() {
        WebElement searchBox = driver.findElement(By.id("search_form_input_homepage"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();

        String actualURL = driver.findElement(By.cssSelector("a.result__a")).getAttribute("href");
        String expectedURL = "https://www.selenium.dev/documentation/webdriver/";
        Assert.assertEquals(actualURL, expectedURL, "The first result link does not match the expected link.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
