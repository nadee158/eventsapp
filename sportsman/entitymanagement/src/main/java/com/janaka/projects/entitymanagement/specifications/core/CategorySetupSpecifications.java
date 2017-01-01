package com.janaka.projects.entitymanagement.specifications.core;

import org.springframework.data.jpa.domain.Specification;

import com.janaka.projects.entitymanagement.domain.core.CategorySetup;
import com.janaka.projects.entitymanagement.domain.core.CategorySetup_;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class CategorySetupSpecifications {

  public static Specification<CategorySetup> isNotDeleted() {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get(CategorySetup_.recordStatus), RecordStatus.ACTIVE);
    };
  }

}
