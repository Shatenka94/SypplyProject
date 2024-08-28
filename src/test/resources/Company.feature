Feature: Create company

@company

Scenario: Successfully create a company
  Given supplySync base URL
  And I provide a valid authorization token
  And I provide valid request body
  When I send a POST request to create a company
  Then the response status code should be 200
  Then response body should contain correct informations

 @deleteButton

 Scenario: Create company successfully and then delete
 Given supplySync base URL
 And I provide a valid authorization token
 And I provide valid request body
 When I send a POST request to create a company
 Then the response status code should be 200
 Then I hit Delete api using that ID
 Then i check status code should be 200

 @getAllCompanies

 Scenario: Verify GET all companies endpoint returns a list of companies when a valid token is provided
   Given supplySync base URL
   And I provide a valid authorization token
   And I provide valid request body
   When I send a POST request to create a company
   And I hit GET endpoint "/api/v1/company"
   Then Verify status code is 200
   And Verify response body contains a list of companies







