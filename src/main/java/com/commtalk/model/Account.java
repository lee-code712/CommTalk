package com.commtalk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {

	@Id
	@Column(name = "nickname")
	private String nickname;
	
	@Column(nullable = false)
	private String password;
	
}
