Feature: Verifying retriving all fixures

Scenario: Verifying if API is returning all fixtures
Given I have fixture API running
When I make get API call
Then I should receive http response code 200
And the response should have all fixtures