package com.raistudies.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadForm {
	
	private String name = null;
	private CommonsMultipartFile file = null;
	private String uploadedData;
	private String filePath;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
		this.name = file.getOriginalFilename();
	}
	public String getUploadedData() {
		return uploadedData;
	}
	public void setUploadedData(String uploadedData) {
		this.uploadedData = uploadedData;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
