public class JsontraversalApplication {

	public static void main(String[] args) {
		JsonTraversalTest jsonTraversalTest = new JsonTraversalTest();
		
		String requestPayload = "{\n"
				+ "  \"data\": [{\n"
				+ "    \"type\": \"articles\",\n"
				+ "    \"id\": \"1\",\n"
				+ "    \"attributes\": {\n"
				+ "      \"title\": \"JSON:API paints my bikeshed!\",\n"
				+ "      \"body\": \"The shortest article. Ever.\",\n"
				+ "      \"created\": \"2015-05-22T14:56:29.000Z\",\n"
				+ "      \"updated\": \"2015-05-22T14:56:28.000Z\"\n"
				+ "    },\n"
				+ "    \"relationships\": {\n"
				+ "      \"author\": {\n"
				+ "        \"data\": {\"id\": \"42\", \"type\": \"people\"}\n"
				+ "      }\n"
				+ "    }\n"
				+ "  }],\n"
				+ "  \"included\": [\n"
				+ "    {\n"
				+ "      \"type\": \"people\",\n"
				+ "      \"id\": \"42\",\n"
				+ "      \"attributes\": {\n"
				+ "        \"name\": \"John\",\n"
				+ "        \"age\": 80,\n"
				+ "        \"gender\": \"male\"\n"
				+ "      }\n"
				+ "    }\n"
				+ "  ]\n"
				+ "}";
		
		jsonTraversalTest.traverseJsonStringUsingJackson(requestPayload);
		jsonTraversalTest.traverseJsonStringUsingGson(requestPayload);
	}

}
