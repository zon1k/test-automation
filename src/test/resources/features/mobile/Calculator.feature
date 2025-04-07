Feature: Calculator Operations

  @Mobile
  Scenario: Perform addition
    Given Calculator is open
    When I add 2 and 3
    Then I should see result 5

  @Mobile
  Scenario: Divide by one
    Given Calculator is open
    When I divide 5 by 5
    Then I should see result 1