import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.is;

public class GoogleSearchTest {
    public static final String GOOGLE_URL = "http:\\google.com";
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        String baseDir = Paths.get(".").toAbsolutePath().normalize().toString();
        System.setProperty("webdriver.chrome.driver", baseDir + "/drivers/chromedriver.exe");
        System.out.println(System.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
    }

    @Test
    public void testSearchForWix() throws Exception {
        driver.get(GOOGLE_URL);
        WebElement searchField = driver.findElement(By.id("lst-ib"));
        searchField.sendKeys("Wix");
        searchField.sendKeys(Keys.ENTER);

        List<WebElement> searchResults = driver.findElements(By.cssSelector(".g"));
        Assert.assertThat(searchResults.size(), is(11));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
