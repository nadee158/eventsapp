package com.janaka.projects.entitymanagement.dataaccessobjects.core;

import org.springframework.data.repository.CrudRepository;

import com.janaka.projects.entitymanagement.domain.usermanagement.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
