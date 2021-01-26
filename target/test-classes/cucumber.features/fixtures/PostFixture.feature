Feature: Verifying adding a fixures

  Scenario: Verifying if API is able to add a fixtures
    Given the add fixtures endpoint exists
    When I make post API call to fixtures service with below details
    | fixtureId | 4 |
    Then add response code should be 202
    And the add response should have all fixtures 4