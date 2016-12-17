package com.janaka.projects.entitymanagement.specifications.core;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.janaka.projects.entitymanagement.domain.core.Player;
import com.janaka.projects.entitymanagement.domain.core.Player_;
import com.janaka.projects.entitymanagement.domain.usermanagement.Person_;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class PlayerSpecifications {

  public static Specification<Player> isNotDeleted() {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get(Player_.recordStatus), RecordStatus.ACTIVE);
    };
  }

  public static Specification<Player> combinedSpecifications(String playerName, String playerNumber, String nic,
      String contactNumber) {
    return (root, query, criteriaBuilder) -> {
      Predicate predicate = criteriaBuilder.conjunction();
      predicate =
          criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(Player_.recordStatus), RecordStatus.ACTIVE));
      if (StringUtils.isNotEmpty(playerName)) {
        predicate = criteriaBuilder.and(predicate,
            criteriaBuilder.like(criteriaBuilder.lower(root.get(Player_.person).get(Person_.fullName)),
                "%" + StringUtils.lowerCase(playerName) + "%"));
      }
      if (StringUtils.isNotEmpty(playerNumber)) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder
            .like(criteriaBuilder.lower(root.get(Player_.playerNumber)), "%" + StringUtils.lowerCase(playerNumber)));
      }
      if (StringUtils.isNotEmpty(nic)) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
            criteriaBuilder.lower(root.get(Player_.person).get(Person_.nic)), "%" + StringUtils.lowerCase(nic) + "%"));
      }
      if (StringUtils.isNotEmpty(contactNumber)) {
        predicate = criteriaBuilder.and(predicate,
            criteriaBuilder.like(criteriaBuilder.lower(root.get(Player_.person).get(Person_.contactNumber)),
                "%" + StringUtils.lowerCase(playerName) + "%"));
      }
      return predicate;
    };
  }

}
