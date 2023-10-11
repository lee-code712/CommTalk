package com.commtalk.service;

import javax.annotation.Resource;

import com.commtalk.model.EngagementAction;
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
	public String getPostsByKeyword(String keyword, Long memberId, Pageable pageable) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<>();
		
		Page<Post> postPages = postRepo.findByTitleOrContent(keyword, pageable);
		response.put("totalPages", postPages.getTotalPages());
		response.put("pageNumber", postPages.getNumber());
		if (postPages.hasPrevious()) {
			response.put("previous", postPages.previousPageable().getPageNumber());
		}
		else {
			response.put("previous", -1);
		}
		if (postPages.hasNext()) {
			response.put("next", postPages.nextPageable().getPageNumber());
		}
		else {
			response.put("next", -1);
		}
        
        List<PostDTO> postDTOs = new ArrayList<>();
		if (memberId != null) {
			for (Post post : postPages) {
				if (!post.getIsDeleted()) {
					postDTOs.add(setPostWithEngagementAction(memberId, post));
				}
			}
		}
		else {
			for (Post post : postPages) {
				if (!post.getIsDeleted()) {
					postDTOs.add(new PostDTO(post, false, false));
				}
			}
		}
		response.put("posts", postDTOs);
		
		return JSONFactory.getJSONStringFromMap(response);
	}

	private PostDTO setPostWithEngagementAction(Long memberId, Post post) {
		boolean isLiked = false;
		boolean isScraped = false;

		List<EngagementAction> engagementActions = engagementActionRepo.findByMemberIdAndRefId(memberId, post.getId());
		if (engagementActions != null) {
			for (EngagementAction engagementAction : engagementActions) {
				if (engagementAction.getAction() == EngagementAction.ActionType.plike) {
					isLiked = true;
				} else if (engagementAction.getAction() == EngagementAction.ActionType.scrap) {
					isScraped = true;
				}
			}
		}

		return new PostDTO(post, isLiked, isScraped);
	}

}
