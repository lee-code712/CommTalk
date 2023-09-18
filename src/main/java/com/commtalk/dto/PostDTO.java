package com.commtalk.dto;

import java.text.SimpleDateFormat;

import com.commtalk.model.Attachment;
import com.commtalk.model.Post;

import lombok.Data;

@Data
public class PostDTO {

	private Long postId;
	private String createdAt;
	private String updatedAt;
	private String title;
	private String content;
	private Long views;
	private Long likes;
	private Long scraps;
	private MemberSimpleDTO author;
	private BoardSimpleDTO board;
	private int commentCnt;
//	private AttachmentSimpleDTO thumbnail;
	
	public PostDTO(Post post) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 날짜 포맷 지정
		
		this.postId = post.getId();
		this.createdAt = sdf.format(post.getCreatedAt());
		this.updatedAt = sdf.format(post.getUpdatedAt());
		this.title = post.getTitle();
		this.content = post.getContent();
		this.views = post.getViews();
		this.likes = post.getLikes();
		this.scraps = post.getScraps();
		this.author = new MemberSimpleDTO(post.getAuthor());
		if (post.getIsAnonymous()) {
			this.author.setNickname("익명"); // 익명여부를 확인하여 닉네임을 익명으로 변경
		}
		this.board = new BoardSimpleDTO(post.getBoard());
		this.commentCnt = post.getComments().size();
//		if (thumbnail != null) {
//			this.thumbnail = new AttachmentSimpleDTO(thumbnail);
//		}
	}
	
}
