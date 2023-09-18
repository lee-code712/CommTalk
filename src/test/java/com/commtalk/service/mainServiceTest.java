package com.commtalk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(classes=CommtalkApplication.class)
public class mainServiceTest {
	
	@Autowired
	MainService mainService;
	
	@Test
	public void testGetPinnedBoardsWithPosts() {
		try {
			String jsonStr = mainService.getPinnedBoardsWithPosts(null);
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
