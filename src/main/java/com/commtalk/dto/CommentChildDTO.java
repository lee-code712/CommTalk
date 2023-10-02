package com.commtalk.dto;

import com.commtalk.model.Comment;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommentChildDTO {
    private Long commentId;
    private String content;
    private MemberSimpleDTO writer;
    private Long parentId;
    private Long likes;
    private String createdAt;
    private String updatedAt;
    private boolean isLiked;

    public CommentChildDTO(Comment comment, boolean isLiked) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 날짜 포맷 지정

        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.writer = new MemberSimpleDTO(comment.getWriter());
        if (comment.getParent() != null) {
            this.parentId = comment.getParent().getId();
        }
        if (comment.getIsAnonymous()) {
            this.writer.setNickname("익명"); // 익명여부를 확인하여 닉네임을 익명으로 변경
        }
        this.likes = comment.getLikes();
        this.createdAt = sdf.format(comment.getCreatedAt());
        this.updatedAt = sdf.format(comment.getUpdatedAt());
        this.isLiked = isLiked;
    }

}
