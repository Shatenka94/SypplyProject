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

import java.util.HashMap;
import java.util.Map;

public class test {
    private RequestSpecification request;
    private String Url = Config.getProperty("supplysyncApiUrl");
    @Test
    public void login(){

        request = RestAssured.given().baseUri(Url);
        System.out.println(Url);
    }
}