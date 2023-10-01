package com.commtalk.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.commtalk.model.Comment;

import lombok.Data;

@Data
public class CommentDTO {

	private Long commentId;
	private String content;
	private MemberSimpleDTO writer;
	private Long likes;
	private String createdAt;
	private String updatedAt;
	private int childCnt;
	private List<CommentChildDTO> childs;
	private boolean isLiked;
	
	public CommentDTO(Comment comment, boolean isLiked) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 날짜 포맷 지정
		
		this.commentId = comment.getId();
		this.content = comment.getContent();
		this.writer = new MemberSimpleDTO(comment.getWriter());
		if (comment.getIsAnonymous()) {
			this.writer.setNickname("익명"); // 익명여부를 확인하여 닉네임을 익명으로 변경
		}
		this.likes = comment.getLikes();
		this.createdAt = sdf.format(comment.getCreatedAt());
		this.updatedAt = sdf.format(comment.getUpdatedAt());
		this.childCnt = 0;
		this.childs = new ArrayList<>();
		this.isLiked = isLiked;
	}

	public void addChild(CommentChildDTO childDTO) {
		this.childs.add(childDTO);
		this.childCnt += 1;
	}

}
