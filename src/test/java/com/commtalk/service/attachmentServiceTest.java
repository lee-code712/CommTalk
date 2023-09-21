package com.commtalk.service;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;

@SpringBootTest(classes=CommtalkApplication.class)
public class attachmentServiceTest {

	@Autowired
	private AttachmentService attachSvc;
	
	@Test
	public void loadPostimages() {
		try {
			String jsonStr = attachSvc.loadPostImages(1L);
			System.out.println(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
