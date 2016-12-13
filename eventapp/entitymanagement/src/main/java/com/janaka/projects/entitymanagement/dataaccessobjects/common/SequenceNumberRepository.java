package com.janaka.projects.entitymanagement.dataaccessobjects.common;

import org.springframework.data.repository.CrudRepository;

import com.janaka.projects.entitymanagement.domain.common.SequenceNumber;

public interface SequenceNumberRepository extends CrudRepository<SequenceNumber, Long> {



}
