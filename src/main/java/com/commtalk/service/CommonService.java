package com.commtalk.service;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.commtalk.dto.CategoryDTO;
import com.commtalk.dto.PostDTO;
import com.commtalk.model.Category;
import com.commtalk.model.Post;
import com.commtalk.repository.AttachmentRepository;
import com.commtalk.repository.CategoryRepository;
import com.commtalk.repository.EngagementActionRepository;
import com.commtalk.repository.PostRepository;
import com.commtalk.util.JSONFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class CommonService {
	
	@Resource
	private CategoryRepository categoryRepo;
	@Resource
	private PostRepository postRepo;
	@Resource
	private AttachmentRepository attachmentRepo;
	@Resource
	private EngagementActionRepository engagementActionRepo;
	
	// 카테고리별 게시판 조회
	public String getCategoriesWithBoards() throws JsonProcessingException {

		List<Category> categories = categoryRepo.findAllWithBoards();
		List<CategoryDTO> categoryDTOs = categories.stream()
				.map(category -> new CategoryDTO(category))
                .collect(Collectors.toList());
		
		return JSONFactory.getJSONStringFromList(categoryDTOs);
		
	}
	
	// 제목 또는 내용으로 게시글 검색
	public String getPostsByKeyword(String keyword, Pageable pageable) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<>();
		
		Page<Post> postPages = postRepo.findByTitleOrContent(keyword, pageable);
		response.put("totalPages", postPages.getTotalPages());
		response.put("pageNumber", postPages.getNumber());
		response.put("previous", postPages.previousPageable());
		response.put("next", postPages.nextPageable());
        
        List<PostDTO> postDTOs = new ArrayList<>();
		for (Post post : postPages) {
			if (!post.getIsDeleted()) {
				postDTOs.add(new PostDTO(post));
			}		    
		}
		response.put("posts", postDTOs);
		
		return JSONFactory.getJSONStringFromMap(response);
	}

}
