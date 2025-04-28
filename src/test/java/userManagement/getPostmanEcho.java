package userManagement;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import Core.StatusCode;
import Utils.JsonReader;
import io.restassured.response.Response;

public class getPostmanEcho {

	@Test(groups = "SmokeSuite")
	public void validateResponseBodyGetBasicAuth() {
		Response response = given().auth().basic("postman", "password").when()
				.get("https://postman-echo.com/basic-auth");

		int actualStatusCode = response.getStatusCode();
		assertEquals(actualStatusCode, StatusCode.SUCCESS.code);
		System.out.println(response.body().asString());
	}

	@Test
	public void validateResponseBodyGetDigestAuth() {
		Response response = given().auth().digest("postman", "password").when()
				.get("https://postman-echo.com/digest-auth");

		int actualStatusCode = response.getStatusCode();
		assertEquals(actualStatusCode, StatusCode.SUCCESS.code);
		System.out.println(response.body().asString());
	}
	
	@Test
	public void validateWithTEstDataFromJson() throws IOException, ParseException {
		String username = JsonReader.getTestData("username");
		String password = JsonReader.getTestData("password");
		System.out.println(username +" : "+ password);
		 
		Response resp = given()
				.auth()
				.basic(username,password)
				.when()
				.get("https://postman-echo.com/basic-auth");
		
		int actualStatusCode = resp.statusCode();
		assertEquals(actualStatusCode,StatusCode.SUCCESS.code);
		}
}