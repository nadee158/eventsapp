package com.janaka.projects.entitymanagement.domain.documentmanagement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.entitymanagement.domain.common.BaseDomain;

@Entity
@Table(name = "media")
public class Domain extends BaseDomain {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = 0;

	@Column(name = "name")
	private String name = StringUtils.EMPTY;

	@Column(name = "description")
	private String description = StringUtils.EMPTY;

	@Version
	@Column(name = "version_number")
	private long versionNumber = 0;
}
