package com.commtalk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(classes=CommtalkApplication.class)
public class commonServiceTest {

	@Autowired
	private CommonService commonService;
	
	@Test
	public void testGetCategoriesWithBoards() {
		try {
			String jsonStr = commonService.getCategoriesWithBoards();
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
