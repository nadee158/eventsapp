package com.janaka.projects.entitymanagement.dataaccessobjects.core;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.janaka.projects.entitymanagement.domain.core.Team;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public interface TeamRepository extends DataTablesRepository<Team, Long> {

  List<Team> findByRecordStatus(RecordStatus active);


}
