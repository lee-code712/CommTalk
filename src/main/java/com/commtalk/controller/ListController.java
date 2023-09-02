package com.commtalk.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commtalk.service.ListService;

@RestController
@RequestMapping(value="/api/list")
public class ListController {

	@Resource
	private ListService listSvc;
	
	/* 특정 게시판의 게시글 리스트 조회 */
	
	/* 특정 게시판의 게시글 검색 */
	
}
