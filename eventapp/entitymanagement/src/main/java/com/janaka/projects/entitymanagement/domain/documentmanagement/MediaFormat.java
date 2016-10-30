package com.janaka.projects.entitymanagement.domain.documentmanagement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.janaka.projects.entitymanagement.domain.common.BaseDomain;
import com.janaka.projects.entitymanagement.enums.MediaType;

@Entity
@Table(name = "media_format")
public class MediaFormat extends BaseDomain {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = 0;

	@Column(name = "mime_type")
	private String mimeType;

	@Column(name = "file_extension")
	private String fileExtension;

	@Column(name = "media_type")
	@Enumerated
	private MediaType mediaType;

	@Column(name = "file_size")
	private long fileSize;

	@Version
	@Column(name = "version_number")
	private long versionNumber = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

}
