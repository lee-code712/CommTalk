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
@Table(name = "messages")
public class Message {
	
	@Column(name = "message_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@ManyToOne
    @JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne
    @JoinColumn(name = "comment_id")
	private Comment comment;
	
	@ManyToOne
    @JoinColumn(name = "sender_id")
	private Member sender;
	
	@ManyToOne
    @JoinColumn(name = "receiver_id")
	private Member receiver;
	
	@Column(name = "sent_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sentAt;
	
	@Column(name = "read_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date readAt;
	
	@PrePersist
    protected void onCreate() {
		sentAt = new Date();
    }

}
