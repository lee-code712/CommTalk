package com.commtalk.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.commtalk.model.Post;

@SpringBootTest(classes=CommtalkApplication.class)
public class postRepositoryTest {
	
	@Autowired
	private PostRepository postRepository;
	
	@Test
//	@Transactional
	public void testSelectTop2ByOrderByViewsDesc() {
		List<Post> post = postRepository.findTop2ByOrderByViewsDesc();
		assertNotNull(post);
	}

}
