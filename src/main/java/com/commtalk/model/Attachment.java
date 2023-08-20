package com.commtalk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "attachments")
public class Attachment {

	@Column(name = "attachment_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fileName", nullable = false)
	private String fileName;
	
	@ManyToOne
    @JoinColumn(name = "post_id")
	private Post post;
	
	@Column(name = "uploaded_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadedAt;
	
	@PrePersist
    protected void onCreate() {
		uploadedAt = new Date();
    }
	
}
