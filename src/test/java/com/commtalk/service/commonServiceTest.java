package com.commtalk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

	@Test
	public void testGetPostsByKeyword() {
		try {
			Pageable pageable = (Pageable) PageRequest.of(1, 1);
			String jsonStr = commonService.getPostsByKeyword("글", null, pageable);
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
}
