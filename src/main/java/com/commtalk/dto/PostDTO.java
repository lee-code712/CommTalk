package com.commtalk.dto;

import com.commtalk.model.Post;

import lombok.Data;

@Data
public class PostDTO {

	private Long postId;
	private String title;
	private Long views;
	private Long likes;
	private MemberSimpleDTO author;
	private BoardSimpleDTO board;
	private int commentCnt;
	
	public PostDTO(Post post) {
		this.postId = post.getId();
		this.title = post.getTitle();
		this.views = post.getViews();
		this.likes = post.getLikes();
		this.author = new MemberSimpleDTO(post.getAuthor());
		if (post.getIsAnonymous()) {
			this.author.setNickname("익명"); // 익명여부를 확인하여 닉네임을 익명으로 변경
		}
		this.board = new BoardSimpleDTO(post.getBoard());
		this.commentCnt = post.getComments().size();
	}
	
}
