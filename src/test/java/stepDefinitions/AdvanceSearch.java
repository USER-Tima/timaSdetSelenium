package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverManager;

import java.util.List;

public class AdvanceSearch {
    WebDriver driver = DriverManager.getDriver();


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
}