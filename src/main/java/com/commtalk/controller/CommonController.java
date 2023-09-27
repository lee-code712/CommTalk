/* UI에서 공통으로 사용하는 기능을 제공하는 컨트롤러  */
package com.commtalk.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.commtalk.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.commtalk.controller.exception.ErrorMsg;
import com.commtalk.controller.exception.ExceptionUtils;
import com.commtalk.service.CommonService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/api/common")
public class CommonController {
	
	@Resource
	private CommonService commonSvc;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	/* 메뉴(카테고리별 게시판) 조회 */
	@RequestMapping(value="/getCategories", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = String.class),
		@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getCategories() {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();	
		String response = "[]";

		try {
			response = commonSvc.getCategoriesWithBoards();
			
			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}
	
	/* 게시글 검색 */
	@RequestMapping(value="/getPosts/{keyword}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = String.class),
		@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getSearchPosts(HttpServletRequest request, @PathVariable String keyword,
											@PageableDefault(size = 10) Pageable pageable) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();	
		String response = "[]";

		Long memberId = jwtTokenProvider.getMemberId(request);
		try {
			response = commonSvc.getPostsByKeyword(keyword, memberId, pageable);
			
			return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}

}
