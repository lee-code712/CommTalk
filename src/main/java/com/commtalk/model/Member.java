package com.commtalk.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "members")
public class Member {

	@Column(name = "member_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	private String phone;
	
	@Column(name = "nickname", unique = true)
	private String nickname;
	
	@Column(name = "registered_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registeredAt;
	
	@PrePersist
    protected void onCreate() {
        registeredAt = new Date();
    }
	
	public Member() {
	}
	
	public Member(Map<String, Object> map) {
		this.username = map.get("username").toString();
		this.email = map.get("email").toString();
		this.nickname = map.get("nickname").toString();
		
		if (map.get("phone") != null) {
			this.phone = map.get("phone").toString();
		}
	}
	
}
