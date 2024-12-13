package stepDefinitions.travelTaskSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class TravelPackageSteps {
    WebDriver driver;
    
    @Given("I am on the Expedia homepage")
    public void setup(){
        
    }

    @When("I search for a travel package to “Paris”")
    public void iSearchForATravelPackageToParis() {
        
    }

    @And("I select travel dates from {int} {int} {int} to {int} {int} {int}")
    public void iSelectTravelDatesFromTo(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {

    }

}
