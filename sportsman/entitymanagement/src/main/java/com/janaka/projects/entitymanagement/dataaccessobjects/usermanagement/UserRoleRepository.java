package com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public interface UserRoleRepository extends DataTablesRepository<UserRole, Long> {

  public UserRole findByUserRoleName(String userRoleName);

  public List<UserRole> findByRecordStatus(RecordStatus recordStatus);

}
