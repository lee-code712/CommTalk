package com.commtalk.service;

import com.commtalk.model.Attachment;
import com.commtalk.model.Post;
import com.commtalk.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepo;

    public void saveAll(List<Attachment> attachments) {
        attachmentRepo.saveAll(attachments);
    }

}
