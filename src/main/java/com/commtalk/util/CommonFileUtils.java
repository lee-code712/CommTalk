package com.commtalk.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.commtalk.model.Attachment;

public class CommonFileUtils {

	@Value("${file.dir}")
	private static String fileDirPath;

	public static List<Attachment> storeFile(List<MultipartFile> multipartFiles) throws IOException {
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
	
	public static void deleteFile(List<Attachment> attachments) {
		for (Attachment attachment : attachments) {
			File file = new File(fileDirPath + attachment.getFileName());
			if (file.exists()) {
				file.delete();
			}
		}
	}

	private static String createPath(String fileName, String ext, int i) {
		if (i >= 2) {
			return fileDirPath + fileName + "(" + i + ")" + ext;
		}
		return fileDirPath + fileName + ext;
	}

}
