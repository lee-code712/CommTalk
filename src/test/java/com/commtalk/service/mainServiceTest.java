package com.commtalk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
	public void testPinnedBoards() {
		try {
			List<Long> boardIds = new ArrayList<>();
			boardIds.add(3L);
			boardIds.add(4L);
			Map<String, Object> command = new HashMap<>();
			command.put("boards", boardIds);
			
			mainService.pinnedBoards(10L, (List<Long>) command.get("boards"));
			String jsonStr = mainService.getPinnedBoardsWithPosts(null);
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetPinnedBoardsWithPosts() {
		try {
			String jsonStr = mainService.getPinnedBoardsWithPosts(null);
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetPopularPostsByViews() {
		try {
			String jsonStr = mainService.getPopularPostsByViews();
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetPostsByBoardName() {
		try {
			String jsonStr = mainService.getPostsByBoardName("패션");
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetMember() {
		try {
			String jsonStr = mainService.getMember(10L);
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
