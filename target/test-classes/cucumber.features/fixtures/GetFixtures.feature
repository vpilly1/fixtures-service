Feature: Verifying retrieving all fixures

  Scenario: Verifying if API is returning all fixtures
    Given the fixtures endpoint exists
    When I make get API call to fixtures service
    Then response code should be 200
    And the response should have all fixtures 4

