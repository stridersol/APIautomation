package Utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

	public static String getTestData(String key) throws IOException, ParseException {
		String testdata;
		return testdata = (String) getJsonData().get(key);// input is the key
	}

	private static JSONObject getJsonData() throws IOException, ParseException {

		// pass the path of the testdata.json file
		File filename = new File("resources//TestData//testdata.json");

		// convert json file into String
		String json = FileUtils.readFileToString(filename, "UTF-8");

		// parse the string into object
		Object obj = new JSONParser().parse(json);

		// give json object o that i can return it to the function everytime it get
		// called
		JSONObject jsonObject = (JSONObject) obj;

		return jsonObject;
	}
	
	public static JSONArray getJsonArray(String key) throws IOException, ParseException {
		JSONObject jsonOBject = getJsonData();
		JSONArray jsonArray = (JSONArray) jsonOBject.get(key);
		return jsonArray;
	}
	
	/**
	 * it will return key and corresponding values inside that array
	 * @param key
	 * @param index
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static Object getJsonArrayData(String key,int index) throws IOException, ParseException {
		JSONArray languages = getJsonArray(key);
		return languages.get(index);
	}
	
	/**
	 * it will print key data it contains the key value inside key
	 * @param key
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void getJsonArrayDataIterator(String key) throws IOException, ParseException {		
		JSONArray jsonArray = getJsonArray(key);
		Iterator<String> iterator = jsonArray.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	
}
