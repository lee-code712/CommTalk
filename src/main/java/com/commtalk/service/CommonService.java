package com.commtalk.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.commtalk.dto.CategoryDTO;
import com.commtalk.dto.PostPreviewDTO;
import com.commtalk.dto.PostDTO;
import com.commtalk.model.Category;
import com.commtalk.model.Post;
import com.commtalk.repository.CategoryRepository;
import com.commtalk.repository.PostRepository;
import com.commtalk.utils.JSONFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class CommonService {
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private PostRepository postRepo;
	
	// 카테고리별 게시판 조회
	public String getCategoriesWithBoards() throws JsonProcessingException {

		List<Category> categories = categoryRepo.findAllWithBoards();
		List<CategoryDTO> categoryDTOs = categories.stream()
				.map(category -> new CategoryDTO(category))
                .collect(Collectors.toList());
		
		return JSONFactory.getJSONStringFromList(categoryDTOs);
		
	}
	
	// 조회수가 많은 순으로 게시글 4개 조회
	public String getPopularPostsByViews() throws JsonProcessingException {
		
		List<Post> posts = postRepo.findTop4ByViewsWithCommentsAndBoard();
		List<PostPreviewDTO> postDTOs = posts.stream()
				.map(post -> new PostPreviewDTO(post))
				.collect(Collectors.toList());
		
		return JSONFactory.getJSONStringFromList(postDTOs);
	}
	
	// 제목 또는 내용으로 게시글 검색
	public String getPostsByKeyword(String keyword) throws JsonProcessingException {
		
		List<Post> posts = postRepo.findByTitleOrContent(keyword);
		List<PostDTO> postDTOs = posts.stream()
				.map(post -> new PostDTO(post))
				.collect(Collectors.toList());	
		
		return JSONFactory.getJSONStringFromList(postDTOs);
	}

}
