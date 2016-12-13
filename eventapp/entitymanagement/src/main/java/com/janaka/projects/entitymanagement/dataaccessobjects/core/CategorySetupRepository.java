package com.janaka.projects.entitymanagement.dataaccessobjects.core;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.janaka.projects.entitymanagement.domain.core.CategorySetup;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public interface CategorySetupRepository extends DataTablesRepository<CategorySetup, Long> {

  List<CategorySetup> findByRecordStatus(RecordStatus active);

  List<CategorySetup> findByEvent_Id(long id);


}
