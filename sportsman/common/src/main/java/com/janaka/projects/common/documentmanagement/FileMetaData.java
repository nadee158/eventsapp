package com.janaka.projects.common.documentmanagement;

public class FileMetaData {

	private String originalFileName;

	private String generatedFileName;

	private String filePath;

	private String mimeType;

	private String extension;

	private long fileSize;

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getGeneratedFileName() {
		return generatedFileName;
	}

	public void setGeneratedFileName(String generatedFileName) {
		this.generatedFileName = generatedFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

}
