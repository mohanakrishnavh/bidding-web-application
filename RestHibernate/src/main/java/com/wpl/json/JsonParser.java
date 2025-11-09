package com.wpl.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

	public static Object parseJson(String json, Class clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(json, clazz);

	}

	public static String parseJsonFromStream(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(stream, String.class);

	}

	public static Map<String, Object> parseJsonMap(String json)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
		});

	}

	public static Map<String, Object> parseJsonMapFromStream(InputStream inputStream)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(inputStream, new TypeReference<HashMap<String, Object>>() {
		});

	}

	public static String convertToJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);

	}

}