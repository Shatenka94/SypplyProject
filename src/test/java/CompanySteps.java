//package steps;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.javafaker.Company;
//import com.github.javafaker.Faker;
//import entities.CustomResponse;
//import entities.RequestBody;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.Assert;
//import org.junit.runner.Runner;
//import utilities.Config;
//import utilities.SupplySyncToken;
//
//import java.util.List;
//
//public class CompanySteps {
//    RequestBody requestBody = new RequestBody();
//    Response response;
//    RequestSpecification request;
//    Faker faker = new Faker();
//    String logo;
//    String name;
//    String email;
//    String address;
//    String phoneNumber;
//    CustomResponse customResponse;
//    String id;
//    Response response1;
//    ObjectMapper mapper = new ObjectMapper();
//    private static final Logger logger = LogManager.getLogger(Runner.class);
//
//
//    @Given("supplySync base URL")
//    public void supply_sync_base_url() {
//        request = RestAssured.given().baseUri(Config.getProperty("baseSupplySync"));
//        logger.info("user on the base url");
//
//    }
//
//    @Given("I provide a valid authorization token")
//    public void i_provide_a_valid_authorization_token() {
//        request = request.auth().oauth2(SupplySyncToken.getToken());
//        logger.info("user provide valid authorization");
//    }
//
//    @Given("I provide valid request body")
//    public void i_provide_valid_request_body() {
//        logo = faker.company().logo();
//        name = faker.company().name();
//        email = faker.internet().emailAddress();
//        address = faker.address().streetAddress();
//        phoneNumber = faker.phoneNumber().phoneNumber();
//        requestBody.setLogo(logo);
//        requestBody.setName(name);
//        requestBody.setEmail(email);
//        requestBody.setAddress(address);
//        requestBody.setPhoneNumber(phoneNumber);
//        request = request.contentType(ContentType.JSON).body(requestBody);
//        logger.info("user create company with given informanion");
//    }
//
//    @When("I send a POST request to create a company")
//    public void i_send_a_post_request_to_create_a_company() throws JsonProcessingException {
//        response = request.post("/api/v1/company");
////       response.prettyPrint();
//        customResponse = mapper.readValue(response.asString(), CustomResponse.class);
////        id = customResponse.getId();
//        logger.info("user send POST request");
//    }
//
//    @Then("the response status code should be {int}")
//    public void the_response_status_code_should_be(Integer code) {
//        Assert.assertEquals((int) code, response.getStatusCode());
//        logger.info("user verify status code");
//
//    }
//
//    @Then("response body should contain correct informations")
//    public void response_body_should_contain_correct_informations() throws JsonProcessingException {
//        customResponse = mapper.readValue(response.asString(), CustomResponse.class);
//        Assert.assertEquals(logo, customResponse.getLogo());
//        response.prettyPrint();
//        logger.info("response body should contain all information");
//
//
//    }
//
//    @Then("I hit Delete api using that ID")
//    public void i_hit_delete_api_using_that_id() {
//        response1 = RestAssured.given().baseUri(Config.getProperty("baseSupplySync")).auth().oauth2(SupplySyncToken.getToken())
//                .delete("/api/v1/company/" + customResponse.getId());
//        logger.info("user delete api using that id");
//    }
//
//    @Then("i check status code should be {int}")
//    public void i_check_status_code_should_be(Integer int1) {
//        Assert.assertEquals((int) int1, response1.getStatusCode());
//        logger.info("status code should be 200");
//    }
//
//
//    @When("I hit GET endpoint {string}")
//    public void i_hit_get_endpoint(String endpoint) throws JsonProcessingException {
//        response1 = request.get(endpoint);
////        response1.prettyPrint();
//        logger.info("user hit get endpoint");
//
//        customResponse = mapper.readValue(response1.asString(), CustomResponse.class);
//
//    }
//
//    @Then("Verify status code is {int}")
//    public void verify_status_code_is(Integer code) {
//        Assert.assertEquals((int) code, response1.getStatusCode());
//        logger.info("status code 200");
//
//
//    }
//
//    @Then("Verify response body contains a list of companies")
//    public void verify_response_body_contains_a_list_of_companies() throws JsonProcessingException {
//
//        int size = customResponse.getCompany().size();
//        boolean isThere = false;
//        for (int i = 0; i < size; i++) {
////          if (customResponse.getCompany().get(i).getId().equals(id)) {
////                isThere = true;
////            }
////
////        }
////
////       Assert.assertTrue(isThere);
//        }
//
//
//    }
//}