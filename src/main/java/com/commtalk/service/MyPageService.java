package com.commtalk.service;

import com.commtalk.dto.MemberDTO;
import com.commtalk.model.Account;
import com.commtalk.model.Member;
import com.commtalk.repository.AccountRepository;
import com.commtalk.repository.MemberRepository;
import com.commtalk.security.JwtTokenProvider;
import com.commtalk.util.JSONFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyPageService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
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

    /* 회원 정보 변경 */
    public String updateMember(Long memberId, Map<String, Object> map) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();

        Member member = memberRepo.findById(memberId).orElse(null);
        if (member != null) {
            try {
                // 사용자 인증 시도
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(member.getNickname(), map.get("orgPassword"))
                );

                Account account = accountRepo.findById(member.getNickname()).orElse(null);
                if (account != null) {
                    // 계정 정보 변경
                    Account newAccount = new Account();
                    newAccount.setNickname(map.get("nickname").toString());
                    if (!account.getNickname().equals(map.get("nickname"))
                            && accountRepo.findById(map.get("nickname").toString()).orElse(null) != null) {
                        response.put("status", "error");
                        response.put("message", "새로운 닉네임이 중복됩니다.");
                        return JSONFactory.getJSONStringFromMap(response);
                    }
                    if (map.get("newPassword") != null) {
                        if (!map.get("newPassword").equals(map.get("passwordCheck"))) {
                            response.put("status", "error");
                            response.put("message", "새로운 패스워드와 패스워드 확인이 일치하지 않습니다.");
                            return JSONFactory.getJSONStringFromMap(response);
                        }
                        newAccount.setPassword(passwordEncoder.encode(map.get("newPassword").toString()));
                    }
                    else {
                        newAccount.setPassword(account.getPassword());
                    }
                    accountRepo.save(newAccount);

                    // 회원 정보 변경
                    member.setNickname(map.get("nickname").toString());
                    member.setUsername(map.get("username").toString());
                    member.setEmail(map.get("email").toString());
                    member.setPhone(map.get("phone").toString());
                    memberRepo.save(member);

                    // 닉네임이 달라진 경우 기존 계정 정보 삭제
                    if (!account.getNickname().equals(map.get("nickname"))) {
                        accountRepo.delete(account);
                    }

                    response.put("status", "success");
                }
            } catch (BadCredentialsException e) { // 자격 증명 실패
                response.put("status", "error");
                response.put("message", "기존 패스워드가 일치하지 않습니다.");
            }
        }
        return JSONFactory.getJSONStringFromMap(response);
    }

}
