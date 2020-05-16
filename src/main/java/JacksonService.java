import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;
import java.util.Map;

public class JacksonService {
	
	private static ObjectMapper mapper = new ObjectMapper();

	public JsonNode traverse(JsonNode node) {
		if (node.isValueNode()) {
			if (JsonNodeType.STRING == node.getNodeType()) {
				return JsonNodeFactory.instance.textNode(StringUtils.encodeValue(node.asText()));  // You can use any other encoding function as well!
			} else if (JsonNodeType.NULL == node.getNodeType()) {
				return null;
			} else {
				return node;
			}
		} else if (node.isNull()) {
			return null;
		} else if (node.isArray()) {
			ArrayNode arrayNode = (ArrayNode) node;
			ArrayNode cleanedNewArrayNode = mapper.createArrayNode();
			for (JsonNode jsonNode : arrayNode) {
				cleanedNewArrayNode.add(traverse(jsonNode));
			}
			return cleanedNewArrayNode;
		} else {
			ObjectNode encodedObjectNode = mapper.createObjectNode();
			for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {
				Map.Entry<String, JsonNode> entry = it.next();
				encodedObjectNode.set(StringUtils.encodeValue(entry.getKey()), traverse(entry.getValue()));
			}
			return encodedObjectNode;
		}
	}
	
}
