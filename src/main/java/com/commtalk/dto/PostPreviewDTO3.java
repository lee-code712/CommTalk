package com.commtalk.dto;

import com.commtalk.model.Attachment;
import com.commtalk.model.Post;

import lombok.Data;

@Data
public class PostPreviewDTO3 {

	private BoardSimpleDTO board;
	private Long postId;
	private String title;
	private AttachmentSimpleDTO thumbnail;
	
	public PostPreviewDTO3(Post post, Attachment attachment) {
		this.board = new BoardSimpleDTO(post.getBoard());
		this.postId = post.getId();
		this.title = post.getTitle();
		this.thumbnail = new AttachmentSimpleDTO(attachment);
	}
	
}
