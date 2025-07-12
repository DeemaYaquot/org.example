package testPackage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        driver.manage().window().maximize();
    }

    @Test
    public void testDropdown() throws InterruptedException {
        WebElement flightsLink = driver.findElement(By.xpath("//a[@id='flights']"));
        flightsLink.click();

        WebElement toField = driver.findElement(By.xpath("//input[@placeholder='To?']"));
        toField.sendKeys("Cairo");
        Thread.sleep(3000);

        toField.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN);
        toField.sendKeys(Keys.ENTER);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.findElement(By.xpath("//div[@aria-label='July 18, 2025']")).click();
        driver.findElement(By.xpath("//div[@aria-label='August 8, 2025']")).click();

        driver.findElement(By.xpath("//div[@aria-label='Return date']")).click();
        driver.findElement(By.className("RxNS-button-content")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Result item 0']")));

        js.executeScript("window.scrollBy(0, 500)");
        driver.findElement(By.xpath("//div[@aria-label='Result item 3']//div[@class='dOAU-best']")).click();

        try{
            driver.findElement(By.xpath("//div[text()='Continue']")).click();
        } catch (NoSuchElementException e) {
            System.out.println("Continue button not found, proceeding without clicking.");
        }

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        wait.until(ExpectedConditions.or(ExpectedConditions.urlContains("mytrip"),
                ExpectedConditions.urlContains("gotogate"),
                ExpectedConditions.urlContains("kiwi")));
        Assert.assertTrue(driver.getCurrentUrl().contains("gotogate")||driver.getCurrentUrl().contains("mytrip")||driver.getCurrentUrl().contains("kiwi"), "Booking didn't navigate to the expected URL.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
