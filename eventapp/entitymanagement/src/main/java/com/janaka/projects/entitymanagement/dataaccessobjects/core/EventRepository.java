package com.janaka.projects.entitymanagement.dataaccessobjects.core;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.janaka.projects.entitymanagement.domain.core.Event;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public interface EventRepository extends DataTablesRepository<Event, Long> {

  List<Event> findByRecordStatus(RecordStatus active);


}
