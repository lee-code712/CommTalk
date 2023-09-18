package com.commtalk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "reports")
public class Report {
	
	@Column(name = "report_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "reason", nullable = false)
	private String reason;
	
	@Enumerated(EnumType.STRING)
	private StatusType status;
	
	@ManyToOne
    @JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne
    @JoinColumn(name = "reporter_id")
	private Member reporter;
	
	@Column(name = "reported_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reportedAt;

	public enum StatusType {
	    PENDING, PROCESSED, REJECTED
	}
	
	@PrePersist
    protected void onCreate() {
		reportedAt = new Date();
    }

}
