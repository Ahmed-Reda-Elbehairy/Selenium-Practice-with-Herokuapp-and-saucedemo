import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class DynamicLocators {
    WebDriver driver;

    @Test
    public void DL() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://jqueryui.com/");
        chooseCategory("Support");
        chooseOption("Chat");
    }

    public void chooseOption(String option) {
        driver.findElement(By.xpath("//a[.='" + option + "']")).click();
    }

    public void chooseCategory(String category) {
        new Actions(driver).moveToElement(driver.findElement(By.xpath("//li[@class = 'dropdown'] //a[.='" + category + "']")))
                .perform();
    }
}
