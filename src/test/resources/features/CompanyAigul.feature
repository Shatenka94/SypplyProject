Feature:
  @AigulTest
  Scenario: Successfully create a company

    Given i provide base URL
    Then i provide valid authorization token
    Then i provid request body to create new company
    Then i hit  to create company "/api/v1/company"
    Then  i checks status code equals "200"
    Then i get id from new created company
    Then i provid request body with updated the company name "AigulCompanies"
    Then i provide valid id and hit "/api/v1/company/"
    Then i check if it is updated