package com.commtalk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment {

	@Column(name = "comment_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
	private Member writer;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
	private Comment parent;
	
	@Column(name = "is_anonymous")
	private Boolean isAnonymous;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	private Long likes;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Column(name = "deleted_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;
	
	@PrePersist
    protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
		isDeleted = false;
		likes = 0L;
    }
	
	@PreUpdate
    protected void onUpdate() {
		updatedAt = new Date();
    }
	
}
