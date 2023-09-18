package com.commtalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commtalk.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

	List<Attachment> findByPostIdOrderByUploadedAtAsc(Long postId);
	
	Attachment findTop1ByPostIdOrderByUploadedAtAsc(Long postId);
	
}
