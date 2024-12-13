Feature: Concatenate strings and add numbers

  Scenario: Concatenate a list of words
    Given I have these words:
      | word    |
      | Hello   |
      | World   |
      | Cucumber|
    Then I concatenate them with a space

  Scenario Outline: Add two numbers
    Given I have numbers <num1> and <num2>
    Then I calculate their sum and verify it is <sum>
    Examples:
      | num1 | num2 | sum |
      | 2    | 3    | 5   |
      | 4    | 6    | 10  |
      | 10   | 15   | 25  |