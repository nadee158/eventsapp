package com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public interface SecurityUserRepository extends DataTablesRepository<SecurityUser, Long> {

  public SecurityUser findByUserName(String userName);

  public SecurityUser findByUuId(String securityUserId);

  public List<SecurityUser> findByUserNameOrPersonNic(String userName, String nic);

  public List<SecurityUser> findByRecordStatus(RecordStatus recordStatus);

  public List<SecurityUser> findByPersonNic(String nic);

}
