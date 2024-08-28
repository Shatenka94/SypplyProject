package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponse;
import entities.RequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import runner.runnerClass;
import utilities.Config;
import utilities.SupplySyncToken;

import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class tariffSteps {
    private RequestSpecification request;
    private Response response;
    private ObjectMapper mapper = new ObjectMapper();
    private String token = SupplySyncToken.getToken();
    private final String baseUrl = Config.getProperty("supplySyncAPIURL");
    private CustomResponse customResponse;
    Response response1;
    String id;


    @Given("supplySync base URL")
    public void supply_sync_base_url() {
        request = RestAssured.given().baseUri(baseUrl);
    }

    @Given("I provide a VALID authorization token")
    public void i_provide_valid_authorization_token() {
        request.auth().oauth2(token).contentType(ContentType.JSON);
    }


    @When("I send a POST request to create a tariff with the following details")
    public void i_send_a_post_request_to_create_a_tariff_with_the_following_details() throws JsonProcessingException {
        RequestBody requestBody = new RequestBody();
        requestBody.setName("Oles Tariff");
        requestBody.setTime("one hour");
        requestBody.setPrice(1);
        requestBody.setMap(true);
        requestBody.setCoordinates("1025 michigan ave");
        requestBody.setAdditionalInformation("deliver before 5");
        requestBody.setType("CITY");
        requestBody.setBranchId(1);
        requestBody.setRegionId(1);
        response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(baseUrl + "/api/v1/tariffs");
        int sellerId = response.jsonPath().getInt("id");
        System.out.println(sellerId + " original seller id");
        int status = response.statusCode();

        Assert.assertEquals(200, status);
        response = request.body(requestBody).post("/api/v1/tariffs");
        System.out.println(response.prettyPrint());
        //     customResponse = mapper.readValue(response.asString(), CustomResponse.class);

    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        int status = response.statusCode();
        System.out.println(status);
        Assert.assertEquals(status, response.getStatusCode());
    }



    @Then("the response should contain the name {string}")
    public void the_response_should_contain_the_name(String name) {
        String expectedname = response.jsonPath().getString("name");
        Assert.assertEquals(name, expectedname);
    }



    @Then("the response should contain an id field")
    public void the_response_should_contain_an_id_field() {
        int id=response.jsonPath().getInt("id");
        Assert.assertNotNull("ID field should not be null",id);
    }

    @Given("I hit GET endpoint {string}")
    public void i_hit_get_endpoint(String endpoint) throws JsonProcessingException {
        response1 = request.get(endpoint);
       response1.prettyPrint();


        customResponse = mapper.readValue(response1.asString(), CustomResponse.class);

    }

    @Then("Verify status code is {int}")
    public void verify_status_code_is(Integer int1) {
        Assert.assertEquals((int) int1, response1.getStatusCode());

    }

    @Then("Verify response body contains a list of companies")
    public void verify_response_body_contains_a_list_of_companies() throws JsonProcessingException {
        int size = customResponse.getCompany().size();
        boolean isThere = false;
        for (int i = 0; i < size; i++) {
          if (customResponse.getCompany().get(i).getId().equals(id)) {
                isThere = true;
            }

        }

       Assert.assertTrue(isThere);
    }
}
