package com.commtalk.controller;

import java.nio.file.Files;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commtalk.controller.exception.ErrorMsg;
import com.commtalk.controller.exception.ExceptionUtils;
import com.commtalk.service.AttachmentService;

@RestController
@RequestMapping(value="/api/attach")
public class AttachmentController {
	
	@Resource
	private AttachmentService attachSvc;

	@GetMapping("/loadImages/{postId}")
    public ResponseEntity<?> loadPostImages(@PathVariable Long postId) {
    	MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		String response = "[]";

		try {
	    	response = attachSvc.loadPostImages(postId);
			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}

    }
	
}
