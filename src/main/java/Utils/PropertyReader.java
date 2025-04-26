package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	public static String propertyReader(String filePath, String key) {
		String value = null;

		// inputstream is required while loading into properties
		try (InputStream input = new FileInputStream(filePath)) {

			// object creation for Property class
			Properties prop = new Properties();

			// load a properties file
			prop.load(input);

			// getProperty will fetch the value according to the key
			value = prop.getProperty(key);

		} catch (IOException e) {
			e.printStackTrace(); //return logs
		}
		return value;
	}
}
