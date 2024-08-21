package api;

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
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utilities.Config;
import utilities.Driver;

import java.util.HashMap;
import java.util.Map;

public class test {
    @Test
    public void testToken() {
        String endPoint = "https://backend.cashwise.us/api/myaccount/auth/login";
        RequestBody requesBody = new RequestBody();
        requesBody.setEmail("olesmakarenko@gmail.com");
        requesBody.setPassword("12345678");
        Response response = RestAssured.given().contentType(ContentType.JSON).body(requesBody).post(endPoint);
        int statusCode = response.statusCode();
        Assert.assertEquals(200, statusCode);
        response.prettyPrint();
        String token = response.jsonPath().getString("jwt_token");
        System.out.println(token);
    }

    @Test
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

