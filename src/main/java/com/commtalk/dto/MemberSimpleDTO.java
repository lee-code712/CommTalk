package com.commtalk.dto;

import com.commtalk.model.Member;

import lombok.Data;

@Data
public class MemberSimpleDTO {
	
	private Long memberId;
	private String nickname;
	private String username;
	
	public MemberSimpleDTO(Member member) {
		this.memberId = member.getId();
		this.nickname = member.getNickname();
		this.username = member.getUsername();
	}

}
