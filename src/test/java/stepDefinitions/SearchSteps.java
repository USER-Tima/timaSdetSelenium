package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePageEbay;
import pages.SearchResultsPageEbay;
import utils.DriverManager;

import java.util.ArrayList;
import java.util.List;

public class SearchSteps {
    WebDriver driver = DriverManager.getDriver();
    HomePageEbay homePage = new HomePageEbay(driver);
    SearchResultsPageEbay searchResultsPage = new SearchResultsPageEbay(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    SearchResultsPageEbay searchResultsPageEbay = new SearchResultsPageEbay(driver);


    private String minPrice;
    private String maxPrice;

    private int minPr;
    private int maxPr;

    @Given("I am on the eBay homepage")
    public void ebayHomePage() throws InterruptedException {
        driver.get("https://www.ebay.com/");
    }
        // I search for "Bluetooth speaker"
        //              {string} = Bluetooth speaker
    @When("I search for {string}")
    public void iSearchFor(String product) throws InterruptedException {                         //Bluetooth speaker
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


    @And("I click on the first product")
    public void iClickOnTheFirstProduct() {
        searchResultsPage.clickFirstElement();
    }

    @Then("I should verify the seller has a rating of or higher")
    public void iShouldVerifyTheSellerHasARatingOfOrHigher() {
        searchResultsPage.sortedMethod();
    }


    @Given("I am on the eBay advanced search page")
    public void iAmOnTheEBayAdvancedSearchPage() {
        String url = "https://www.ebay.com/sch/ebayadvsearch";
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("I enter the keyword {string}")
    public void iEnterTheKeyword(String text) {
        searchResultsPage.sendKeywords(text);
    }

    @And("I exclude the word {string}")
    public void iExcludeTheWord(String text) {
        searchResultsPage.sendExcludeWord(text);
    }

    @And("I set the minimum price to {string}")
    public void iSetTheMinimumPriceTo(String to) {
        searchResultsPage.sendMinPrice(to);
    }

    @And("I set the maximum price to {string}")
    public void iSetTheMaximumPriceTo(String from) {
        searchResultsPage.sendMaxPrice(from);
    }

    @And("I choose the buying format {string}")
    public void iChooseTheBuyingFormat(String text) {
        WebElement chose = driver.findElement(By.xpath("//label[text()='" + text + "']"));
        if (!chose.isSelected()) chose.click();
    }

    @And("I select the condition {string}")
    public void iSelectTheCondition(String text) {

        WebElement selectNew = driver.findElement(By.xpath("//label[text()='"+text+"']"));
        if (!selectNew.isSelected()) selectNew.click();

    }

    @And("I specify the location as {string}")
    public void iSpecifyTheLocationAs(String selectUSA) {
        WebElement selectLocation = driver.findElement(By.xpath("//label[text()='"+selectUSA+" ']"));
        if (!selectLocation.isSelected()) selectLocation.click();
    }

    @And("I click on the search button")
    public void iClickOnTheSearchButton() {
        searchResultsPage.saveButtonMethod();
    }

    @Then("I should verify that all results contain the keyword {string}")
    public void iShouldVerifyThatAllResultsContainTheKeyword(String text) {
        searchResultsPage.infoResultAfterSave();
        System.out.println("All results contain the keyword: " + text);

    }

    @And("I should verify that no result contains the word {string}")
    public void iShouldVerifyThatNoResultContainsTheWord(String text) {
        List<WebElement> result = driver.findElements(By.xpath(" //span[@role='heading']"));
        for (WebElement a : result){
            String resText = a.getText().trim().toLowerCase();
            if (resText.contains(text)){
                System.out.println("NO NO");
            }
            else {
                System.out.println(resText);
            }
        }
    }

    @And("I should verify that the buying format for all results is {string}")
    public void iShouldVerifyThatTheBuyingFormatForAllResultsIs(String text) {
        WebElement buyItNow = driver.findElement(By.xpath("//span[@class='srp-format-tabs-h2' and text()='"+text+"']"));
        if (buyItNow.isSelected()) {System.out.println("Selected!!! : " + text);}
    }

    @And("I should verify that all items are located in the {string}")
    public void iShouldVerifyThatAllItemsAreLocatedInThe(String text) {
        WebElement location = driver.findElement(By.xpath("//a[@class='srp-carousel-list__item-link--truncated']"));
        if (location.isSelected()) {System.out.println("Selected!!! : " + text);}
    }
}






