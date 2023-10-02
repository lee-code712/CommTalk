package com.commtalk.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.commtalk.model.EngagementAction.ActionType;
import com.commtalk.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.commtalk.controller.exception.ErrorMsg;
import com.commtalk.controller.exception.ExceptionUtils;
import com.commtalk.service.PostService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Map;

@RestController
@RequestMapping(value="/api/post")
public class PostController {

	@Resource
	private PostService postSvc;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	/* 선택한 게시판의 게시글 리스트 조회 */
	@RequestMapping(value="/getPostsByBoard/{boardId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = String.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getPostsByBoard(HttpServletRequest request, @PathVariable Long boardId,
											 @PageableDefault(size = 10) Pageable pageable) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		String response = "[]";

		Long memberId = jwtTokenProvider.getMemberId(request);
		try {
			response = postSvc.getPostsByBoard(boardId, memberId, pageable);

			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}

	/* 선택한 게시판의 게시글 검색 */
	@RequestMapping(value="/getPosts/{boardId}/{keyword}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = String.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getSearchPosts(HttpServletRequest request, @PathVariable Long boardId, @PathVariable String keyword,
											@PageableDefault(size = 10) Pageable pageable) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		String response = "[]";

		Long memberId = jwtTokenProvider.getMemberId(request);
		try {
			response = postSvc.getPostsByBoardAndKeyword(keyword, boardId, memberId, pageable);

			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}

	/* 선택한 게시글 상세 조회 */
	@RequestMapping(value="/getPostDetail/{postId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = String.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getPostDetail(HttpServletRequest request, @PathVariable Long postId) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		String response = "[]";

		Long memberId = jwtTokenProvider.getMemberId(request);
		try {
			response = postSvc.getPostById(postId, memberId);

			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}

	/* 선택한 게시글 댓글 리스트 조회 */
	@RequestMapping(value="/getCommentsByPost/{postId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = String.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getCommentsByPost(HttpServletRequest request, @PathVariable Long postId) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		String response = "[]";

		Long memberId = jwtTokenProvider.getMemberId(request);
		try {
			response = postSvc.getCommentsByPost(postId, memberId);

			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}

	/* 공감, 스크랩 변경 */
	@RequestMapping(value="/changeEngagementAction", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = String.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> changeEngagementAction(@RequestBody Map<String, Object> command, HttpServletRequest request) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		String response = "[]";

		Long memberId = jwtTokenProvider.getMemberId(request);
		try {
			Long refId = new Long((Integer) command.get("refId"));
			ActionType action = ActionType.valueOf(command.get("actionType").toString());
			response = postSvc.changeEngagementAction(memberId, refId, action);

			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}

	/* 조회수 변경 */
	@RequestMapping(value="/updateViews", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = String.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> updateViews(@RequestBody Map<String, Object> command, HttpServletRequest request) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		String response = "[]";

		Long memberId = jwtTokenProvider.getMemberId(request);
		try {
			Long postId = new Long((Integer) command.get("postId"));
			response = postSvc.updateViews(postId, memberId);

			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}

	/* 댓글 생성 */
	@RequestMapping(value="/createComment", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = String.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> createComment(@RequestBody Map<String, Object> command, HttpServletRequest request) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		String response = "[]";

		Long memberId = jwtTokenProvider.getMemberId(request);
		try {
			response = postSvc.createComment(memberId, command);

			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}

	/* 선택한 게시판의 게시판 정보 조회 */
	@RequestMapping(value="/getBoard/{boardId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = String.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getBoard(@PathVariable Long boardId) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		String response = "[]";

		try {
			response = postSvc.getBoard(boardId);

			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}

}
