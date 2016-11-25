package com.janaka.projects.entitymanagement.dataaccessobjects.core;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.janaka.projects.entitymanagement.domain.core.Event;

public interface EventRepository extends DataTablesRepository<Event, Long> {


}
