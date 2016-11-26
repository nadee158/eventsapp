package com.janaka.projects.entitymanagement.specifications.core;

import org.springframework.data.jpa.domain.Specification;

import com.janaka.projects.entitymanagement.domain.core.Event;
import com.janaka.projects.entitymanagement.domain.core.Event_;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class EventSpecifications {

  public static Specification<Event> isNotDeleted() {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get(Event_.recordStatus), RecordStatus.ACTIVE);
    };
  }

}
