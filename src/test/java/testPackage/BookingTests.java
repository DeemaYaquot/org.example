package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class BookingTests {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver =  new ChromeDriver();
        driver.get("https://www.booking.com/");
    }

    @Test
    public void testDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='flights']")));
        WebElement flightsLink = driver.findElement(By.xpath("/html/body/div[2]/div/div/header/div/nav[2]/div/ul/li[2]/a"));
        flightsLink.click();

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='To?']")));
//        driver.findElement(By.xpath("//input[@placeholder='To?']")).click();
        driver.findElement(By.xpath("//input[@placeholder='To?']")).sendKeys("Cairo");
        driver.findElement(By.xpath("//div[@aria-label='Departure date']")).click();
//        WebElement dropdownMenu = driver.findElement(By.id("dropdown"));
//        Select dropdown = new Select(dropdownMenu);
//        dropdown.selectByVisibleText("Option 2");
//
//        List<WebElement> options = dropdown.getOptions();
//        for (WebElement option : options) {
//            System.out.println("Dropdown option: " + option.getText());
//        }
//
//        String selectedOption = dropdown.getFirstSelectedOption().getText();
//        Assert.assertEquals(selectedOption, "Option 2", "Selected option is not as expected.");
    }

//    @AfterClass
//    public void tearDown() {
//        driver.quit();
//    }
}
