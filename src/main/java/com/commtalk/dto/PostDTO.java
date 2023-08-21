package com.commtalk.dto;

import com.commtalk.model.Post;

import lombok.Data;

@Data
public class PostDTO {

	private Long postId;
	
	private String title;
	
	private Long views;
	
	private Long likes;
	
	private Long scraps;
	
	private BoardDTO board;
	
	public PostDTO(Post post) {
		this.postId = post.getId();
		this.title = post.getTitle();
		this.views = post.getViews();
		this.likes = post.getLikes();
		this.scraps = post.getScraps();
		this.board = new BoardDTO(post.getBoard());
	}
}
