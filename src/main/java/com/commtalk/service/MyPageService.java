package com.commtalk.service;

import com.commtalk.repository.AccountRepository;
import com.commtalk.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyPageService {

    @Resource
    private AccountRepository accountRepo;
    @Resource
    private MemberRepository memberRepo;

    /* 회원 정보 조회 */

}
