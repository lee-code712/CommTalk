package com.commtalk.dto;

import com.commtalk.model.Post;

import lombok.Data;

@Data
public class PostPreviewDTO {

	private Long postId;
	private String title;
	private Long views;
	private Long likes;
//	private Long scraps;
	private BoardSimpleDTO board;
	private int commentCnt;
	
	public PostPreviewDTO(Post post) {
		this.postId = post.getId();
		this.title = post.getTitle();
		this.views = post.getViews();
		this.likes = post.getLikes();
//		this.scraps = post.getScraps();
		this.board = new BoardSimpleDTO(post.getBoard());
		this.commentCnt = post.getComments().size();
	}
	
}
