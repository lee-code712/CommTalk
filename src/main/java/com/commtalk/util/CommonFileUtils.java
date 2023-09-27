package com.commtalk.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.commtalk.dto.AttachmentSimpleDTO;
import com.commtalk.model.Attachment;

@Component
public class CommonFileUtils {

	@Value("${servlet.multipart.location}")
	private String fileDirPath;

	public List<Attachment> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
		List<Attachment> attachments = new ArrayList<>();

		// 디렉토리 준비
		File file = new File(fileDirPath);
		if (file.exists() == false) {
			file.mkdirs();
		}
		
		// 파일 저장
		int fileIndex = 0;
		for (MultipartFile multipartFile : multipartFiles) {
			if (multipartFile.isEmpty() == false) {
				String fileName = multipartFile.getOriginalFilename();
				String ext = fileName.substring(fileName.lastIndexOf("."));

				int i = 1; // 동일한 파일이 있을경우 붙을 변수
				int idx = fileName.lastIndexOf("."); // 확장자명 *번째
				fileName = fileName.substring(0, idx);

				file = new File(createPath(fileName, ext, i));

				while (file.isFile()) { // 디렉토리에 파일이 있으면 true 없으면 false
					i++;
				}
				file = new File(createPath(fileName, ext, i));
				
				Attachment attachment = new Attachment(fileName);
				attachments.add(attachment);
			}
			multipartFile.transferTo(file);
			fileIndex = fileIndex + 1;
		}
		return attachments;
	}
	
	public void deleteFiles(List<AttachmentSimpleDTO> attachments) {
		for (AttachmentSimpleDTO attachment : attachments) {
			File file = new File(createPath(attachment.getFileName()));
			if (file.exists()) {
				file.delete();
			}
		}
	}
	
	public List<String> loadFiles(List<AttachmentSimpleDTO> attachments) throws IOException {
		List<String> images = new ArrayList<>();
		
		for (AttachmentSimpleDTO attachment : attachments) {
			try {
				String filePath = createPath(attachment.getFileName());
				byte[] bytes = Files.readAllBytes(Paths.get(filePath));
		        String base64Image = Base64.encodeBase64String(bytes);
		        
		        images.add(base64Image);
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
		
		return images;
	}

	private String createPath(String fileName, String ext, int i) {
		if (i >= 2) {
			return fileDirPath + fileName + "(" + i + ")" + ext;
		}
		return fileDirPath + fileName + ext;
	}
	
	public String createPath(String fileName) {
		return fileDirPath + fileName;
	}

}
