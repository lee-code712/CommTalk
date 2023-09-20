package com.commtalk.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(classes=CommtalkApplication.class)
public class authServiceTest {

	@Autowired
	private AuthService authService;
	
	@Test
	public void testJoin() {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("nickname", "admin");
			map.put("password", "admin");
			map.put("username", "admin");
			map.put("email", "admin@example.com");
			
			String jsonStr = authService.join(map);
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLogin() {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("nickname", "test");
			map.put("password", "1234");
			
			String jsonStr = authService.login(map);
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
}
