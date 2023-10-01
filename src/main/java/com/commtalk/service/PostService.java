package com.commtalk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.commtalk.dto.BoardSimpleDTO;
import com.commtalk.dto.CommentDTO;
import com.commtalk.dto.CommentChildDTO;
import com.commtalk.model.*;
import com.commtalk.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.commtalk.dto.PostDTO;
import com.commtalk.dto.PostDetailDTO;
import com.commtalk.util.JSONFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class PostService {

	@Resource
	private MemberRepository memberRepo;
	@Resource
	private BoardRepository boardRepo;
	@Resource
	private PostRepository postRepo;
	@Resource
	private AttachmentRepository attachmentRepo;
	@Resource
	private PostHashtagRepository postHashtagRepo;
	@Resource
	private EngagementActionRepository engagementActionRepo;
	
	// 특정 게시판의 게시글 조회
	public String getPostsByBoard(Long boardId, Long memberId, Pageable pageable) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<String, Object>();
		
		Page<Post> postPages = postRepo.findByBoardOrderByCreatedAt(boardId, pageable);
		response.put("totalPages", postPages.getTotalPages());
		response.put("pageNumber", postPages.getNumber());
		response.put("previous", postPages.previousPageable());
		response.put("next", postPages.nextPageable());
        
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

	// 제목 또는 내용으로 특정 게시판의 게시글 검색
	public String getPostsByBoardAndKeyword(String keyword, Long boardId, Long memberId, Pageable pageable) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<>();

		Page<Post> postPages = postRepo.findByBoardIdAndTitleOrContent(keyword, boardId, pageable);
		response.put("totalPages", postPages.getTotalPages());
		response.put("pageNumber", postPages.getNumber());
		response.put("previous", postPages.previousPageable());
		response.put("next", postPages.nextPageable());

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
	
	// 게시글 상세 조회
	public String getPostById(Long postId, Long memberId) throws JsonProcessingException {

		Post post = postRepo.findById(postId).orElse(null);
		if (post != null) {
			PostDetailDTO postDTO = null;
			if (memberId != null) {
				postDTO = setPostDetailWithEngagementAction(memberId, post);
			}
			else {
				postDTO = new PostDetailDTO(post, false, false);
			}
			List<Comment> comments = post.getComments();
			if (comments.size() > 0) {
				Map<Long, CommentDTO> commentDTOMap = new HashMap<>();
				for (Comment comment : comments) {
					if (comment.getParent() == null) {
						commentDTOMap.put(comment.getId(), setCommentWithEngagementAction(memberId, comment));
					}
					else {
						CommentDTO commentDTO = commentDTOMap.get(comment.getParent().getId());
						commentDTO.addChild(setCommentChildWithEngagementAction(memberId, comment));
						commentDTOMap.put(comment.getParent().getId(), commentDTO);
					}
				}
				List<CommentDTO> commentDTOs = new ArrayList<>(commentDTOMap.values());
				postDTO.setComments(commentDTOs);
			}

	        List<Attachment> attachments = attachmentRepo.findByPostIdOrderByUploadedAtAsc(post.getId());
	        postDTO.setAttachments(attachments);
	        List<PostHashtag> hashtags = postHashtagRepo.findByPostId(post.getId());
	        postDTO.setHashtags(hashtags);
	        
	        return JSONFactory.getJSONStringFromObject(postDTO);
		}
		
		return null;
	}

	// 특정 게시글의 댓글 리스트 조회
	public String getCommentsByPost(Long postId, Long memberId) throws JsonProcessingException {

		Post post = postRepo.findById(postId).orElse(null);
		if (post != null) {
			List<Comment> comments = post.getComments();
			List<CommentDTO> commentDTOs = new ArrayList<>();
			if (comments.size() > 0) {
				Map<Long, CommentDTO> commentDTOMap = new HashMap<>();
				for (Comment comment : comments) {
					if (comment.getParent() == null) {
						commentDTOMap.put(comment.getId(), setCommentWithEngagementAction(memberId, comment));
					}
					else {
						CommentDTO commentDTO = commentDTOMap.get(comment.getParent().getId());
						commentDTO.addChild(setCommentChildWithEngagementAction(memberId, comment));
						commentDTOMap.put(comment.getParent().getId(), commentDTO);
					}
				}
				commentDTOs = new ArrayList<>(commentDTOMap.values());
			}

			return JSONFactory.getJSONStringFromObject(commentDTOs);
		}

		return null;
	}

	// 공감, 스크랩 추가 | 삭제
	public String changeEngagementAction(Long memberId, Long refId, EngagementAction.ActionType action) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<>();

		EngagementAction engagementAction = engagementActionRepo.findByMemberIdAndRefIdAndAction(memberId, refId, action);
		if (engagementAction != null) {
			engagementActionRepo.delete(engagementAction);
			response.put("status", "removed");
		}
		else {
			Member member = memberRepo.findById(memberId).orElse(null);
			if (member != null) {
				engagementAction = new EngagementAction(member, refId, action);
			}
			engagementActionRepo.save(engagementAction);
			response.put("status", "added");
		}

		return JSONFactory.getJSONStringFromMap(response);
	}

	// 특정 게시판의 게시판 정보 조회
	public String getBoard(Long boardId) throws JsonProcessingException {

		Board board = boardRepo.findById(boardId).orElse(null);

		if (board != null) {
			BoardSimpleDTO boardDTO = new BoardSimpleDTO(board);

			return JSONFactory.getJSONStringFromObject(boardDTO);
		}

		return null;
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

	private PostDetailDTO setPostDetailWithEngagementAction(Long memberId, Post post) {
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

		return new PostDetailDTO(post, isLiked, isScraped);
	}

	private CommentDTO setCommentWithEngagementAction(Long memberId, Comment comment) {
		boolean isLiked = false;

		EngagementAction engagementAction = engagementActionRepo.findByMemberIdAndRefIdAndAction(memberId, comment.getId(), EngagementAction.ActionType.clike);
		if (engagementAction != null && engagementAction.getAction() == EngagementAction.ActionType.clike) {
			isLiked = true;
		}

		return new CommentDTO(comment, isLiked);
	}

	private CommentChildDTO setCommentChildWithEngagementAction(Long memberId, Comment comment) {
		boolean isLiked = false;

		EngagementAction engagementAction = engagementActionRepo.findByMemberIdAndRefIdAndAction(memberId, comment.getId(), EngagementAction.ActionType.clike);
		if (engagementAction != null && engagementAction.getAction() == EngagementAction.ActionType.clike) {
			isLiked = true;
		}

		return new CommentChildDTO(comment, isLiked);
	}
	
}
