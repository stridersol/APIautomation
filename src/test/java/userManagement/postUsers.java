package userManagement;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.*;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import Core.StatusCode;
import io.restassured.response.Response;

public class postUsers {
	
	private static FileInputStream fileInputStream(String requestBodyFileName) {
		FileInputStream fileInputStream = null;
		try {
			new FileInputStream(
					new File(System.getProperty("user.dir") + "/Resources/TestData/" + requestBodyFileName));
		}catch(FileNotFoundException e) {
			throw new RuntimeException();
		}
				
		return fileInputStream;
	}

	@Test
	public void validatePostWithString() {

		Response response = given().header("Content-Type", "application/json")
				.body("{\"name\":\"morpheus\",\"job\":\"leader\"}").when().post("https://reqres.in/api/users");

		assertEquals(response.statusCode(), StatusCode.CREATED.code);
		System.out.println("Validate Post with String executed succesfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePutWithString() {

		Response response = given().auth().none().header("Content-Type", "application/json")
				.body("{\"name\":\"morpheus\",\"job\":\"zionresident\"}").when().put("https://reqres.in/api/users/2");

		assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
		System.out.println("Validate put with String executed succesfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePatchWithString() {

		Response response = given().auth().none().header("Content-Type", "application/json")
				.body("{\"name\":\"morpheus\",\"job\":\"zionresident\"}").when().put("https://reqres.in/api/users/2");

		assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
		System.out.println("Validate patch with String executed succesfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePostWithJsonFile() throws FileNotFoundException, IOException {

		Response response = given().header("Content-Type", "application/json")
				.body(IOUtils.toString(fileInputStream("postRequestBody.json")))
				.when().post("https://reqres.in/api/users");

		assertEquals(response.statusCode(), StatusCode.CREATED.code);
		System.out.println("Validate Post with outside file executed succesfully");
		System.out.println(response.getBody().asString());
	}
	
	@Test
	public void validatePatchWithJsonFile() throws FileNotFoundException, IOException {

		Response response = given().header("Content-Type", "application/json")
				.body(IOUtils.toString(fileInputStream("patchRequestBody.json")))
				.when().post("https://reqres.in/api/users");

		assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
		System.out.println("Validate patch with outside file executed succesfully");
		System.out.println(response.getBody().asString());
	}
	
	@Test
	public void validatePutWithJsonFile() throws FileNotFoundException, IOException {

		Response response = given().header("Content-Type", "application/json")
				.body(IOUtils.toString(fileInputStream("putRequestBody.json")))
				.when().post("https://reqres.in/api/users");

		assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
		System.out.println("Validate put with outside file executed succesfully");
		System.out.println(response.getBody().asString());
	}
}
