
Feature: login to supplysync
  @login
  Scenario: Login to supplysync and verify you logged in
    When user is on login page
    And user enters correct login
    And user enters correct password
    And user clicks login
    Then verify that user is logged in

    @getalltariffs
    Scenario: get all tariffs and verify they are displayed
      When user is on login page
      And user enters correct login
      And user enters correct password
      And user clicks login
      Then verify that user is logged in
      Then user clicks on tariff
      Then user gets all tariffs via API
      Then user create new tariff and verify it was created