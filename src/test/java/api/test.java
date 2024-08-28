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
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utilities.Config;
import utilities.Driver;
import utilities.SupplySyncToken;

import java.util.HashMap;
import java.util.Map;

public class test {
    private RequestSpecification request;
    private String Url = Config.getProperty("supplySyncAPIURL");
    private String token = SupplySyncToken.getToken();
    private final String baseUrl = Config.getProperty("supplySyncAPIURL");
    @Test
    public void login(){

       Response response = RestAssured.given().auth().oauth2(token).delete(baseUrl + "/api/v1/tariffs/140");

        request = RestAssured.given().baseUri(Url);
        System.out.println(Url);
        System.out.println(response.getStatusCode());
    }
}