package com.commtalk.dto;

import com.commtalk.model.Board;

import lombok.Data;

@Data
public class BoardDTO {
	
	private Long boardId;
	
	private String boardName;
	
	public BoardDTO(Board board) {
		this.boardId = board.getId();
		this.boardName = board.getName();
	}

}
