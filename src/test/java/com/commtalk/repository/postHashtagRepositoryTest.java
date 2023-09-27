package com.commtalk.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.commtalk.model.PostHashtag;
import com.commtalk.model.Post;

@SpringBootTest(classes=CommtalkApplication.class)
public class postHashtagRepositoryTest {
	
	@Autowired
	private PostHashtagRepository postHashtagRepository;
	@Autowired
	private PostRepository postRepository;

	@Test
	@Transactional
	public void testSave() {
		Post post = postRepository.findById(1L).orElse(null);
		assertNotNull(post);
		
		if (post != null) {
			PostHashtag postHashtag = new PostHashtag();
			postHashtag.setHashtag("해시태그2");
			postHashtag.setPost(post);
			
			postHashtagRepository.save(postHashtag);
			
			PostHashtag findPostHashtag = postHashtagRepository.findById(postHashtag.getId()).orElse(null);
			assertNotNull(findPostHashtag);
		}
	}
	
}
