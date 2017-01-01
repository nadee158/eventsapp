package com.janaka.projects.entitymanagement.specifications.core;

import org.springframework.data.jpa.domain.Specification;

import com.janaka.projects.entitymanagement.domain.core.GradeBelt;
import com.janaka.projects.entitymanagement.domain.core.GradeBelt_;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class GradeBeltSpecifications {

  public static Specification<GradeBelt> isNotDeleted() {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get(GradeBelt_.recordStatus), RecordStatus.ACTIVE);
    };
  }

}
