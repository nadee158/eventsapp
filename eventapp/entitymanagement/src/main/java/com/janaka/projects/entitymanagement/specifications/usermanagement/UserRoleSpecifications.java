package com.janaka.projects.entitymanagement.specifications.usermanagement;

import org.springframework.data.jpa.domain.Specification;

import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole;
import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole_;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class UserRoleSpecifications {

  public static Specification<UserRole> isNotDeleted() {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get(UserRole_.recordStatus), RecordStatus.ACTIVE);
    };
  }
}
