package com.commtalk.dto;

import com.commtalk.model.Post;

import lombok.Data;

@Data
public class PostPreviewDTO2 {

	private Long postId;
	private String title;
	private Long views;
	private Long likes;
	private Long scraps;
	private int commentCnt;
	
	public PostPreviewDTO2(Post post) {
		this.postId = post.getId();
		this.title = post.getTitle();
		this.views = post.getViews();
		this.likes = post.getLikes();
		this.scraps = post.getScraps();
		this.commentCnt = post.getComments().size();
	}
	
}
