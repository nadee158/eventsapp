package com.janaka.projects.entitymanagement.specifications.core;

import org.springframework.data.jpa.domain.Specification;

import com.janaka.projects.entitymanagement.domain.core.AgeGroup;
import com.janaka.projects.entitymanagement.domain.core.AgeGroup_;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class AgeGroupSpecifications {

  public static Specification<AgeGroup> isNotDeleted() {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get(AgeGroup_.recordStatus), RecordStatus.ACTIVE);
    };
  }

}
