package com.commtalk.service;

import com.commtalk.dto.MemberDTO;
import com.commtalk.model.Member;
import com.commtalk.repository.AccountRepository;
import com.commtalk.repository.MemberRepository;
import com.commtalk.util.JSONFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyPageService {

    @Resource
    private AccountRepository accountRepo;
    @Resource
    private MemberRepository memberRepo;

    /* 회원 정보 조회 */
    public String getMember(Long memberId) throws JsonProcessingException {
        Member member = memberRepo.findById(memberId).orElse(null);
        if (member != null) {
            MemberDTO memberDTO = new MemberDTO(member);

            return JSONFactory.getJSONStringFromObject(memberDTO);
        }

        return null;
    }

}
