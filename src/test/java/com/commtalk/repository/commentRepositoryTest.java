package com.commtalk.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.commtalk.model.Comment;
import com.commtalk.model.Member;
import com.commtalk.model.Post;

@SpringBootTest(classes=CommtalkApplication.class)
public class commentRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;
	
	@Test
	@Transactional
	public void testSave() {
		
		Member writer = memberRepository.findById(10L).orElse(null);
		assertNotNull(writer);
		Post post = postRepository.findById(1L).orElse(null);
		assertNotNull(post);
		
		if (writer != null && post != null) {
			Comment comment = new Comment();
			comment.setContent("이것은 댓글~");
			comment.setPost(post);
			comment.setWriter(writer);
			comment.setIsAnonymous(true);
			
			commentRepository.save(comment);
			
			Comment findComment = commentRepository.findById(comment.getId()).orElse(null);
			assertNotNull(findComment);
		}
		
	}
}
