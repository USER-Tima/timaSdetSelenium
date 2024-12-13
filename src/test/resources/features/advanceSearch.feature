Feature: Advance search

  Scenario: Validate search functionality
    Given I am on the eBay homepage
    When I type a search term and verify results
      | laptop |
      | phone  |
      | camera |

  Scenario: Verify page titles
    Given I am on the eBay homepage
    When I navigate to the following pages and print each page has a title
      | Motors      |
      | Electronics |
      | Toys        |

  Scenario: Validate section headers are present
    Given I am on the eBay homepage
    When I check the following popular categories are displayed
      | Luxury   |
      | Sneakers |
      | Toys     |

  Scenario: Validate gift-cards benefits title
    Given I am on the eBay homepage
    Then I navigate to the Gift Cards page
    When I check the benefits are the same as my examples
      | Hassle-free          |
      | Heartwarming designs |
      | The choice is theirs |

  Scenario Outline: Validate product count on Deals page
    Given I am on the eBay homepage
    When I navigate to the deals section
    And I click on the "<category>" category
    Then I should verify that at least "<minCount>" products are displayed

    Examples:
      | category     | minCount |
      | Collectibles | 10       |
      | Coins        | 15       |
      | Music & Gear | 20       |

#  Scenario Outline: Validate product details for specific keywords
#    Given I am on the eBay homepage
#    When I search for "<product>"
#    And I verify that each product description contains the keyword "<keyword>"
#
#    Examples:
#      | product     | keyword    |
#      | smartphone  | camera     |
#      | laptop      | Intel      |
#      | headphones  | wireless   |
#


  Scenario Outline: Search and filter products with multiple criteria
    Given I am on the eBay homepage
    When I search for "<product>"
    And I apply a price filter from "<minPrice>" to "<maxPrice>"
    And I filter by condition:
      | New       |
      | Used      |
    Then I should verify that the product condition matches the applied filters

    Examples:
      | product     | minPrice | maxPrice |
      | headphones  | 50       | 300      |
      | smartphones | 200      | 1000     |
      | laptops     | 500      | 2000     |


