package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    By usernameFieldId = By.id("user-name");
    By passwordFieldId = By.id("password");
    By loginButtonId = By.id("login-button");
    By actualMessageXpath = By.xpath("//h3[@data-test='error']");
    By closeButtonClassName = By.className("error-button");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameFieldId).clear();
        driver.findElement(usernameFieldId).sendKeys(username);
    }

    public By getUsernameLocator() {
        return usernameFieldId;
    }
    public void enterPassword(String password) {
        driver.findElement(passwordFieldId).clear();
        driver.findElement(passwordFieldId).sendKeys(password);
    }

    public void clickOnLoginButton() {
        driver.findElement(loginButtonId).click();
    }

    public void clickOnCloseButton() {
        driver.findElement(closeButtonClassName).click();
    }

    public String getActualMessage() {
        return driver.findElement(actualMessageXpath).getText();
    }

    public void refreshPage() {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
    }
}
