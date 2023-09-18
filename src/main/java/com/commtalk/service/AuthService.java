package com.commtalk.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.commtalk.model.Account;
import com.commtalk.model.Member;
import com.commtalk.repository.AccountRepository;
import com.commtalk.repository.MemberRepository;
import com.commtalk.security.JwtTokenProvider;
import com.commtalk.util.JSONFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class AuthService {
	
	@Resource
	private AccountRepository accountRepo;
	
	@Resource
	private MemberRepository memberRepo;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	public String join(Map<String, Object> map) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<>();
		
		Account account = new Account(map);
		if (accountRepo.findById(account.getNickname()) != null) {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			accountRepo.save(account);
			
			Member member = new Member(map);
			memberRepo.save(member);
			
			response.put("status", "success");
		}
		else {
			response.put("status", "error");
			response.put("message", "닉네임이 중복됩니다.");
		}
		
		return JSONFactory.getJSONStringFromMap(response);
	}
	
	public String login(Map<String, Object> map) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<>();
		
		// 사용자 인증 시도
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(map.get("nickname"), map.get("password"))
        );
        
		// JWT 토큰 생성
        String jwtToken = jwtTokenProvider.generateToken(authentication);
        response.put("token", jwtToken);
        
        return JSONFactory.getJSONStringFromMap(response);
	}

}
