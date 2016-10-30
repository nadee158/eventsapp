package com.janaka.projects.entitymanagement.domain.documentmanagement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.janaka.projects.entitymanagement.domain.common.BaseDomain;

@Entity
@Table(name = "media_version")
public class DocVersion extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = 0;

	@Version
	@Column(name = "version_number")
	private long versionNumber = 0;

}
