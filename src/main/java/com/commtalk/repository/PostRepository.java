package com.commtalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.commtalk.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query("SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.comments c JOIN FETCH p.board ORDER BY p.views DESC")
	List<Post> findTop4ByViewsWithCommentsAndBoard();

	@Query("SELECT DISTINCT p FROM Post p " +
	       "JOIN FETCH p.board b " +
	       "JOIN FETCH p.author a " +
	       "LEFT JOIN FETCH p.comments c " +
	       "WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
	List<Post> findByTitleOrContent(@Param("keyword") String keyword);
	
}
