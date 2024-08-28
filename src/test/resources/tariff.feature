Feature: Creating tariff
  As an API consumer
  I want to create a tariff using the API
  So that I can manage tariffs in the system

  Background:
    Given supplySync base URL
    And I provide a VALID authorization token

  @tarifftest
  Scenario: Successfully create a tariff
    When I send a POST request to create a tariff with the following details
    Then the response status code should be 200
    And the response should contain the name "Oles Tariff"
    And the response should contain an id field
