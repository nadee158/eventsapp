package com.janaka.projects.services.business.unitsofwork.usermanagement;

import java.util.ArrayList;
import java.util.List;

import com.janaka.projects.dtos.domain.usermanagement.UserRoleDTO;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.UserRoleRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.UserRoleDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;

public class UserRoleListUnitOfWork extends UnitOfWork {

  private UserRoleRepository repository = null;

  private ObjectListResponse<UserRoleDTO> response = null;
  private List<UserRole> domainList = null;

  @Override
  protected void doWork() {
    this.domainList = (List<UserRole>) repository.findByIsDeleted(false);
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    this.response = new ObjectListResponse<UserRoleDTO>();
    if (isSuccessful) {
      if (!(this.domainList == null || this.domainList.isEmpty())) {
        List<UserRoleDTO> dtoList = new ArrayList<>();
        for (UserRole domain : this.domainList) {
          dtoList.add(UserRoleDTOConverter.convertDomainToDTO(domain));
        }
        this.response.setDtoList(dtoList);
        this.response.setListSize(dtoList.size());
      }

    }
    super.postExecute(isSuccessful);
  }

  public ObjectListResponse<UserRoleDTO> getResponse() {
    return response;
  }

  public UserRoleListUnitOfWork(UserRoleRepository repository, JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.repository = repository;
  }



}
