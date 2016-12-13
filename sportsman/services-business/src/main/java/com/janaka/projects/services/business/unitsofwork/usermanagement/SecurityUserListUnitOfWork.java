package com.janaka.projects.services.business.unitsofwork.usermanagement;

import java.util.ArrayList;
import java.util.List;

import com.janaka.projects.dtos.domain.usermanagement.SecurityUserDTO;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.entitymanagement.enums.RecordStatus;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.SecurityUserDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;

public class SecurityUserListUnitOfWork extends UnitOfWork {

  private SecurityUserRepository repository = null;

  private ObjectListResponse<SecurityUserDTO> response = null;
  private List<SecurityUser> domainList = null;

  @Override
  protected void doWork() {
    this.domainList = (List<SecurityUser>) repository.findByRecordStatus(RecordStatus.ACTIVE);
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    this.response = new ObjectListResponse<SecurityUserDTO>();
    if (isSuccessful) {
      if (!(this.domainList == null || this.domainList.isEmpty())) {
        List<SecurityUserDTO> dtoList = new ArrayList<>();
        for (SecurityUser domain : this.domainList) {
          dtoList.add(SecurityUserDTOConverter.convertDomainToDTO(domain));
        }
        this.response.setDtoList(dtoList);
        this.response.setListSize(dtoList.size());
      }

    }
    super.postExecute(isSuccessful);
  }

  public ObjectListResponse<SecurityUserDTO> getResponse() {
    return response;
  }

  public SecurityUserListUnitOfWork(SecurityUserRepository repository,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.repository = repository;
  }



}
