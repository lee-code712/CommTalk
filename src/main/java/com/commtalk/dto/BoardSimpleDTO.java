package com.commtalk.dto;

import com.commtalk.model.Board;

import lombok.Data;

@Data
public class BoardSimpleDTO {
	
	private Long boardId;
	private String boardName;
	
	public BoardSimpleDTO(Board board) {
		this.boardId = board.getId();
		this.boardName = board.getName();
	}

}
