package com.commtalk.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.commtalk.dto.PinnedBoardDTO;
import com.commtalk.model.Board;
import com.commtalk.model.PinnedBoard;
import com.commtalk.model.Post;
import com.commtalk.repository.BoardRepository;
import com.commtalk.repository.PinnedBoardRepository;
import com.commtalk.repository.PostRepository;
import com.commtalk.util.JSONFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class MainService {

	@Resource
	private PinnedBoardRepository pinnedBoardRepo;
	
	@Resource
	private BoardRepository boardRepo;
	
	@Resource
	private PostRepository postRepo;
	
	// 핀 고정 게시판 조회
	public String getPinnedBoardsWithPosts(Long memberId) throws JsonProcessingException {
		
		List<PinnedBoardDTO> pinnedBoardDTOs = new ArrayList<>();
		Pageable pageable = (Pageable) PageRequest.of(0, 4);
		
		if (memberId != null) {
			List<PinnedBoard> pinnedBoards = pinnedBoardRepo.findByMemberId(memberId);
			for (PinnedBoard pinnedBoard : pinnedBoards) {
				Long boardId = pinnedBoard.getBoard().getId();
				List<Post> posts = postRepo.findTop4ByCreatedAtWithCommentsAndBoard(boardId, pageable);
				PinnedBoardDTO pinnedBoardDTO = new PinnedBoardDTO(pinnedBoard.getBoard(), posts);
				pinnedBoardDTOs.add(pinnedBoardDTO);
			}
		}
		else {
			// 로그인하지 않은 경우 기본 게시판 조회
			List<Long> boardIds = new ArrayList<>();
			boardIds.add(1L);
			boardIds.add(2L);
			boardIds.add(3L);
			boardIds.add(4L);
			
			List<Board> boards = boardRepo.findByIdIn(boardIds);
			for (Board board : boards) {
				Long boardId = board.getId();
				List<Post> posts = postRepo.findTop4ByCreatedAtWithCommentsAndBoard(boardId, pageable);
				PinnedBoardDTO pinnedBoardDTO = new PinnedBoardDTO(board, posts);
				pinnedBoardDTOs.add(pinnedBoardDTO);
			}
		}

		return JSONFactory.getJSONStringFromList(pinnedBoardDTOs);

	}
}
