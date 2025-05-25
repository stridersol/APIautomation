package fruityJuice;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.FruitJuiceList;
import pojo.Nutritions;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class getFruityJuice {

	@Test
	public void getFruityJuiceList() {
		FruitJuiceList obj = new FruitJuiceList();
		obj.setFamily("dd");
		obj.setGenus("dd");

		Nutritions objNut = new Nutritions();
		objNut.setCalories(333);
		obj.setNutritions(objNut);

		Response response = given().body(obj).get("https://fruityvice.com/api/fruit/all");
		JsonPath json = response.jsonPath();

		List<FruitJuiceList> list = json.getList("", FruitJuiceList.class);

		for (FruitJuiceList ls : list) {
			System.out.println("Fruite Name " + ls.getName() + " Nutrition Value " + ls.getNutritions().getCalories());
		}
	}
}
