Feature: Orange Hrm Login tests

  Scenario: Validate login page functi
    Given I am on the dashboard page
    And when I click on add new user button
    When I fill out the new user from with following details
      | User Role | Status  | Employee Name | Username | Password |
      | Admin     | Enabled | Melissa       | melissa  | Abcd1234 |
    And I click on Save button
    Then success message should be displayed