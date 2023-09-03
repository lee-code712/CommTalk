package com.commtalk.dto;

import com.commtalk.model.PostHashtag;

import lombok.Data;

@Data
public class PostHashtagSimpleDTO {

	private Long hashtagId;
	private String hashtag;
	
	public PostHashtagSimpleDTO(PostHashtag postHashtag) {
		this.hashtagId = postHashtag.getId();
		this.hashtag = postHashtag.getHashtag();
	}
	
}
