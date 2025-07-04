package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class SwagLabsTests {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver =  new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/v1/");
        driver.manage().window().maximize();
    }

    @Test
    public void testSuccessfulLogin() {
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        String expectedHeader = "Products";
        String actualHeader = driver.findElement(By.className("product_label")).getText();
        Assert.assertEquals(actualHeader, expectedHeader, "Login failed!");

        WebElement menu = driver.findElement(By.className("bm-burger-button"));
        menu.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
    }

//    @Test
//    public void testFailedLogin() {
//        String[][] userInfo = {
//                {"locked_out_user","secret_sauce","Epic sadface: Sorry, this user has been locked out."},
//                {"","secret_sauce","Epic sadface: Username is required"},
//                {"standard_user","","Epic sadface: Password is required"},
//                {"Deema","secret_sauce","Epic sadface: Username and password do not match any user in this service"},
//                {"standard_user","1234","Epic sadface: Username and password do not match any user in this service"}};
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//
//
//        for (String[] strings : userInfo) {
//            WebElement username = driver.findElement(By.id("user-name"));
//            username.sendKeys(strings[0]);
//
//            WebElement password = driver.findElement(By.id("password"));
//            password.sendKeys(strings[1]);
//
//            WebElement loginButton = driver.findElement(By.id("login-button"));
//            loginButton.click();
//
//            String expectedError = strings[2];
//            String actualError = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
//            Assert.assertEquals(actualError, expectedError, "Error message doesn't match expected text for user: " + strings[0]);
//
//            WebElement closeButton = driver.findElement(By.className("error-button"));
//            closeButton.click();
//
//            driver.navigate().refresh();
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
//
//
////            username.click();
////            username.clear();
////            username.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
////
////            password.click();
////            password.clear();
////            password.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
////
////// Wait until the username field is empty
////            wait.until(ExpectedConditions.attributeToBe(By.id("user-name"), "value", ""));
////            wait.until(ExpectedConditions.attributeToBe(By.id("password"), "value", ""));
//        }
//    }


    @DataProvider(name = "loginData")
    public Object[][] loginCredentials() {
        return new Object[][] {
            {"locked_out_user","secret_sauce","Epic sadface: Sorry, this user has been locked out."},
            {"","secret_sauce","Epic sadface: Username is required"},
            {"standard_user","","Epic sadface: Password is required"},
            {"Deema","secret_sauce","Epic sadface: Username and password do not match any user in this service"},
            {"standard_user","1234","Epic sadface: Username and password do not match any user in this service"}
        };
    }
    @Test(dataProvider = "loginData")
    public void testFailedLogin(String username, String password, String expectedError) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys(username);

        WebElement passWord = driver.findElement(By.id("password"));
        passWord.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        String actualError = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertEquals(actualError, expectedError, "Error message doesn't match expected text for user: " + username);

        WebElement closeButton = driver.findElement(By.className("error-button"));
        closeButton.click();

        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
