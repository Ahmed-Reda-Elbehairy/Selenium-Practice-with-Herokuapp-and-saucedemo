import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assertions {
    WebDriver driver;
    By userName = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.id("login-button");
    By errorMsg = By.tagName("h3");

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

    }

    @Test
    public void inValidLogin() {
        driver.findElement(userName).sendKeys("ahmed");
        driver.findElement(password).sendKeys("secret_sauce");
        driver.findElement(loginBtn).click();
        String text = driver.findElement(errorMsg).getText();
        Assert.assertEquals(text, "Epic sadface: Username and password do not match any user in this service",
                "Error message didn't appear as expected");
        Assert.assertTrue(driver.findElement(errorMsg).isDisplayed(), "Login was Successful!");
    }

    @Test
    public void inValidLoginWithSoftAssertion() {
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(userName).sendKeys("ahmed");
        driver.findElement(password).sendKeys("secret_sauce");
        driver.findElement(loginBtn).click();
        String text = driver.findElement(errorMsg).getText();
        softAssert.assertEquals(text, "Epic sadface: Username and password do not match any user in this service",
                "Error message didn't appear as expected");
        softAssert.assertTrue(driver.findElement(errorMsg).isDisplayed(), "Error message is not displayed!");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
