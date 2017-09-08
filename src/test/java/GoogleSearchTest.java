import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;

public class GoogleSearchTest {
    public static final String GOOGLE_URL = "http:\\google.com";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        driver = new ChromeDriver(options);
    }

    @Parameters({"searchString", "foundPageTitle"})
    @Test
    public void testSearchForWix(String searchString, String foundPageTitle) throws Exception {
        driver.get(GOOGLE_URL);
        GoogleSearchPage searchPage = PageFactory.initElements(driver, GoogleSearchPage.class);
        searchPage.searchFor(searchString);
        searchPage.openFirstFoundLink();
        Assert.assertThat(driver.getTitle(), containsString(foundPageTitle));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
