package com.commtalk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.model.Member;
import com.commtalk.repository.MemberRepository;

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
		
		Member findMember = memberRepository.findByUsername("test");
		
		assertNotNull(findMember);
	    assertEquals("test", findMember.getUsername());
	    assertEquals("test@example.com", findMember.getEmail());
	    assertEquals("010-1111-1111", findMember.getPhone());
	    assertEquals("talktalk", findMember.getNickname());
	}
}
