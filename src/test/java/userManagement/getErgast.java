package userManagement;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import Core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getErgast {
	
	@Test(groups = "RegressionSuite")
	public void testGetUsersWithPathParams() {
		String raceSeasonValue = "2017";
		
		//Set base URI for the API
		RestAssured.baseURI = "http://ergast.com";
		
		//Send a GET request and store the response in a variable
		Response response = given()
				.pathParam("raceSeason",raceSeasonValue)
				.when()
				.get("/api/f1/{raceSeason}/circuits.json")
				.then()
				.statusCode(StatusCode.SUCCESS.code)
				.extract()
				.response();
		System.out.println(response.body().asString());
	}
}
