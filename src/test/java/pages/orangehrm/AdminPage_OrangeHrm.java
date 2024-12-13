package pages.orangehrm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static utils.WebUtils.clickElement;

public class AdminPage_OrangeHrm {
    WebDriver driver;
    Duration commonTime = Duration.ofSeconds(5);

    public AdminPage_OrangeHrm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[.=' Add ']")
    private WebElement addNewUserBtn;

    @FindBy(xpath = "//label[text()='User Role']/parent::div/following-sibling::div//div[contains(text(), 'Select')]")
    public WebElement userRoleDropDown;


    @FindBy(xpath = "//div[@class='oxd-select-option']/span[text()='Admin']")
    public WebElement adminOption;

    // //div[@class='oxd-select-option']/span[text()='Admin']


    @FindBy(xpath = "//label[text()='Status']/parent::div/following-sibling::div//div[contains(text(), 'Select')]")
    public WebElement userStatusDropDown;

    public void clickAddNewUserBtn() {
        clickElement(addNewUserBtn, commonTime);
        clickElement(userRoleDropDown, commonTime);
    }
}
