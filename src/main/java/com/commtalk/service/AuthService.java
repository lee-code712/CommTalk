package com.commtalk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.commtalk.model.Account;
import com.commtalk.model.Board;
import com.commtalk.model.Member;
import com.commtalk.model.PinnedBoard;
import com.commtalk.repository.AccountRepository;
import com.commtalk.repository.BoardRepository;
import com.commtalk.repository.MemberRepository;
import com.commtalk.repository.PinnedBoardRepository;
import com.commtalk.security.JwtTokenProvider;
import com.commtalk.util.JSONFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class AuthService {

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
	@Resource
	private BoardRepository boardRepo;
	@Resource
	private PinnedBoardRepository pinnedBoardRepo;
	
	public String join(Map<String, Object> map) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<>();
		
		Account account = new Account(map);
		if (accountRepo.findById(account.getNickname()).orElse(null) != null) {
			response.put("status", "error");
			response.put("message", "닉네임이 중복됩니다.");
		}
		else {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			accountRepo.save(account);
			
			Member member = new Member(map);
			memberRepo.save(member);
			
			// 기본 게시판 핀 고정
			List<Long> boardIds = new ArrayList<>();
			boardIds.add(1L);
			boardIds.add(2L);
			boardIds.add(3L);
			boardIds.add(4L);
			
			if (member.getId() != null) {
				for (Long boardId : boardIds) {
					Board board = boardRepo.findById(boardId).orElse(null);
					if (board != null) {
						PinnedBoard pinnedBoard = new PinnedBoard(member, board);
						pinnedBoardRepo.save(pinnedBoard);
					}
				}
			}
			
			response.put("status", "success");
		}
		
		return JSONFactory.getJSONStringFromMap(response);
	}
	
	public String login(Map<String, Object> map) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<>();
		
		try {
			// 사용자 인증 시도
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(map.get("nickname"), map.get("password"))
	        );
	        
	        // JWT 토큰 생성
	        String jwtToken = jwtTokenProvider.generateToken(authentication);
	        response.put("status", "success");
	        response.put("token", jwtToken);
	        
		} catch (BadCredentialsException e) { // 자격 증명 실패
			response.put("status", "error");
			response.put("message", "닉네임 또는 패스워드가 일치하지 않습니다.");
		}
        
        return JSONFactory.getJSONStringFromMap(response);
	}

}
