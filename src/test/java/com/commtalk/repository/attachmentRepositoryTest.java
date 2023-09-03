package com.commtalk.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.commtalk.model.Attachment;
import com.commtalk.model.Post;

@SpringBootTest(classes=CommtalkApplication.class)
public class attachmentRepositoryTest {
	
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private PostRepository postRepository;

	@Test
	@Transactional
	public void testSave() {
		Post post = postRepository.findById(1L).orElse(null);
		assertNotNull(post);
		
		if (post != null) {
			Attachment attachment = new Attachment();
			attachment.setFileName("test_image(1).jpg");
			attachment.setPost(post);
			
			attachmentRepository.save(attachment);
			
			Attachment findAttachment = attachmentRepository.findById(attachment.getId()).orElse(null);
			assertNotNull(findAttachment);
		}
	}
	
}
