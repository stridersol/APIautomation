package userManagement;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

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
		
		//Assert that the first user in the list has the correct values
		response.then().body("data[0].id",is(7));
		response.then().body("data[0].email",is("michael.lawson@reqres.in"));
		response.then().body("data[0].first_name",is("Michael"));
		response.then().body("data[0].last_name",is("Lawson"));
		response.then().body("data[0].avatar",is("https://reqres.in/img/faces/7-image.jpg"));

		
		
				
			
		
		
	}

	}
