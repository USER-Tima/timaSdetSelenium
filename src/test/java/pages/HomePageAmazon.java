package pages;

import net.serenitybdd.core.Serenity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageAmazon {
    WebDriver driver;

    public HomePageAmazon(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Search Amazon']")
    private WebElement searchAmazon;

    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    private WebElement searchButtonAmazon;

    public void enterSearchTermAmazon(String a) {
        searchAmazon.clear();
        searchAmazon.sendKeys(a);
    }
    public void clickSearchButtonAmazon() {
        searchButtonAmazon.click();
    }

}
