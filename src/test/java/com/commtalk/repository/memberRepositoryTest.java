package com.commtalk.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.commtalk.model.Member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

@SpringBootTest(classes=CommtalkApplication.class)
public class memberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	@Transactional
	public void testSaveAndSelectMember() {
		Member member = new Member();
		member.setUsername("test");
		member.setEmail("test@example.com");
		member.setPhone("010-1111-1111");
		member.setNickname("talktalk");
		memberRepository.save(member);
		
		Member findMember = memberRepository.findByNickname("talktalk");
		
		assertNotNull(findMember);
	    assertEquals("test", findMember.getUsername());
	    assertEquals("test@example.com", findMember.getEmail());
	    assertEquals("010-1111-1111", findMember.getPhone());
	    assertEquals("talktalk", findMember.getNickname());
	}
	
	@Test
	@Transactional
	public void testSelectMemberById() {
		Member findMember = memberRepository.findById(10L).orElse(null);
		assertNotNull(findMember);
	}
}
