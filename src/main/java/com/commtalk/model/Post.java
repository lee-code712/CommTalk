package com.commtalk.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {

	@Column(name = "post_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
	private Board board;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
	private Member author;
	
	@Column(name = "is_anonymous")
	private Boolean isAnonymous;
	
	@Column(name = "is_commentable")
	private Boolean isCommentable;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	private Long views;
	
	private Long likes;
	
	private Long scraps;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Column(name = "deleted_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;
	
	/* 추가 */
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	@PrePersist
    protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
		isDeleted = false;
		views = 0L;
		likes = 0L;
		scraps = 0L;
    }
	
	@PreUpdate
    protected void onUpdate() {
		updatedAt = new Date();
    }
	
	public void addComments(Comment comment) {
		comments.add(comment);
		comment.setPost(this);
    }
	
}
