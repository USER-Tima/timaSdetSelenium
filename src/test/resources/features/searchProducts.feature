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










