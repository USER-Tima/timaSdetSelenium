package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.*;

import static utils.WebUtils.clickElement;
import static utils.WebUtils.sendKeysToElement;

public class SearchResultsPageEbay {
    WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    HomePageEbay homePage = new HomePageEbay(driver);


    @FindBy(xpath = "//input[@aria-label='Minimum Value in $']")
    private WebElement minPriceInput;

    @FindBy(xpath = "//input[@aria-label='Maximum Value in $']")
    private WebElement maxPriceInput;

    @FindBy(xpath = "//button[@aria-label='Submit price range']")
    private WebElement applyButton;

    @FindBy(xpath = "//span[@class='s-item__price']")
    private List<WebElement> priceElements;

    @FindBy(xpath = "//button[@aria-label='Sort selector. Best Match selected.']")
    private WebElement sortDropDown;

    @FindBy(xpath = "//div[@class='s-item__title']/span")
    private List<WebElement> productTitleList;

    @FindBy(xpath = "//a[@href='https://www.ebay.com/sch/i.html?_from=R40&_nkw=Bluetooth+speaker&_sacat=0&_pgn=2']")
    private WebElement secondPage;

    @FindBy(xpath = "//span[text()='Bluetooth speaker']")
    private WebElement headerTextBluetooth;


    public SearchResultsPageEbay(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void applyPriceFilter(String minPrice, String maxPrice) {
        sendKeysToElement(minPrice, minPriceInput, Duration.ofSeconds(5));
        sendKeysToElement(maxPrice, maxPriceInput, Duration.ofSeconds(5));
        clickElement(applyButton, Duration.ofSeconds(5));
    }

    public boolean areResultsWithinPriceRange(int minPrice, int maxPrice) {
        for (WebElement element : priceElements) {
            // Replace eny character except: "0123456789."
            // " Tech state  77625.. Techstate" -> "77625.."
            String priceText = element.getText().replaceAll("[^0-9.]", "");

            if (priceText.isEmpty()) {
                System.out.println("Skipping an item with no price displayed");
                continue;
            }

            double price;

            try {
                price = Double.parseDouble(priceText);
            } catch (NumberFormatException e) {
                System.out.println("Skipping an item with an invalid price: " + priceText);
                continue;
            }

            if (price < minPrice || price > maxPrice) {
                return false;
            } else {
                System.out.println("Valid price within range: " + price);
            }
        }
        return true;
    }

    //String option = Price + Shipping: lowest first
    public void sortResultsBy(String option) {
        clickElement(sortDropDown, Duration.ofSeconds(5));
        WebElement sortOptions = driver.findElement(By.xpath("//ul[@class='fake-menu__items']/li/a/span[.='" + option + "']"));
        clickElement(sortOptions, Duration.ofSeconds(5));
    }

    public boolean arePricesSortedLowToHigh() {
        List<Double> prices = new ArrayList<>(); //List of actual prices from page

        for (WebElement priceElement : priceElements) {
                                    // ^ 1-2-3-4-5-6-7-8-9 .  &&@#$%&**
            String priceText = priceElement.getText().replaceAll("[^0-9.]", "");
            if (!priceText.isEmpty()) {
                prices.add(Double.parseDouble(priceText));
                // LIST <44.99, 157.99>
            }
        }
        // 0.5, 0.9, 1.5, 0.99
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        // 0.5, 0.9, 0.99, 1.5

        if (!prices.equals(sortedPrices)) {
            System.out.println("The results are not sorted from lowest to highest price!");
            System.out.println("Actual prices: " + prices);
            System.out.println("Expected prices: " + sortedPrices);
            return false;
        }

        return true;
    }


    public void filterForRamMethod(String ramSize) throws InterruptedException {
        WebElement ramElement = driver.findElement(By.xpath("//input[@aria-label='"+ramSize+" GB']/parent::node()"));
        clickElement(ramElement,Duration.ofSeconds(2));
        Thread.sleep(2000);
    }

    public boolean hasRamSize() {
        String ramS = " 16GB";
        int count = 0;
        for (WebElement element: productTitleList) {
            String productTitle = element.getText().trim().toUpperCase();
            count++;
            System.out.println(count + ") Here is the title " + productTitle);
            if (productTitle.isEmpty()) {
                System.out.println("This is not a valid product ");
                continue;
            }
        }
        return true;
    }

    public void secondPageMethod() {
        clickElement(secondPage,Duration.ofSeconds(2));
    }

    public String resultForSecondPage() {
        return headerTextBluetooth.getText();
    }
}

