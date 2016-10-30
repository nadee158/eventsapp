package com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.janaka.projects.entitymanagement.domain.documentmanagement.Media;
import com.janaka.projects.entitymanagement.domain.documentmanagement.MediaVersion;

public interface MediaVersionRepository extends CrudRepository<MediaVersion, Long> {

	List<MediaVersion> findByMediaOrderByMediaVersionDesc(Media media);

}
