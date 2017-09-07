import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleSearchPage {

    @FindBy(id = "lst-ib")
    WebElement searchField;

    @FindBy(className = "g")
    List<WebElement> searchResults;
}
