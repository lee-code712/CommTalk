package com.commtalk.dto;

import com.commtalk.model.Attachment;

import lombok.Data;

@Data
public class AttachmentSimpleDTO {

	private Long attachmentId;
	private String fileName;
	
	public AttachmentSimpleDTO() {
	}
	
	public AttachmentSimpleDTO(Attachment attachment) {
		this.attachmentId = attachment.getId();
		this.fileName = attachment.getFileName();
	}
}
