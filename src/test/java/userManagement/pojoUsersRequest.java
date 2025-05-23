package userManagement;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import Core.StatusCode;
import Utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.UsersCreate;
import pojo.cityRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class pojoUsersRequest {

	@Test
	public void postUserRequest() {
		List<String> languages = new ArrayList<>();
		languages.add("java");
		languages.add("python");
		
		cityRequest cityRequest1 = new cityRequest("Bangalore", "30");
		cityRequest cityRequest2 = new cityRequest("Delhi","32");
		
		List<cityRequest> cityRequest = new ArrayList<>();
		cityRequest.add(cityRequest1);
		cityRequest.add(cityRequest2);
		
		UsersCreate obj = new UsersCreate("morpheus", "leader",languages,cityRequest);

		given()
		.header("x-api-key", "reqres-free-v1")
		.header("content-type","application/json")
		.body(obj).log().all()
		.when()
		.post("https://reqres.in/api/users")
		.then().log().all().statusCode(201);
	}
	
	@Test
	public void putUserRequest() {
		List<String> languages = new ArrayList<>();
		languages.add("Hindi");
		languages.add("sankrit");
		
		cityRequest cityRequest1 = new cityRequest("Bangalore", "30");
		cityRequest cityRequest2 = new cityRequest("Delhi","32");
		
		List<cityRequest> cityRequest = new ArrayList<>();
		cityRequest.add(cityRequest1);
		cityRequest.add(cityRequest2);
		
		UsersCreate put = new UsersCreate("Ajay", "leader",languages,cityRequest);

		given()
		.header("x-api-key", "reqres-free-v1")
		.header("content-type","application/json")
		.body(put).log().all()
		.when()
		.put("https://reqres.in/api/users/2")
		.then().log().all().statusCode(200);
	}
	
	@Test
	public void patchUserRequest() {

		UsersCreate patch = new UsersCreate();
		patch.setName("Sachin");
		given()
		.header("x-api-key", "reqres-free-v1")
		.header("content-type","application/json")
		.body(patch).log().all()
		.when()
		.patch("https://reqres.in/api/users/2")
		.then().log().all().statusCode(200);
	}
	
	@Test
	public void userInfo() {
		
	Response response=	given()
		.header("x-api-key", "reqres-free-v1")
		.when()
		.get("https://reqres.in/api/users/12");
		/*.then().body("name", equalTo("morpheus")).log().all();*/
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getStatusCode());

	}
	
	@Test
	public void patchUserRequestDeserialize() {

		UsersCreate patch = new UsersCreate();
		patch.setName("Sachin");
		
		Response response =given()
		.header("x-api-key", "reqres-free-v1")
		.header("content-type","application/json")
		.body(patch)
		.when()
		.patch("https://reqres.in/api/users/2");
		
		UsersCreate responseBody = response.as(UsersCreate.class);
		System.out.println(responseBody.getName());
		assertEquals(responseBody.getName(),"Sachin");
		
		assertEquals(response.getStatusCode(),200);
		System.out.println(response.asPrettyString());
		
		
		
	}
	

}
