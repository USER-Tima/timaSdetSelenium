package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.HomePageEbay;
import pages.SearchResultsPageEbay;
import utils.DriverManager;

public class SearchSteps {
    WebDriver driver = DriverManager.getDriver();
    HomePageEbay homePage = new HomePageEbay(driver);
    SearchResultsPageEbay searchResultsPage = new SearchResultsPageEbay(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;


    private String minPrice;
    private String maxPrice;

    @Given("I am on the eBay homepage")
    public void ebayHomePage() throws InterruptedException {
        driver.get("https://www.ebay.com/");
    }
        // I search for "Bluetooth speaker"
        //              {string} = Bluetooth speaker
    @When("I search for {string}")
    public void iSearchFor(String product) {                         //Bluetooth speaker
        homePage.enterSearchTerm(product);   //homePage.enterSearchTerm(product);
        homePage.clickSearchButton();
    }

    @Then("I should see search results containing {string}")
    public void searchResults(String result) {
        String resultsText = homePage.getSearchResultsText();
        System.out.println(resultsText);
        Assert.assertTrue(resultsText.toLowerCase().contains(result.toLowerCase()));

        if (resultsText.contains(result)) {
            System.out.println("The Strings are equal");
        } else {
            System.out.println("The Strings are not equal");
        }
    }

    @And("I apply a price filter from {string} to {string}")
    public void applyPriceFilter(String from, String to) {
        this.minPrice = from;
        this.maxPrice = to;
        searchResultsPage.applyPriceFilter(from, to);
    }

    @Then("I should see search results within the price range")
    public void setSearchResultsPage(){
        int min = Integer.parseInt(minPrice);
        int max = Integer.parseInt(maxPrice);
        Assert.assertTrue(searchResultsPage.areResultsWithinPriceRange(min, max));
    }

    @And("I sort the results by {string}")
    public void sortTheResultsBy(String option) {
        searchResultsPage.sortResultsBy(option);
    }

    @Then("I should see results sorted from lowest to highest price")
    public void seeSortedResultsLowToHigh() {
        Assert.assertTrue("The results are sorted correctly!", searchResultsPage.arePricesSortedLowToHigh());
    }

    @And("I apply a filter for RAM size {string} GB")
    public void applyFilterForRamSize(String ramSize) throws InterruptedException {
        searchResultsPage.filterForRamMethod(ramSize);
    }

    @Then("I should see results with that RAM size")
    public void seeResultRamSize() {
        Assert.assertTrue(searchResultsPage.hasRamSize());
    }

    @And("I navigate to the second page of results")
    public void navSecondPart(){
        searchResultsPage.secondPageMethod();
    }

    @Then("I should see search results for {string} on page 2")
    public void resultOnPageSecond(String resPageSec) {
        searchResultsPage.resultForSecondPage();
    }
}







