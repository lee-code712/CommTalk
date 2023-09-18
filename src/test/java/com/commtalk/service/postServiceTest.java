package com.commtalk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.commtalk.CommtalkApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(classes=CommtalkApplication.class)
public class postServiceTest {
	
	@Autowired
	PostService postService;
	
	@Test
	public void getPostsByBoard() {
		try {
			Pageable pageable = (Pageable) PageRequest.of(0, 1);
			String jsonStr = postService.getPostsByBoard(1L, pageable);
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getPostById() {
		try {
			String jsonStr = postService.getPostById(1L);
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
