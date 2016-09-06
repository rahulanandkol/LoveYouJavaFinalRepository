package com.loveyoujava.model;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class LoveYouJavaUploadModel {
	private MultipartFile multipartFile;
	private String fileTitle;
	private String editTitle;
	private String editTitleContent;
	
	
	public String getEditTitleContent() {
		return editTitleContent;
	}
	public void setEditTitleContent(String editTitleContent) {
		this.editTitleContent = editTitleContent;
	}
	public String getEditTitle() {
		return editTitle;
	}
	public void setEditTitle(String editTitle) {
		this.editTitle = editTitle;
	}
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
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
