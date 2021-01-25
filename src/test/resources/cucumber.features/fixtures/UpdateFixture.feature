Feature: Verifying updating a fixures

  Scenario: Verifying if API is able to update a fixtures
    Given the update fixture endpoint exists
    When I make put API call to fixtures service with below details
      | fixtureId | 1 |
    Then update response code should be 200
    And the update response should have updated fixture 4