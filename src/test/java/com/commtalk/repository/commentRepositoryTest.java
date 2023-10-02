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
//	@Transactional
	public void testSave() {

		Member writer = memberRepository.findById(10L).orElse(null);
		assertNotNull(writer);
		Post post = postRepository.findById(1L).orElse(null);
		assertNotNull(post);
		Comment parentComment = commentRepository.findById(1L).orElse(null);
		assertNotNull(parentComment);

		if (writer != null && post != null && parentComment != null) {
			Comment comment = new Comment();
			comment.setContent("이것은 대댓글22~");
			comment.setPost(post);
			comment.setWriter(writer);
			comment.setIsAnonymous(true);
			comment.setParent(parentComment);

			commentRepository.save(comment);

			Comment findComment = commentRepository.findById(comment.getId()).orElse(null);
			assertNotNull(findComment);
		}

	}
}
