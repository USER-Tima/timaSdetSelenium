package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePageEbay;
import pages.SearchResultsPageEbay;
import utils.DriverManager;
import static utils.WebUtils.*;

import java.time.Duration;
import java.util.List;

public class AdvanceSearch {
    WebDriver driver = DriverManager.getDriver();
    SearchResultsPageEbay searchResultsPageEbay = new SearchResultsPageEbay(driver);
    HomePageEbay homePageEbay = new HomePageEbay(driver);


    @When("I type a search term and verify results")
    public void iTypeSearchTerm(DataTable dataTable) {
        List<String> searchTerm = dataTable.asList(String.class);
        System.out.println(searchTerm);
        for(String term : searchTerm) {
            WebElement searchBox = driver.findElement(By.xpath("//input[@id='gh-ac']"));
            searchBox.clear();
            searchBox.sendKeys(term);

            WebElement searchButton = driver.findElement(By.xpath("//input[@id='gh-btn']"));
            searchButton.click();

            driver.navigate().back();
        }
    }

    @When("I navigate to the following pages and print each page has a title")
    public void iNavigateToTheFollowingPagesAndPrintEachPageHasATitle(DataTable dataTable) {
        List<String> searchTest = dataTable.asList(String.class);
        for(String term : searchTest) {
            driver.findElement(By.linkText(term)).click();
            String title = driver.getTitle();
            System.out.println("Title: " + title);
            driver.navigate().back();

        }
    }
    @When("I check the following popular categories are displayed")
    public void iCheckTheFollowingPopularCategoriesAreDisplayed(DataTable dataTable) {
        List<String> searchTest = dataTable.asList(String.class);
        isDisplayedMethod(searchTest);
    }

    public boolean isDisplayedMethod(List<String> list){
        for(String term : list) {
            try {
                WebElement a = driver.findElement
                        (By.xpath("//h3[@class='vl-popular-destinations-evo__name' and text()='" + term + "']"));
               if (a.isDisplayed()) {System.out.println("The section header   \"" + term + "\" is present!");}
                return true;
            } catch (Exception e) {
                System.out.println("Element not  \"" + term + "\"  displayed!");
            }
        }
        return false;
    }
    @Then("I navigate to the Gift Cards page")
    public void iNavigateToTheGiftCardsPage() {
        searchResultsPageEbay.giftCardNav();
    }

    @When("I check the benefits are the same as my examples")
    public void iCheckTheBenefitsAreTheSameAsMyExamples(DataTable dataTable) {
        List<String> searchTest = dataTable.asList(String.class);
        isCompareOrNot(searchTest);
    }

    public void isCompareOrNot(List<String> list){
        for(String li : list) {
            try {
                WebElement test = driver.findElement
                        (By.xpath("//h3[@class='text-col__title' and text()='"+li+"']"));
                if (test.isDisplayed()) {System.out.println("YES it s TRUE!! : \"" + li + "\"!");}
                continue;
            } catch (Exception e) {
                System.out.println("Element not  \"" + li + "\"  displayed!");
                continue;
            }
        }
    }

    @When("I navigate to the deals section")
    public void iNavigateToTheSection() {
        driver.navigate().to("https://www.ebay.com/deals/other-deals");
    }

    @And("I click on the {string} category")
    public void iClickOnTheCategory(String category) {
        WebElement categoryElement = driver.findElement(By.xpath("//span[@class='dne-see-more-type-text-span' and text()='"+ category +"']"));
        clickElement(categoryElement,Duration.ofSeconds(3));

    }

    @Then("I should verify that at least {string} products are displayed")
    public void iShouldVerifyThatAtLeastProductsAreDisplayed(String minAmount) {
        int minCountInt = Integer.parseInt(minAmount);
        List<WebElement> sizeItem = driver.findElements(By.xpath("//div[@class=\"slashui-image-cntr\"]"));
        int productCount = sizeItem.size();

        Assert.assertTrue("Expected at least " + minCountInt + "products, but found only " +
                productCount, productCount >= minCountInt);

        System.out.println("Verified that at least " + minCountInt + " product are displayed!");

    }

    @And("I filter by condition:")
    public void iFilterByCondition(DataTable dataTable) {
        List<String> conditions = dataTable.asList(String.class);

        for (String condition : conditions) {
            WebElement element = driver.findElement(By.xpath("//input[@aria-label='" + condition + "']"));
            if (!element.isSelected()){element.click();}
        }
    }

    @Then("I should verify that the product condition matches the applied filters")
    public void iShouldVerifyThatTheProductConditionMatchesTheAppliedFilters() {
        List<WebElement> a = driver.findElements(By.xpath("//span[@class='SECONDARY_INFO']"));
       preOwnedOrBrandNew(a);
    }

    public void preOwnedOrBrandNew(List<WebElement> list) {
        String preOwned = "Pre-Owned";
        String brandNew = "Brand New";
        for (WebElement condition : list) {
            String conditionText = condition.getText();
            if (conditionText.equals(preOwned) || conditionText.equals(brandNew)) {
                System.out.println("TRUE! : " + conditionText);
            }
            else {
                System.out.println("Skip --> " + conditionText);
            }
        }
    }
}