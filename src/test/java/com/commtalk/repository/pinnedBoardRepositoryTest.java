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
import com.commtalk.model.PinnedBoard;

@SpringBootTest(classes=CommtalkApplication.class)
public class pinnedBoardRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private PinnedBoardRepository pinnedBoardRepository;
	
	@Test
	@Transactional
	public void testSave() {
		Member member = memberRepository.findById(10L).orElse(null);
		assertNotNull(member);
		Board board = boardRepository.findById(4L).orElse(null);
		assertNotNull(board);
		
		if (member != null && board != null) {
			PinnedBoard pinnedBoard = new PinnedBoard();
			pinnedBoard.setBoard(board);
			pinnedBoard.setMember(member);
			
			pinnedBoardRepository.save(pinnedBoard);
			
			PinnedBoard findPinnedBoard = pinnedBoardRepository.findById(pinnedBoard.getId()).orElse(null);
			assertNotNull(findPinnedBoard);
		}
		
	}

	@Test
	@Transactional
	public void testSelectByMemberId() {
		List<PinnedBoard> pinnedBoards = pinnedBoardRepository.findByMemberId(10L);
		assertNotNull(pinnedBoards);
	}
	
}
