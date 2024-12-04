Feature: Cross-Site Product Search and Comparison

  Scenario: Compare product prices between Amazon and eBay
    Given I am on the Amazon homepage
    When I search for "IPhone 16 Pro Max 512gb" on Amazon
    And I open eBay in a new tab
    And I search for "IPhone 16 Pro Max 512gb" on eBay
    Then I compare the prices of the first product on Amazon and eBay
    And I log which site offers the better price