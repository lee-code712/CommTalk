package com.commtalk.controller;

import com.commtalk.controller.exception.ErrorMsg;
import com.commtalk.controller.exception.ExceptionUtils;
import com.commtalk.model.EngagementAction;
import com.commtalk.security.JwtTokenProvider;
import com.commtalk.service.MyPageService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value="/api/myPage")
public class MyPageController {

    @Resource
    private MyPageService mySvc;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /* 회원 정보 조회 */
    @RequestMapping(value="/getMember", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
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
            response = mySvc.getMember(memberId);

            return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
        }
    }

    /* 회원 정보 변경 */
    @RequestMapping(value="/updateMember", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = String.class),
            @ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
    })
    public ResponseEntity<?> updateMember(@RequestBody Map<String, Object> command, HttpServletRequest request) {
        MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
        ErrorMsg errors = new ErrorMsg();
        String response = "[]";

        Long memberId = jwtTokenProvider.getMemberId(request);
        try {
            response = mySvc.updateMember(memberId, command);

            return new ResponseEntity<String>(response, header, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
        }
    }

}
