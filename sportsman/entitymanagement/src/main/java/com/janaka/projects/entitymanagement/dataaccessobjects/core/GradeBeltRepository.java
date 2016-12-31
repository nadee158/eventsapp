package com.janaka.projects.entitymanagement.dataaccessobjects.core;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.janaka.projects.entitymanagement.domain.core.GradeBelt;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public interface GradeBeltRepository extends DataTablesRepository<GradeBelt, Long> {

  List<GradeBelt> findByRecordStatus(RecordStatus active);


}
