package com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;

public interface SecurityUserRepository extends DataTablesRepository<SecurityUser, Long> {

  public SecurityUser findByUserName(String userName);

  public SecurityUser findByUuId(String securityUserId);

  public List<SecurityUser> findByUserNameOrPersonNic(String userName, String nic);

  public List<SecurityUser> findByIsDeleted(boolean isDeleted);

  public List<SecurityUser> findByPersonNic(String nic);

}
