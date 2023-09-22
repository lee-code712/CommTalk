package com.commtalk.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commtalk.controller.exception.ErrorMsg;
import com.commtalk.controller.exception.ExceptionUtils;
import com.commtalk.dto.AttachmentSimpleDTO;
import com.commtalk.util.CommonFileUtils;

@RestController
@RequestMapping(value="/api/file")
public class FileController {
	
	@Autowired
	private CommonFileUtils fileUtils;

	@GetMapping("/load/{fileName}")
    public ResponseEntity<?> loadPostImages(@PathVariable String fileName) {
    	MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();

		try {
	    	Path imageFilePath = Paths.get(fileUtils.createPath(fileName));
	        Resource resource = new UrlResource(imageFilePath.toUri());
	        
	        if (resource.exists() && resource.isReadable()) {
	        	header.add("Content-Type", Files.probeContentType(imageFilePath));
	            return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
	        }
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}

    }
	
}
