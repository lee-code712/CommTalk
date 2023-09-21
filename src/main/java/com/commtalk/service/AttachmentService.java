package com.commtalk.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.commtalk.dto.AttachmentSimpleDTO;
import com.commtalk.model.Attachment;
import com.commtalk.repository.AttachmentRepository;
import com.commtalk.util.CommonFileUtils;
import com.commtalk.util.JSONFactory;

@Service
public class AttachmentService {

	@Resource
	private AttachmentRepository attachRepo;
	
	@Resource
	private CommonFileUtils fileUtils;
	
	public String loadPostImages(Long postId) throws IOException {
		List<Attachment> attachements = attachRepo.findByPostIdOrderByUploadedAtAsc(postId);
		List<AttachmentSimpleDTO> attachmentDTOs = attachements.stream()
				.map(attachement -> new AttachmentSimpleDTO(attachement))
                .collect(Collectors.toList());

		return JSONFactory.getJSONStringFromList(fileUtils.loadFiles(attachmentDTOs));
	}
}
