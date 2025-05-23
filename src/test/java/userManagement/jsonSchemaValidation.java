package userManagement;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class jsonSchemaValidation {
	
	@Test
	public void getUserData() {
		File schema = new File("resources/expectedSchema.json");

		given().
		when().get("https://reqres.in/api/users?page=2")
		.then()
		.assertThat()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchema(schema));
				
	}

}
