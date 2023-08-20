package com.commtalk.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.commtalk.dto.CategoryDTO;
import com.commtalk.model.Category;
import com.commtalk.repository.CategoryRepository;
import com.commtalk.utils.JSONFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class CommonService {
	
	@Resource
	private CategoryRepository categoryRepo;
	
	// 카테고리별 게시판 조회
	public String getCategoriesWithBoards() throws JsonProcessingException {

		List<Category> categories = categoryRepo.findAllWithBoards();
		List<CategoryDTO> categoryDTOs = categories.stream()
				.map(category -> new CategoryDTO(category))
                .collect(Collectors.toList());
		
		return JSONFactory.getJSONStringFromList(categoryDTOs);
		
	}
	
	// 조회수가 많은 순으로 게시글 조회

}
