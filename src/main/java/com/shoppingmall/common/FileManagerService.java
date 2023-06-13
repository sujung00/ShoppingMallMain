package com.shoppingmall.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 실제 업로드 된 이미지가 저장될 경로(서버)
	//public static final String FILE_UPLOAD_PATH = "C:\\SUJUNG\\shoppingmall\\images/";
	public static final String FILE_UPLOAD_PATH = "/home/ec2-user/images/";
	
	// input:MultipartFile(이미지파일), loginId
	// output:image path(String)
	public String saveFile(String loginId, MultipartFile file) {
		String directoryName = loginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return "/images/" + directoryName + file.getOriginalFilename();
	}
	
	// input:imagePath(String)	output:X
	public void deleteFile(String imagePath) {
		// imagePath: /images/aaaa_165645231/sun.png
		// C:\\SUJUNG\\6_spring_project\\memo\\workspace\\images/aaaa_165645231/sun.png
		// 겹치는 images 경로를 제거 - imagePath 안에 있는 /images/ 구문 제거
		
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		
		// 이미지 제거
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("[이미지 삭제] 이미지 삭제 실패. imagePath:{}", imagePath);
				return;
			}
		}
		
		// 디렉토리(폴더) 삭제
		path = path.getParent();
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("[이미지 삭제] 디렉토리 삭제 실패. imagePath:{}", imagePath);
			}
		}
	}
}