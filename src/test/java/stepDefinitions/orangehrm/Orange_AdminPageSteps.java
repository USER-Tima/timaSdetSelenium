package stepDefinitions.orangehrm;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.orangehrm.AdminPage_OrangeHrm;
import pages.orangehrm.DashboardPage_OrangeHrm;
import utils.DriverManager;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static utils.WebUtils.sendKeysToElement;
import static utils.WebUtils.clickElement;

public class Orange_AdminPageSteps {
    WebDriver driver = DriverManager.getDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    AdminPage_OrangeHrm adminPageOrangeHrm = new AdminPage_OrangeHrm(driver);
    DashboardPage_OrangeHrm dashboardPageOrangeHrm = new DashboardPage_OrangeHrm(driver);

    String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @Given("I am on the dashboard page")
    public void iAmOnTheDashboardPage() throws InterruptedException {
        driver.get(url);
        Thread.sleep(2000);
        sendKeysToElement("Admin", driver.findElement(By.xpath("//input[@name='username']")), Duration.ofSeconds(3));
        sendKeysToElement("admin123", driver.findElement(By.xpath("//input[@name='password']")), Duration.ofSeconds(3));
        clickElement(driver.findElement(By.xpath("//button[@type='submit']")), Duration.ofSeconds(3));
        clickElement(dashboardPageOrangeHrm.adminMenuNavOption, Duration.ofSeconds(3));
        Thread.sleep(3000);
    }

    @And("when I click on add new user button")
    public void when_i_click_on_add_new_user_button() throws InterruptedException {
        adminPageOrangeHrm.clickAddNewUserBtn();
        Thread.sleep(3000);
    }

    @When("I fill out the new user from with following details")
    public void i_fill_out_the_new_user_form_with_following_details(DataTable dataTable) throws InterruptedException {

//      | User Role | Status  | Employee Name | Username | Password |
//      | Admin     | Enabled | Melissa       | melissa  | Abcd1234 |

        List<Map<String, String>> userDetails = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> userDetail : userDetails) {
            String userRole = userDetail.get("User Role");   // userRole = Admin
            String status = userDetail.get("Status");  // status = Enabled
            String employeeName = userDetail.get("Employee Name");
            String username = userDetail.get("Username");
            String password = userDetail.get("Password");

            WebElement a = driver.findElement(By.xpath("//div[@class='oxd-select-option']/span[text()='"+userRole+"']"));
            clickElement(a,Duration.ofSeconds(2));

            WebElement b = driver.findElement(By.xpath("//label[text()='Status']/parent::div/following-sibling::div//div[contains(text(), 'Select')]"));
            clickElement(b,Duration.ofSeconds(2));
            driver.findElement(By.xpath("//div[@class='oxd-select-option']/span[text()='"+status+"']")).click();


        }
    }














    //  @FindBy(xpath = "//label[text()='User Role']/parent::div/following-sibling::div//div[contains(text(), 'Select')]")
    //    public WebElement userRoleDropDown;
    //
    //
    //    // //div[@class='oxd-select-option']/span[text()='Admin']

    @When("I click on Save button")
    public void i_click_on_save_button() {

    }

    @Then("success message should be displayed")
    public void success_message_should_be_displayed() {

    }
}
