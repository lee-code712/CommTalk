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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "report_restrictions")
public class ReportRestriction {

	@Column(name = "restriction_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "restriction_desc", nullable = false)
	private String desc;
	
	@Column(name = "action_taken")
	@Enumerated(EnumType.STRING)
	private ActionType action;
	
	@OneToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne
    @JoinColumn(name = "admin_id")
	private Member admin;
	
	@Column(name = "actioned_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date actionedAt;
	
	public enum ActionType {
	    DELETE, WARN, LIMIT
	}
	
	@PrePersist
    protected void onCreate() {
		actionedAt = new Date();
    }
	
}
