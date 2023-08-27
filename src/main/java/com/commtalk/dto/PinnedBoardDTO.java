package com.commtalk.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.commtalk.model.Board;
import com.commtalk.model.Post;

import lombok.Data;

@Data
public class PinnedBoardDTO {

	private BoardSimpleDTO board;
	private List<PostPreviewDTO2> posts;
	
	public PinnedBoardDTO(Board board, List<Post> posts) {
		this.board = new BoardSimpleDTO(board);
		this.posts = posts.stream()
				.map((post) -> new PostPreviewDTO2(post))
                .collect(Collectors.toList());
	}
	
}
