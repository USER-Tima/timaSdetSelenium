package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static utils.WebUtils.*;

public class HomePageEbay {
    WebDriver driver;

    public HomePageEbay(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='gh-ac']")
    private WebElement searchBox;

    @FindBy(xpath = "//input[@id='gh-btn']")
    private WebElement searchButton;

    @FindBy(xpath = "//h1[@class='srp-controls__count-heading']")
    private WebElement searchResults;

    @FindBy(xpath = "//a[@href='https://www.ebay.com/deals' and @class='gh-p']")
    private WebElement dealsPage;


    public void enterSearchTerm(String product) {
        sendKeysToElement(product,searchBox,Duration.ofSeconds(3));
    }

    public void clickSearchButton() throws InterruptedException {
        Thread.sleep(3000);
        searchButton.click();
        Thread.sleep(3000);
    }

    public String getSearchResultsText() {
        return searchResults.getText();
    }
    public void setupDealsPage(String page) {
        WebElement dealsPage = driver.findElement(By.xpath("//a[@href='https://www.ebay.com/"+page+"' and @class='gh-p']"));
        clickElement(dealsPage, Duration.ofSeconds(3));
    }
}
