import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class PriorityAndDependency {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");

    }

    //@Test(priority = 1)
    //@Ignore
    @Test
    public void loginTC() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("radius")).click();
        Assert.assertTrue(driver.findElement(By.id("flash")).isDisplayed());
        //throw new RuntimeException();
    }

    //@Test(priority = 2)
    @Test(dependsOnMethods = "loginTC", alwaysRun = true)
    public void logoutTC() {
        driver.findElement(By.className("button")).click();
        Assert.assertTrue(driver.findElement(By.id("flash")).getText().contains("You logged out of the secure area!"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
