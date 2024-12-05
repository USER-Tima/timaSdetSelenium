package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class DateTableExpl {
    private List<String> words = new ArrayList<>();

    private int num1;
    private int num2;

    @Given("I have these words:")
    public void iHaveTheseWords(DataTable dataTable) {
        // Convert DataTable to List<String> without streams
        List<List<String>> rows = dataTable.asLists(String.class);
        System.out.println(rows.get(0));
        words.clear();

        // Skip the header row and add words to the list
        for (int i = 1; i < rows.size(); i++) { // Start from index 1 to skip the header
            words.add(rows.get(i).get(0)); // Add the first column of each row
        }

        System.out.println("Words: " + words);
    }

    @Then("I concatenate them with a space")
    public void iConcatenateThemWithASpace() {
        StringBuilder concatenatedResult = new StringBuilder();

        // Concatenate words with a space
        for (int i = 0; i < words.size(); i++) {
            concatenatedResult.append(words.get(i));
            if (i < words.size() - 1) { // Add space if not the last word
                concatenatedResult.append(" ");
            }
        }

        System.out.println("Concatenated Result: " + concatenatedResult);
    }

    @Given("I have numbers {int} and {int}")
    public void iHaveNumbersAnd(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
        System.out.println("Numbers: " + num1 + " and " + num2);
    }

    @Then("I calculate their sum and verify it is {int}")
    public void iCalculateTheirSumAndVerifyItIs(int expectedSum) {
        int actualSum = num1 + num2;
        System.out.println("Sum: " + actualSum);
        Assert.assertEquals(expectedSum, actualSum); // Verify the result
    }
}
