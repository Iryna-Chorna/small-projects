import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class GoogleSearchTest {
    public static final String GOOGLE_URL = "http:\\google.com";
    public static final String WIX_TITLE = "Wix";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testSearchForWix() throws Exception {
        driver.get(GOOGLE_URL);
        GoogleSearchPage searchPage = PageFactory.initElements(driver, GoogleSearchPage.class);
        searchPage.searchFor("wix");

        // The line below may show "compilation error" if you don't have Lombok plugin for IntelliJ IDEA installed
        // Nevertheless it will not prevent you from running tests successfully.
        List<WebElement> searchResults = searchPage.getSearchResults();
        searchPage.openFirstFoundLink();
        Assert.assertThat(driver.getTitle(), containsString(WIX_TITLE));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
