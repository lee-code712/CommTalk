package com.commtalk.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONFactory {

	public static String getJSONStringFromList(List<?> list) throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = objectMapper.writeValueAsString(list);
			
		return jsonStr;
	}
}
