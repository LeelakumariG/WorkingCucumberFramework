package data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

public class DataReader {

	public List<HashMap<String, String>> getJSONDataToMap(String string, String string2) throws IOException {

		// Read JSON file to a string
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "//src//test//java//data//purchaseOrder.json"), "UTF-8");

		// Convert JSON string to List<HashMap<String, String>> using Jackson
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
}
