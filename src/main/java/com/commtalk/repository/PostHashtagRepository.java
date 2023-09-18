package com.commtalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commtalk.model.PostHashtag;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {

	List<PostHashtag> findByPostId(Long postId);
	
}
