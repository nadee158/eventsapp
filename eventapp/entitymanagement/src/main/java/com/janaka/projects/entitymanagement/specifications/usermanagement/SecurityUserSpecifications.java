package com.janaka.projects.entitymanagement.specifications.usermanagement;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser_;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class SecurityUserSpecifications {


  public static Specification<SecurityUser> isNotDeleted(long securityUserId) {
    return (root, query, criteriaBuilder) -> {
      Predicate predicateAnd = criteriaBuilder.conjunction();
      predicateAnd =
          criteriaBuilder.and(predicateAnd, criteriaBuilder.notEqual(root.get(SecurityUser_.id), securityUserId));
      predicateAnd = criteriaBuilder.and(predicateAnd,
          criteriaBuilder.equal(root.get(SecurityUser_.recordStatus), RecordStatus.ACTIVE));
      return predicateAnd;
      // return criteriaBuilder.equal(root.get(Application_.isDeleted),
      // false);
    };
  }


  // Note: User_ is the @StaticMetamodel of the class User
  // generated by hibernate-jpamodelgen dependency
  // The source folder target/generated-sources/annotations must be
  // added to the buildpath (Project > Configure Build Path)
  // public static Specification<Application> hasLikesBetween(Integer
  // lowerBound, Integer
  // upperBound) {
  // return (root, query, criteriaBuilder) -> {
  // Predicate predicate = criteriaBuilder.conjunction();
  // if (lowerBound != null) {
  // predicate =
  // criteriaBuilder.and(predicate,
  // criteriaBuilder.greaterThanOrEqualTo(root.get(User_.likes),
  // lowerBound));
  // }
  // if (upperBound != null) {
  // predicate =
  // criteriaBuilder.and(predicate,
  // criteriaBuilder.lessThanOrEqualTo(root.get(User_.likes),
  // upperBound));
  // }
  // return predicate;
  // };
  // }

}
