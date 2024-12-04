package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.HomePageEbay;
import pages.HomePageAmazon;
import pages.ResultPage;
import pages.SearchResultsPageEbay;
import utils.DriverManager;
import utils.WebUtils;

import java.util.Set;

public class SearchStepsAmazon {
    WebDriver driver = DriverManager.getDriver();
    HomePageEbay homePage = new HomePageEbay(driver);
    SearchResultsPageEbay searchResultsPage = new SearchResultsPageEbay(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    HomePageAmazon homePageAmazon = new HomePageAmazon(driver);
    WebUtils webUtils = new WebUtils();
    ResultPage resultPage = new ResultPage(driver);
    HomePageEbay homePageEbay = new HomePageEbay(driver);



    @Given("I am on the Amazon homepage")
    public void setup() {
        driver.get("https://www.amazon.com/");
    }

    @When("I search for {string} on Amazon")
    public void searchOnAmazon(String iphoneProMax) {
        homePageAmazon.enterSearchTermAmazon(iphoneProMax);
        homePageAmazon.clickSearchButtonAmazon();
        resultPage.firstElementPriceAmazon();
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
        resultPage.firstElementPriceEbay();
    }

    @Then("I compare the prices of the first product on Amazon and eBay")
    public void compareThePricesOfTheFirstProductOnAmazonAndEBay() {

    }

    @And("I log which site offers the better price")
    public void iLogWhichSiteOffersTheBetterPrice() {
        resultPage.result();
    }
}
