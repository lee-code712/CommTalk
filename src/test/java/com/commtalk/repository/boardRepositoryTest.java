package com.commtalk.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.commtalk.model.Board;
import com.commtalk.model.Category;
import com.commtalk.model.Member;

@SpringBootTest(classes=CommtalkApplication.class)
public class boardRepositoryTest {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	@Transactional
	public void testSave() {
		Member owner = memberRepository.findById(10L).orElse(null);
		assertNotNull(owner);
		Category category = categoryRepository.findById(4L).orElse(null);
		assertNotNull(category);
		
		if (owner != null && category != null) {
			Board board = new Board();
			board.setName("게시판4");
			board.setDesc("게시판 소개");
			board.setOwner(owner);
			board.setCategory(category);
			
			boardRepository.save(board);
			
			Board findBoard = boardRepository.findById(board.getId()).orElse(null);
			assertNotNull(findBoard);
		}
	}

}
