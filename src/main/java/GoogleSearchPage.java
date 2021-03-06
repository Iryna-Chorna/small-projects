import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Getter
public class GoogleSearchPage {

    public static final int TIME_OUT_IN_SECONDS = 5;
    private WebDriver driver;

    @FindBy(id = "lst-ib")
    private WebElement searchField;

    @FindBy(css = ".g .r")
    private List<WebElement> searchResults;

    @FindBy(id = "ires")
    private WebElement resultsContainer;

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchFor(String searchText) {
        searchField.sendKeys(searchText);
        searchField.sendKeys(Keys.ENTER);
        new WebDriverWait(driver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOf(resultsContainer));
    }

    public void openFirstFoundLink() {
        searchResults.get(0).click();
        waitForPageLoad();
    }

    private void waitForPageLoad() {
        new WebDriverWait(driver, TIME_OUT_IN_SECONDS)
                .until(driver -> ((JavascriptExecutor)driver)
                        .executeScript("return document.readyState").equals("complete"));
    }
}
