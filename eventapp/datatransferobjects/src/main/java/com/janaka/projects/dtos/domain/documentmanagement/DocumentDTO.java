package com.janaka.projects.dtos.domain.documentmanagement;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.janaka.projects.dtos.domain.common.BaseDTO;

public class DocumentDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long referenceKey;

	private String fileName;

	private MultipartFile file;

	private String description;

	private int mediaDomainType;

	private String downloadURL;

	private String originalFileName;

	private long fileSize;

	private String generatedFileName;

	public long getReferenceKey() {
		return referenceKey;
	}

	public void setReferenceKey(long referenceKey) {
		this.referenceKey = referenceKey;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMediaDomainType() {
		return mediaDomainType;
	}

	public void setMediaDomainType(int mediaDomainType) {
		this.mediaDomainType = mediaDomainType;
	}

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getGeneratedFileName() {
		return generatedFileName;
	}

	public void setGeneratedFileName(String generatedFileName) {
		this.generatedFileName = generatedFileName;
	}

	@Override
	public String toString() {
		return "DocumentDTO [referenceKey=" + referenceKey + ", fileName=" + fileName + ", file=" + file
				+ ", description=" + description + ", mediaDomainType=" + mediaDomainType + ", downloadURL="
				+ downloadURL + ", originalFileName=" + originalFileName + ", fileSize=" + fileSize
				+ ", generatedFileName=" + generatedFileName + "]";
	}

}
