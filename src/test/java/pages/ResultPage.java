package pages;

import net.serenitybdd.core.Serenity;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    WebDriver driver;
    HomePageAmazon homePageAmazon = new HomePageAmazon(driver);
    HomePageEbay homePageEbay = new HomePageEbay(driver);

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//span[@class='s-item__price'])[3]")
    private WebElement firstElementFromEbay;

    @FindBy(xpath = "(//span[@class='a-price-whole'])[1]")
    private WebElement firstElementFromAmazon;




//    public void resultPrice() {
//        System.out.println(String.valueOf(Serenity.sessionVariableCalled("AmazonPriceGlobal")));
////        System.out.println("" + homePageEbay.firstElementPriceMethod());
//
////        if (homePageAmazon.firstElementPriceAmazon() > homePageEbay.firstElementPriceMethod()) {
////            String result = "I suggest you Amazon! $" + homePageAmazon.firstElementPriceAmazon() + " the best offer!";
////            return result;
////        }
////        String secondResult = "I suggest you Amazon! $" + homePageAmazon.firstElementPriceAmazon() + " the best offer!";
////        return secondResult;
//    }

    double priceEbay = 0;
    public double firstElementPriceEbay() {
        String priceText = firstElementFromEbay.getText().replaceAll("[^0-9.]", "");

        try {
            priceEbay = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            System.out.println("Skipping an item with an invalid price: " + priceText);
        }
        System.out.println("This is a price from eBay: $" + priceEbay);
        return priceEbay;
    }

    double priceAmazon;
    public double firstElementPriceAmazon(){
        String priceText = firstElementFromAmazon.getText().replaceAll("[^0-9.]", "");
        double priceAmazon = 0;
        try {
            priceAmazon = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            System.out.println("Skipping an item with an invalid price: " + priceText);
        }
        System.out.println("This is a price from Amazon: $" + priceAmazon);
        return priceAmazon;
    }

    public void result() {
        if (priceAmazon > priceEbay) {
            System.out.println("I suggest you Amazon! $" + priceAmazon + " the best offer!");
        }
        else {
            System.out.println("I suggest you Ebay! $" + priceEbay + " the best offer!");
        }
        System.out.println("I m DONE!!!");
    }
}
