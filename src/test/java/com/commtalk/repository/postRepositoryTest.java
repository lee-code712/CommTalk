package com.commtalk.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.commtalk.model.Board;
import com.commtalk.model.Member;
import com.commtalk.model.Post;
import com.commtalk.utils.JSONFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(classes=CommtalkApplication.class)
public class postRepositoryTest {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private PostRepository postRepository;

	@Test
//	@Transactional
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
//	@Transactional
	public void testSelectTop4ByViewsWithCommentsAndBoard() {
		List<Post> post = postRepository.findTop4ByViewsWithCommentsAndBoard();
		assertNotNull(post);
	}
	
	@Test
	@Transactional
	public void testSelectByKeyword() {
		List<Post> posts = postRepository.findByTitleOrContent("");
		System.out.println(posts.size());
//		posts.forEach(post -> System.out.println(post.toString()));
		assertNotNull(posts);
	}

}
