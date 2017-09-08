import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.is;

public class GoogleSearchTest {
    public static final String GOOGLE_URL = "http:\\google.com";
    public static final int RESULTS_COUNT = 11;

    private WebDriver driver;

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
        GoogleSearchPage searchPage = PageFactory.initElements(driver, GoogleSearchPage.class);
        searchPage.searchFor("Wix");

        // The line below may show "compilation error" if you don't have Lombok plugin for IntelliJ IDEA installed
        // Nevertheless it will not prevent you from running tests successfully
        List<WebElement> searchResults = searchPage.getSearchResults();
        Assert.assertThat(searchResults.size(), is(RESULTS_COUNT));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
