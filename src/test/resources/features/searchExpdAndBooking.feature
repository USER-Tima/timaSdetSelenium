Feature: Compare travel packages across platforms
  Scenario: Customize and compare travel packages
    Given I am on the Expedia homepage
    When I search for a travel package to “Paris”
    And I select travel dates from “2024-12-15" to “2024-12-22”
    And I add flight preferences:
      | preference         | value           |
      | Cabin class        | Economy         |
      | Number of travelers| 2               |
    And I add hotel preferences:
      | preference         | value           |
      | Star rating        | 4               |
      | Distance from city | Within 5 miles  |
    Then I note the total price for the package
    And I open Booking.com in a new tab
    When I search for a travel package to “Paris”
    And I set the same preferences
    Then I note the total price for the package
    And I log which platform offers the best price