package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.HomePageAmazon;
import pages.SearchResultsPageEbay;
import utils.DriverManager;
import utils.WebUtils;

import java.util.Set;

public class SearchStepsAmazon {
    WebDriver driver = DriverManager.getDriver();
    HomePage homePage = new HomePage(driver);
    SearchResultsPageEbay searchResultsPage = new SearchResultsPageEbay(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    HomePageAmazon homePageAmazon = new HomePageAmazon(driver);
    WebUtils webUtils = new WebUtils();

    @Given("I am on the Amazon homepage")
    public void setup() {
        driver.get("https://www.amazon.com/");
    }

    @When("I search for {string} on Amazon")
    public void searchOnAmazon(String iphoneProMax) {
        homePageAmazon.enterSearchTermAmazon(iphoneProMax);
        homePageAmazon.clickSearchButtonAmazon();
    }

    @And("I open eBay in a new tab")
    public void goeBaySite() throws InterruptedException {
        String originalWindow = driver.getWindowHandle();
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.ebay.com', '_blank');");

        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window); // Switch to the new window
                break;
            }
        }

    }

    @And ("I search for {string} on eBay")
    public void searchByEbayIphone(String product) throws InterruptedException {
        homePage.enterSearchTerm(product);
        homePage.clickSearchButton();
    }
}
