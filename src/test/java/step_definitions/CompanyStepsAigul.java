package step_definitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponse;
import entities.RequestBody;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utilities.Config;
import utilities.SupplySyncToken;

public class CompanyStepsAigul {
    RequestSpecification request;
    Response response;
    CustomResponse customResponse;
    ObjectMapper mapper = new ObjectMapper();
    RequestBody requestBody = new RequestBody();
    Faker faker = new Faker();
    String id;



    @Given("i provide base URL")
    public void i_provide_base_url() {
        request = RestAssured.given().baseUri(Config.getProperty("baseSupplySync"));
    }
    @Then("i provide valid authorization token")
    public void i_provide_valid_authorization_token() {
       request = request.auth().oauth2(SupplySyncToken.getToken());
    }
    @Then("i provid request body to create new company")
    public void i_provid_request_body_to_create_new_company() {
        requestBody.setLogo(faker.company().logo());
        requestBody.setName(faker.company().name());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setAddress(faker.address().fullAddress());
        requestBody.setPhoneNumber(faker.phoneNumber().phoneNumber());
    }
    @Then("i hit  to create company {string}")
    public void i_hit_to_create_company(String endpoint) throws JsonProcessingException {
        response = request.contentType(ContentType.JSON).body(requestBody).post(endpoint);
        customResponse = mapper.readValue(response.asString(), CustomResponse.class);

    }
    @Then("i checks status code equals {string}")
    public void i_checks_status_code_equals(String statusCode) {
        Assert.assertEquals(statusCode,response.getStatusCode());
    }
    @Then("i get id from new created company")
    public void i_get_id_from_new_created_company() {
        id = customResponse.getId();
    }

    @Then("i provid request body with updated the company name {string}")
    public void i_provid_request_body_with_updated_the_company_name(String newCompany) {
        requestBody.setName(newCompany);

    }

    @Then("i provide valid id and hit {string}")
    public void i_provide_valid_id_and_hit(String endpoint) throws JsonProcessingException {
       response = request.contentType(ContentType.JSON).body(requestBody).put(endpoint + id);
        customResponse = mapper.readValue(response.asString(), CustomResponse.class);
    }



        @Then("i check if it is updated")
    public void i_check_if_it_is_updated() {
            System.out.println(customResponse.getName());

    }

}
