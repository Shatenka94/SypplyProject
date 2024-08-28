Feature: Creating tariff
  As an API consumer
  I want to create a tariff using the API
  So that I can manage tariffs in the system

  Background:
    Given supplySync base URL
    And I provide a VALID authorization token

  @tariffcreate
  Scenario: Successfully create a tariff
    When I send a POST request to create a tariff with the following details
    Then the response status code should be 200
    And the response should contain the name "Oles Tariff"
    And the response should contain an id field

  @getAlltariffs

  Scenario: Verify GET all tariffs returns a list of tariffs when a valid token is provided
    Given supplySync base URL
    And I provide a VALID authorization token
    And I hit GET endpoint "/api/v1/tariffs"
    Then Verify status code is 200
    And Verify response body contains a list of tariffs

  @deletetariffs

  Scenario: Verify delete specific tariff with id provided
    Given supplySync base URL
    And I provide a VALID authorization token
    And I hit DELETE endpoint "/api/v1/tariffs/" with id "140"
    And Verify response body doesnt contains that tariffs id "140"
