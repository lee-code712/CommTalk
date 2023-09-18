package com.commtalk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "post_hashtags")
public class PostHashtag {
	
	@Column(name = "hashtag_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "hashtag", nullable = false)
	private String hashtag;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
	private Post post;

}
