package com.janaka.projects.entitymanagement.dataaccessobjects.core;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.janaka.projects.entitymanagement.domain.core.AgeGroup;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public interface AgeGroupRepository extends DataTablesRepository<AgeGroup, Long> {

  List<AgeGroup> findByRecordStatus(RecordStatus active);


}
