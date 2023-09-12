package com.commtalk.security;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.commtalk.model.Account;
import com.commtalk.model.Member;
import com.commtalk.repository.AccountRepository;
import com.commtalk.repository.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Resource
	private AccountRepository accountRepo;
	
	@Resource
	private MemberRepository memberRepo;
	
    @Override
    public CustomUserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {

    	Account account = accountRepo.findById(nickname).orElse(null);

    	if (account != null && account.getNickname().equals(nickname)) {
    		Member member = memberRepo.findByNickname(nickname);
			CustomUserDetails userDetails = new CustomUserDetails(
					account.getNickname(), 
					account.getPassword(), 
					member.getId(), // memberId
					Arrays.asList("USER") // roles
			);
			return userDetails;
        } 
    	else {
            throw new UsernameNotFoundException("User not found with nickname: " + nickname);
        }
    }
}
