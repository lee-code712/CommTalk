package com.commtalk.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.commtalk.controller.exception.ErrorMsg;
import com.commtalk.controller.exception.ExceptionUtils;
import com.commtalk.security.JwtTokenProvider;
import com.commtalk.service.MainService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/api/main")
public class MainController {
	
	@Resource
	private MainService mainSvc;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	/* 게시판 핀 고정 */
	@RequestMapping(value="/updatePinnedBoards", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = String.class),
		@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> updatePinnedBoards(@RequestBody Map<String, Object> command, HttpServletRequest request) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();	
		String response = "[]";

		Long memberId = jwtTokenProvider.getMemberId(request);
		try {
			List<Long> boardIds = ((List<Integer>) command.get("boards")).stream()
					.mapToLong(Integer::longValue)
					.boxed().collect(Collectors.toList());
			mainSvc.pinnedBoards(memberId, boardIds);
			response = mainSvc.getPinnedBoardsWithPosts(memberId);
			
			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}
	
	/* 핀 고정 게시판의 게시글 조회 */
	@RequestMapping(value="/getPinnedBoards", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = String.class),
		@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getPinnedBoards(HttpServletRequest request) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();	
		String response = "[]";

		Long memberId = jwtTokenProvider.getMemberId(request);
		try {
			response = mainSvc.getPinnedBoardsWithPosts(memberId);
			
			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}
	
	/* 메인 고정 게시판의 게시글 조회 */
	@RequestMapping(value="/getPosts/{boardName}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = String.class),
		@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getPosts(@PathVariable String boardName) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();	
		String response = "[]";

		try {
			response = mainSvc.getPostsByBoardName(boardName);
			
			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}
	
	/* 인기 게시글 조회 */
	@RequestMapping(value="/getPopularPosts", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = String.class),
		@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getPopularPosts() {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();	
		String response = "[]";

		try {
			response = mainSvc.getPopularPostsByViews();
			
			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}

	/* 회원 정보 조회 */
	@RequestMapping(value="/getMember", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = String.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getMember(HttpServletRequest request) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		String response = "[]";

		Long memberId = jwtTokenProvider.getMemberId(request);
		try {
			response = mainSvc.getMember(memberId);

			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}
	
}
