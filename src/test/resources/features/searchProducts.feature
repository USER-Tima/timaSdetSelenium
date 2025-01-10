Feature: Search Products on eBay

  Scenario: Search for a product
    Given I am on the eBay homepage
    When I search for "smartphone"
    Then I should see search results containing "smartphone"

  Scenario: Filter search results by price range
    Given I am on the eBay homepage
    When I search for "laptop"
    And I apply a price filter from "300" to "1000"
    Then I should see search results within the price range

  Scenario: Sort search results by price (low to high)
    Given I am on the eBay homepage
    When I search for "headphones"
    And I sort the results by "Price + Shipping: lowest first"
    Then I should see results sorted from lowest to highest price


  Scenario: Filter for laptops by RAM size
    Given I am on the eBay homepage
    When I search for "laptop"
    And I apply a filter for RAM size "16" GB
    Then I should see results with that RAM size


  Scenario: Verify pagination on search results
    Given I am on the eBay homepage
    When I search for "Bluetooth speaker"
    And I navigate to the second page of results
    Then I should see search results for "Bluetooth speaker" on page 2

  Scenario: Validate seller ratings and feedback for a product
    Given I am on the eBay homepage
    When I search for "gaming laptop"
    And I click on the first product
    Then I should verify the seller has a rating of or higher


  Scenario: Validate advanced search functionality with multiple criteria
    Given I am on the eBay advanced search page
    When I enter the keyword "headphones"
    And I exclude the word "wireless"
    And I set the minimum price to "50"
    And I set the maximum price to "300"
    And I choose the buying format "Buy It Now"
    And I select the condition "New"
    And I specify the location as "Located in"
    And I click on the search button
    Then I should verify that all results contain the keyword "headphones"
    And I should verify that no result contains the word "wireless"
#    And I should verify that the buying format for all results is "Buy It Now"
#    And I should verify that all items are located in the "United States"








