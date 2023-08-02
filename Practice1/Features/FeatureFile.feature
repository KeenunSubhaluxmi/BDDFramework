Feature: Searching for Phones on Mall.cz

  Scenario: I search for "Phone" and view search results
    Given I am on the Mall.cz website
    When I search for "Phone"
    And I click on the search button
    Then the search results page should be displayed
    And the search results should contain items related to phones
