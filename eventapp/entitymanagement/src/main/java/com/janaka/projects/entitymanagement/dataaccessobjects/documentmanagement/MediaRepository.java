package com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement;

import org.springframework.data.repository.CrudRepository;

import com.janaka.projects.entitymanagement.domain.documentmanagement.Media;

public interface MediaRepository extends CrudRepository<Media, Long> {

}
