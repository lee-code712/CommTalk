package com.commtalk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.commtalk.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.comments c JOIN FETCH p.board b ORDER BY p.views DESC")
	List<Post> findByViewsWithCommentsAndBoard(Pageable pageable);

	@Query("SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.comments c JOIN FETCH p.board b WHERE b.id = :boardId ORDER BY p.views DESC")
	List<Post> findByBoardAndViewsWithCommentsAndBoard(@Param("boardId") Long boardId, Pageable pageable);

	@Query("SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.comments c JOIN FETCH p.board b WHERE b.id = :boardId ORDER BY p.createdAt DESC")
	List<Post> findByCreatedAtWithCommentsAndBoard(@Param("boardId") Long boardId, Pageable pageable);

	@Query(value = "SELECT DISTINCT p FROM Post p " +
			"JOIN FETCH p.board b " +
			"JOIN FETCH p.author a " +
			"LEFT JOIN FETCH p.comments c " +
			"WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword% ORDER BY p.createdAt DESC",
			countQuery = "SELECT COUNT(p) FROM Post p " +
					"JOIN p.board b " +
					"WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword% ORDER BY p.createdAt DESC")
	Page<Post> findByTitleOrContent(@Param("keyword") String keyword, Pageable pageable);

	@Query(value = "SELECT DISTINCT p FROM Post p " +
			"JOIN FETCH p.board b " +
			"JOIN FETCH p.author a " +
			"LEFT JOIN FETCH p.comments c " +
			"WHERE b.id = :boardId AND (p.title LIKE %:keyword% OR p.content LIKE %:keyword%) ORDER BY p.createdAt DESC",
			countQuery = "SELECT COUNT(p) FROM Post p " +
					"JOIN p.board b " +
					"WHERE b.id = :boardId AND (p.title LIKE %:keyword% OR p.content LIKE %:keyword%) ORDER BY p.createdAt DESC")
	Page<Post> findByBoardIdAndTitleOrContent(@Param("keyword") String keyword, @Param("boardId") Long boardId, Pageable pageable);

	@Query(value = "SELECT DISTINCT p FROM Post p " +
			"JOIN FETCH p.board b " +
			"JOIN FETCH p.author a " +
			"LEFT JOIN FETCH p.comments c " +
			"WHERE b.id = :boardId ORDER BY p.createdAt DESC",
			countQuery = "SELECT COUNT(p) FROM Post p " +
					"JOIN p.board b " +
					"WHERE b.id = :boardId ORDER BY p.createdAt DESC")
	Page<Post> findByBoardOrderByCreatedAt(@Param("boardId") Long boardId, Pageable pageable);


	@Query("SELECT DISTINCT p " +
			"FROM Post p " +
			"JOIN FETCH p.board b " +
			"JOIN FETCH p.author a " +
			"LEFT JOIN FETCH p.comments c " +
			"WHERE p.id = :postId ORDER BY c.createdAt")
	Optional<Post> findById(@Param("postId") Long postId);

}
