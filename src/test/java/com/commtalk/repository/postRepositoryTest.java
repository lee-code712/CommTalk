package com.commtalk.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.commtalk.CommtalkApplication;
import com.commtalk.model.Board;
import com.commtalk.model.Member;
import com.commtalk.model.Post;

@SpringBootTest(classes=CommtalkApplication.class)
public class postRepositoryTest {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private PostRepository postRepository;

	@Test
	@Transactional
	public void testSave() {
		Member author = memberRepository.findById(10L).orElse(null);
		assertNotNull(author);
		Board board = boardRepository.findById(4L).orElse(null);
		assertNotNull(board);
		
		if (author != null && board != null) {
			Post post = new Post();
			post.setTitle("게시글5");
			post.setContent("이것은 게시글입니다. 내용이 들어가는 부분입니다.");
			post.setAuthor(author);
			post.setBoard(board);
			post.setIsAnonymous(true);
			post.setIsCommentable(true);
			post.setLikes(0L);
			post.setViews(3L);
			
			postRepository.save(post);
			
			Post findPost = postRepository.findById(post.getId()).orElse(null);
			assertNotNull(findPost);
		}
	}
	
	@Test
	@Transactional
	public void testSelectTop4ByViewsWithCommentsAndBoard() {
		Pageable pageable = (Pageable) PageRequest.of(0, 4);
		List<Post> post = postRepository.findByViewsWithCommentsAndBoard(pageable);
		assertNotNull(post);
	}
	
	@Test
	@Transactional
	public void testSelectByKeyword() {
		Pageable pageable = (Pageable) PageRequest.of(0, 10);
		Page<Post> posts = postRepository.findByTitleOrContent(null, pageable);
		assertNotNull(posts);
	}
	
	@Test
	@Transactional
	public void testSelectByBoard() {
		Pageable pageable = (Pageable) PageRequest.of(0, 10);
		Page<Post> posts = postRepository.findByBoardOrderByCreatedAt(1L, pageable);
		assertNotNull(posts);
	}
	
	@Test
	@Transactional
	public void testSelectById() {
		Post post = postRepository.findById(1L).orElse(null);
		assertNotNull(post);
	}

}
