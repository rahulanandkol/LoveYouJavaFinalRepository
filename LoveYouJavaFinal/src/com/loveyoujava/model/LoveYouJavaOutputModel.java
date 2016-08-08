package com.loveyoujava.model;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class LoveYouJavaOutputModel {
	private String fileId;
	private String filePath;
	private String fileTitle; 
	
	public String getFileId() { 
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	
	
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	        File convFile = new File( multipart.getOriginalFilename());
	        multipart.transferTo(convFile);
	        return convFile;
	}
	
}
