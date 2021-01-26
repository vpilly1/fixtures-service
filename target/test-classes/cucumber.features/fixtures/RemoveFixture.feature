Feature: Verifying deleting a fixture

  Scenario: Verifying if API is deleting a fixture
    Given the fixture delete endpoint exists
    When I make Delete API call to fixtures service 4
    Then delete response code should be 204
    And the delete response should have one less fixture 3
