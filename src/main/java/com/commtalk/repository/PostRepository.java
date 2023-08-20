package com.commtalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commtalk.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findTop2ByOrderByViewsDesc();

}
