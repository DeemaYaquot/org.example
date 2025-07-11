package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class LocatorsTests {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver =  new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
    }

    @Test
    public void testFailedLogin() {
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("Deema");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();

        String expectedMessage = "Your username is invalid!";
        String actualMessage = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Error message doesn't match expected text.");
    }

    @Test
    public void testSuccessfulLogin() {
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        WebElement header = driver.findElement(By.tagName("h2"));
        System.out.println("Header text: " + header.getText());

        WebElement paragraph = driver.findElement(By.className("subheader"));
        System.out.println("Paragraph text: " + paragraph.getText());

        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Login message doesn't match expected text.");
    }

    @Test
    public void testDropdown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdownMenu = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownMenu);
        dropdown.selectByVisibleText("Option 2");

        List <WebElement> options = dropdown.getOptions();
        for (WebElement option : options) {
            System.out.println("Dropdown option: " + option.getText());
        }

        String selectedOption = dropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedOption, "Option 2", "Selected option is not as expected.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
