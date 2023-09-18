package com.commtalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commtalk.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}
