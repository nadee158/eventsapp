package com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement;

import org.springframework.data.repository.CrudRepository;

import com.janaka.projects.entitymanagement.domain.documentmanagement.MediaFormat;

public interface MediaFormatRepository extends CrudRepository<MediaFormat, Long> {

	public MediaFormat findByMimeType(String mimeType);

}
