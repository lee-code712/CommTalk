package com.commtalk.dto;

import com.commtalk.model.Member;

import lombok.Data;

@Data
public class MemberSimpleDTO {
	
	private Long memberId;
	private String nickname;
	
	public MemberSimpleDTO(Member member) {
		this.memberId = member.getId();
		this.nickname = member.getNickname();
	}

}
