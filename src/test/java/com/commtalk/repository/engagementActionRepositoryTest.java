package com.commtalk.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.commtalk.model.Comment;
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
	@Autowired
	private CommentRepository commentRepo;
	
	@Test
	public void testSave() {
		Member member = memberRepo.findById(10L).orElse(null);
		assertNotNull(member);
//		Post post = postRepo.findById(1L).orElse(null);
//		assertNotNull(post);
		Comment comment = commentRepo.findById(1L).orElse(null);
		assertNotNull(comment);

		if (member != null && comment != null) {
			EngagementAction engagementAction = new EngagementAction();
			engagementAction.setAction(ActionType.clike);
			engagementAction.setMember(member);
			engagementAction.setRefId(comment.getId());
			
			engagementActionRepo.save(engagementAction);
			
			EngagementAction findEngagementAction = engagementActionRepo.findById(engagementAction.getId()).orElse(null);
			assertNotNull(findEngagementAction);
		}
		
	}

	@Test
	public void findByMemberIdAndRefId() {
		List<EngagementAction> engagementAction = engagementActionRepo.findByMemberIdAndRefId(10L, 1L);
		assertNotNull(engagementAction);
	}

}
