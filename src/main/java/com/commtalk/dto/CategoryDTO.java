package com.commtalk.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.commtalk.model.Category;

import lombok.Data;

@Data
public class CategoryDTO {
	
	private Long categoryId;
	private String categoryName;
	private List<BoardSimpleDTO> boards;
	
	public CategoryDTO(Category category) {
		this.categoryId = category.getId();
		this.categoryName = category.getName();
		this.boards = category.getBoards().stream()
				.map((board) -> new BoardSimpleDTO(board))
                .collect(Collectors.toList());
	}

}
