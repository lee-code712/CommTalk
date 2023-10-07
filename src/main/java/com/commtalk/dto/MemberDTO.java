package com.commtalk.dto;

import com.commtalk.model.Member;
import lombok.Data;

@Data
public class MemberDTO {

    private String nickname;
    private String username;
    private String email;
    private String phone;

    public MemberDTO(Member member) {
        this.nickname = member.getNickname();
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.phone = member.getPhone();
    }

}
