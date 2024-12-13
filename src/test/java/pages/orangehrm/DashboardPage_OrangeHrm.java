package pages.orangehrm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage_OrangeHrm {
    WebDriver driver;

    public DashboardPage_OrangeHrm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@class='oxd-main-menu-item-wrapper']//span[text()='Admin']")
    public WebElement adminMenuNavOption;

    @FindBy(xpath = "")
    private WebElement userRoleDropDown;

}
