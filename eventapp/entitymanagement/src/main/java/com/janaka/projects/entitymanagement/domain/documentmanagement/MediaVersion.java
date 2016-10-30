package com.janaka.projects.entitymanagement.domain.documentmanagement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.janaka.projects.entitymanagement.domain.common.BaseDomain;

@Entity
@Table(name = "media_version")
public class MediaVersion extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = 0;

	@Column(name = "media_version")
	private int mediaVersion;

	@ManyToOne
	@JoinColumn(name = "media_id")
	private Media media;

	@OneToOne
	@JoinColumn(name = "media_audit_id", nullable = true)
	private MediaAudit mediaAudit;

	@Version
	@Column(name = "version_number")
	private long versionNumber = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public int getMediaVersion() {
		return mediaVersion;
	}

	public void setMediaVersion(int mediaVersion) {
		this.mediaVersion = mediaVersion;
	}

	public MediaAudit getMediaAudit() {
		return mediaAudit;
	}

	public void setMediaAudit(MediaAudit mediaAudit) {
		this.mediaAudit = mediaAudit;
	}

}
