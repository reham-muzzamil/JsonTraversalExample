import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Map;

public class GsonService {

		public JsonElement traverse(JsonElement element) {
			if (element.isJsonPrimitive()) {
				JsonPrimitive primitive = element.getAsJsonPrimitive();
				if (primitive != null && primitive.isString()) {
					return new JsonPrimitive(StringUtils.encodeValue(primitive.getAsString()));  // You can use any other encoding function as well!
				} else {
					return primitive;
				}
			} else if (element.isJsonArray()) {
				JsonArray jsonArray = element.getAsJsonArray();
				JsonArray cleanedNewArray = new JsonArray();
				for (JsonElement jsonElement : jsonArray) {
					cleanedNewArray.add(traverse(jsonElement));
				}
				return cleanedNewArray;
			} else if (element.isJsonNull()) {
				return element.getAsJsonNull();
			} else {
				JsonObject obj = element.getAsJsonObject();
				JsonObject encodedJsonObject = new JsonObject();
				for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
					encodedJsonObject.add(StringUtils.encodeValue(entry.getKey()), traverse(entry.getValue()));
				}
				return encodedJsonObject;
			}
		}

}
