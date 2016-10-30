package com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement;

import org.springframework.data.repository.CrudRepository;

import com.janaka.projects.entitymanagement.domain.documentmanagement.MediaDownloadLog;

public interface MediaDownloadLogRepository extends CrudRepository<MediaDownloadLog, Long> {

}
