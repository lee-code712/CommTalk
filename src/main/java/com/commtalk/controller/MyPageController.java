package com.commtalk.controller;

import com.commtalk.service.MyPageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value="/api/myPage")
public class MyPageController {

    @Resource
    private MyPageService mySvc;

    /* 회원 정보 조회 */

}
