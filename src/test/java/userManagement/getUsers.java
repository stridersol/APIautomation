package userManagement;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;


public class getUsers {
	
	@Test
	public void getUserData() {
		given().
		when().get("https://regres.in/api/users?page=2").
		then().
		assertThat().
		statusCode(500);	
	}
	
	@Test
	public void validateGetResponseBody() {
		//set base URI for the API
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		//Send a GET request and validate the response body using 'then'
		given() 
				.when()
				.get("/todos/1") 
				.then()
				.assertThat()
				.statusCode(200)
				.body(not(blankString()))
				.body("title",equalTo("delectus aut autem"))
				.body("userId",equalTo(1));	
	}
	
	@Test
	public void validateResponseHasitems() {
		//Set base URI for the API
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		//Send a GET request and store the response in a variable
		Response response = given()
				.when()
				.get("/posts")
				.then()
				.extract()
				.response();
		
		//use Hamcrest to check that the response body contains specific items
		assertThat(response.jsonPath().getList("title"), hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit","qui est esse"));	
	}
	
	@Test
	public void validateResponseHasSize() {
		//set base URI for the API
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		//Send a GET request and store the response in a variable
		Response response = given()
				.when()
				.get("/comments")
				.then()
				.extract()
				.response();
		
		//use Hamcrest to check that the response body contains specific items
		assertThat(response.jsonPath().getList(""),hasSize(500));
	}

	@Test
	public void validateContainsInOrder() {
		//Set base URI for the API
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		//Send a GET request and store the response in a variable
		Response response = given()
				.when()
				.get("/comments?postId=1")
				.then()
				.extract()
				.response();
		
		//Use Hamcrest to check that the response body contains specific items in a specific order
		List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz","Jayne_Kuhic@sydney.com","Nikita@garfield.biz","Lew@alysha.tv","Hayden@althea.biz");
		assertThat(response.jsonPath().getList("email"),contains(expectedEmails.toArray(new String[0])));
	}
	
	@Test
	public void testGetUserWithQueryParameters() {
		//Set base URI for the API
		RestAssured.baseURI = "https://reqres.in/api";
		
		//Send a GET request and store the response in a variable
		Response response = given()
				.queryParam("page", 2)
				.when()
				.get("/users")
				.then()
				.statusCode(200)
				.extract()
				.response();
		
		//Assert that the response contains 6 users
		response.then().body("data", hasSize(6));
		
		//Assert that the first user in the  list has the correct values
		response.then().body("data[0].id",is(7));
		response.then().body("data[0].email",is("michael.lawson@reqres.in"));
		response.then().body("data[0].first_name",is("Michael"));
		response.then().body("data[0].last_name",is("Lawson"));
		response.then().body("data[0].avatar",is("https://reqres.in/img/faces/7-image.jpg"));		
	}
	
	@Test
	public void validateStatusCodeUser() {
		System.out.println("*********");
		
		Response resp = 
				given()
					.queryParam("page",2)
					.when()
					.get("https://reqres.in/api/users");
		int actualStatusCode = resp.statusCode();
		assertEquals(actualStatusCode,200);
	}
	
	@Test
	public void testGetUsersWithMultipleQueryParams() {
		//Set base URI for the API
		RestAssured.baseURI = "https://reqres.in/api";
		
		//Send a GET request and store the response in a variable
		Response response = given()
				.queryParam("page", 2)
				.queryParam("per_page", 2)
				.when()
				.get("/users")
				.then()
				.statusCode(200)
				.extract()
				.response();	
	}
	
	@Test
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
				.statusCode(200)
				.extract()
				.response();
		System.out.println(response.body().asString());
	}
	
	@Test
	public void testGetUsersWithFormParam() {
		RestAssured.baseURI="https://reqres.in";
		
		Response response = 
				given()
				.contentType("application/x-www-form-urlencoded")
				.formParam("name", "John Doe")
				.formParam("job", "Developer")
				.when()
				.post("/users")
				.then()
				.statusCode(201)
				.extract()
				.response();
				
		response.then().body("name", equalTo("John Doe"));
		response.then().body("job",equalTo("Developer"));	
	}
	
	@Test
	public void testUserListwithHeader() {
		RestAssured.baseURI = "https://reqres.in";
		
		given()
				.header("Content-Type","application/json")
				.when()
				.get("/api/users?page=2")
				.then()
				.statusCode(200)
				.body("page", equalTo(2));	
	}
	

	@Test
	public void testUserListwithTwoHeader() {
		RestAssured.baseURI = "https://reqres.in";
		
		given()
				.header("Content-Type","application/json")
				.header("Authorization","bearer ")
				.when()
				.get("/api/users?page=2")
				.then()
				.statusCode(200)
				.body("page", equalTo(2));			
	}
	
	@Test
	public void testTwoHeaderWithMap() {
		RestAssured.baseURI = "https://reqres.in";
		
		//Create a Map to hold Headers
		Map<String,String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "bearer ");
		
		given()
				.headers(headers)
				.when()
				.get("/api/users?page=2")
				.then()
				.statusCode(200)
				.body("page", equalTo(2));			
	}
	
	@Test
	public void testFetchHeaders() {
		Response response = given()
				.when()
				.get("https://reqres.in/api/users?page=2")
				.then()
				.extract()
				.response();
		
		Headers headers = response.getHeaders();
		
		for(Header h : headers) {
			if(h.getName().contains("Server")) {
				assertEquals(h.getValue(),"cloudflare");
				System.out.println(h.getName() + " : " + h.getValue());
			}
		}
	}
}