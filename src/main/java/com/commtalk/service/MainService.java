package com.commtalk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.commtalk.dto.PinnedBoardDTO;
import com.commtalk.dto.PostPreviewDTO;
import com.commtalk.model.Board;
import com.commtalk.model.Member;
import com.commtalk.model.PinnedBoard;
import com.commtalk.model.Post;
import com.commtalk.repository.BoardRepository;
import com.commtalk.repository.MemberRepository;
import com.commtalk.repository.PinnedBoardRepository;
import com.commtalk.repository.PostRepository;
import com.commtalk.util.JSONFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class MainService {

	@Resource
	private PinnedBoardRepository pinnedBoardRepo;
	
	@Resource
	private MemberRepository memberRepo;
	
	@Resource
	private BoardRepository boardRepo;
	
	@Resource
	private PostRepository postRepo;
	
	// 게시판 핀 고정
	public void pinnedBoards(Long memberId, List<Long> boardIds) {
		
		// 기존에 있던 핀 게시판 정보 삭제
		List<PinnedBoard> pinnedBoards = pinnedBoardRepo.findByMemberId(memberId);
		if (pinnedBoards != null) {
			List<Long> pinnedBoardIds = pinnedBoards.stream()
					.map(pinnedBoard -> pinnedBoard.getId())
					.collect(Collectors.toList());
			
			pinnedBoardRepo.deleteAllById(pinnedBoardIds);
		}
		
		// 게시판 핀 고정
		Member member = memberRepo.findById(memberId).orElse(null);
		if (member != null) {
			for (Long boardId : boardIds) {
				Board board = boardRepo.findById(boardId).orElse(null);
				if (board != null) {
					PinnedBoard pinnedBoard = new PinnedBoard(member, board);
					pinnedBoardRepo.save(pinnedBoard);
				}
			}
		}
	}
	
	// 핀 고정 게시판 조회
	public String getPinnedBoardsWithPosts(Long memberId) throws JsonProcessingException {
		
		List<PinnedBoardDTO> pinnedBoardDTOs = new ArrayList<>();
		Pageable pageable = (Pageable) PageRequest.of(0, 4);
		
		if (memberId != null) {
			List<PinnedBoard> pinnedBoards = pinnedBoardRepo.findByMemberId(memberId);
			for (PinnedBoard pinnedBoard : pinnedBoards) {
				Long boardId = pinnedBoard.getBoard().getId();
				List<Post> posts = postRepo.findTop4ByBoardAndViewsWithCommentsAndBoard(boardId, pageable);
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
				List<Post> posts = postRepo.findTop4ByBoardAndViewsWithCommentsAndBoard(boardId, pageable);
				PinnedBoardDTO pinnedBoardDTO = new PinnedBoardDTO(board, posts);
				pinnedBoardDTOs.add(pinnedBoardDTO);
			}
		}

		return JSONFactory.getJSONStringFromList(pinnedBoardDTOs);
	}
	
	// 전체 게시판에서 조회수가 높은 게시글 4개 조회
	public String getPopularPostsByViews() throws JsonProcessingException {

		Pageable pageable = (Pageable) PageRequest.of(0, 4);
		List<Post> posts = postRepo.findTop4ByViewsWithCommentsAndBoard(pageable);
		List<PostPreviewDTO> postDTOs = posts.stream()
				.map(post -> new PostPreviewDTO(post))
				.collect(Collectors.toList());

		return JSONFactory.getJSONStringFromList(postDTOs);
	}

}
