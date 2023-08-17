package com.commtalk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long member_id;
	private String username;
	private String email;
	private String phone;
	private String nickname;
	private Date registered_at;
	
	public Long getMember_id() {
		return member_id;
	}
	
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Date getRegistered_at() {
		return registered_at;
	}
	
	public void setRegistered_at(Date registered_at) {
		this.registered_at = registered_at;
	}

}
