package com.commtalk.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.commtalk.model.EngagementAction;
import com.commtalk.model.EngagementAction.ActionType;
import com.commtalk.model.Member;
import com.commtalk.model.Post;

import java.util.List;

@SpringBootTest(classes=CommtalkApplication.class)
public class engagementActionRepositoryTest {
	
	@Autowired
	private EngagementActionRepository engagementActionRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private PostRepository postRepo;
	
	@Test
	public void testSave() {
		Member member = memberRepo.findById(10L).orElse(null);
		assertNotNull(member);
		Post post = postRepo.findById(1L).orElse(null);
		assertNotNull(post);
		
		if (member != null && post != null) {
			EngagementAction engagementAction = new EngagementAction();
			engagementAction.setAction(ActionType.like);
			engagementAction.setMember(member);
			engagementAction.setPost(post);
			
			engagementActionRepo.save(engagementAction);
			
			EngagementAction findEngagementAction = engagementActionRepo.findById(engagementAction.getId()).orElse(null);
			assertNotNull(findEngagementAction);
		}
		
	}

	@Test
	public void findByMemberIdAndPostId() {
		List<EngagementAction> engagementAction = engagementActionRepo.findByMemberIdAndPostId(10L, 1L);
		assertNotNull(engagementAction);
	}

}
