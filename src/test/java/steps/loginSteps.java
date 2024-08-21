package steps;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponse;
import entities.RequestBody;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utilities.Config;
import utilities.Driver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class loginSteps {
    LoginPage loginPage = new LoginPage();
    String loogedOn = "https://supplysync.us/login";

    @When("user is on login page")
    public void user_is_on_login_page() {
        Driver.getDriver().get(Config.getProperty("supplysyncDemo"));
    }

    @When("user enters correct login")
    public void user_enters_correct_login() {
        loginPage.username.sendKeys(Config.getProperty("username"));
    }

    @When("user enters correct password")
    public void user_enters_correct_password() {
        loginPage.password.sendKeys(Config.getProperty("password"));
    }

    @When("user clicks login")
    public void user_clicks_login() {
        loginPage.loginButton.click();
    }

    @Then("verify that user is logged in")
    public void verify_that_user_is_logged_in() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals(loogedOn));
    }

    @Then("user clicks on tariff")
    public void user_clicks_on_tariff() {
        loginPage.tariffbutton.click();
    }

    @Then("user gets all tariffs via API")
    public void user_gets_all_tariffs_via_api() {
        String url = "https://backend.supplysync.us/api/v1/tariffs";
        String token = Config.getProperty("supplysyncToken");
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        int expectedstatuscode = 200;
        Assert.assertEquals(response.statusCode(), expectedstatuscode);
    }

    @Then("user create new tariff and verify it was created")
    public void user_create_new_tariff_and_verify_it_was_created() throws JsonProcessingException {
        String url = Config.getProperty("supplysyncApiUrl") + "/api/v1/tariffs";
        String token = Config.getProperty("supplysyncToken");
        RequestBody requestBody = new RequestBody();
        requestBody.setName("Oles");
        requestBody.setTime("one hour");
        requestBody.setPrice(1);
        requestBody.setMap(true);
        requestBody.setCoordinates("1025 michigan ave");
        requestBody.setAdditionalInformation("deliver before 5");
        requestBody.setType("CITY");
        requestBody.setBranchId(1);
        requestBody.setRegionId(1);

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);
        int sellerId= response.jsonPath().getInt("id");
        System.out.println(sellerId+" original seller id");
        int status = response.statusCode();

        Assert.assertEquals(200, status);

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        int ExpectedSellerId = customResponse.getId();
        System.out.println(ExpectedSellerId+" expected");

        Assert.assertEquals(ExpectedSellerId,sellerId);

    }
}



