import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonTraversalTest {

	private static ObjectMapper mapper = new ObjectMapper();

	public String traverseJsonStringUsingJackson(String requestBody) {
		JacksonService jacksonService = new JacksonService();
		JsonNode jsonNode;
		
		System.out.println("Before : RequestBody is ");
		System.out.println(requestBody);
		System.out.println("Traversal and Manipulation Using Jackson");
		try {
			jsonNode = mapper.readTree(requestBody);
			JsonNode updated = jacksonService.traverse(jsonNode);
			System.out.println(updated.toString());
			return updated.toString();
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String traverseJsonStringUsingGson(String requestBody) {
		GsonService gsonService = new GsonService();
		JsonElement jsonElement;
		jsonElement = new JsonParser().parse(requestBody);
		System.out.println("Traversal and Manipulation Using Gson");
		JsonElement updated = gsonService.traverse(jsonElement);
		System.out.println(updated.toString());
		return updated.toString();

	}

}
