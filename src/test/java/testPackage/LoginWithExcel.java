package testPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;


public class LoginWithExcel {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver =  new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/v1/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginMultipleUsers() throws Exception {
        URI uri = getClass().getClassLoader().getResource("loginData.xlsx").toURI();
        Path path = Paths.get(uri);
        String filePath = path.toString();

        List<String[]> data = ExcelUtils.readLoginData(filePath);


        for (String[] row : data) {
            String username = row[0];
            String password = row[1];
            String expectedMessage = row[2];

            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickOnLoginButton();

            Assert.assertEquals(loginPage.getActualMessage(), expectedMessage, "Wrong error message for user: " + username);

            loginPage.clickOnCloseButton();
            loginPage.refreshPage();
        }
    }


//    @AfterClass
//    public void tearDown() {
//        driver.quit();
//    }
}
