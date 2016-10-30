package com.janaka.projects.entitymanagement.dataaccessobjects.common;

import org.springframework.data.repository.CrudRepository;

import com.janaka.projects.entitymanagement.domain.common.Feedback;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

}
