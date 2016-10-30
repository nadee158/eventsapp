package com.janaka.projects.entitymanagement.domain.documentmanagement;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.janaka.projects.entitymanagement.domain.common.BaseDomain;
import com.janaka.projects.entitymanagement.enums.MediaDomainType;

@Entity
@Table(name = "media")
public class Media extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = 0;

	@Column(name = "is_active")
	private boolean isActive = true;

	@Column(name = "name")
	private String name = StringUtils.EMPTY;

	@Column(name = "description")
	private String description = StringUtils.EMPTY;

	@Column(name = "media_domain")
	@Enumerated
	private MediaDomainType mediaDomain;

	@Column(name = "original_file_name")
	private String originalFileName;

	@Column(name = "generated_file_name")
	private String generatedFileName;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "download_param")
	private String dowloadParam;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "media_format_id", referencedColumnName = "id")
	private MediaFormat mediaFormat;

	@JsonIgnore
	@OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
	private List<MediaVersion> mediaVersions;

	@Version
	@Column(name = "version_number")
	private long versionNumber = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MediaDomainType getMediaDomain() {
		return mediaDomain;
	}

	public void setMediaDomain(MediaDomainType mediaDomain) {
		this.mediaDomain = mediaDomain;
	}

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

	public MediaFormat getMediaFormat() {
		return mediaFormat;
	}

	public void setMediaFormat(MediaFormat mediaFormat) {
		this.mediaFormat = mediaFormat;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<MediaVersion> getMediaVersions() {
		return mediaVersions;
	}

	public void setMediaVersions(List<MediaVersion> mediaVersions) {
		this.mediaVersions = mediaVersions;
	}

	public String getDowloadParam() {
		return dowloadParam;
	}

	public void setDowloadParam(String dowloadParam) {
		this.dowloadParam = dowloadParam;
	}

}
