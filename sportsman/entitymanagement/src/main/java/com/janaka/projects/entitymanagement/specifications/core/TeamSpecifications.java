package com.janaka.projects.entitymanagement.specifications.core;

import org.springframework.data.jpa.domain.Specification;

import com.janaka.projects.entitymanagement.domain.core.Team;
import com.janaka.projects.entitymanagement.domain.core.Team_;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class TeamSpecifications {

  public static Specification<Team> isNotDeleted() {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get(Team_.recordStatus), RecordStatus.ACTIVE);
    };
  }

}
