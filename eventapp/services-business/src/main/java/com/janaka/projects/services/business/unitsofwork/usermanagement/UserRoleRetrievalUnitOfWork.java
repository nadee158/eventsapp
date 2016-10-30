package com.janaka.projects.services.business.unitsofwork.usermanagement;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.dtos.domain.usermanagement.UserRoleDTO;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.UserRoleRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.UserRoleDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;

public class UserRoleRetrievalUnitOfWork extends UnitOfWork {

  private UserRoleRepository repository = null;


  private ObjectRetrievalRequest request = null;
  private ObjectRetrievalResponse<UserRoleDTO> response = null;

  private UserRole userRole = null;



  @Override
  protected void doWork() {
    if (!(this.request == null)) {
      this.userRole = repository.findOne(request.getId());
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    response = new ObjectRetrievalResponse<UserRoleDTO>();
    if (isSuccessful) {
      if (!(this.userRole == null)) {
        response.setId(userRole.getId());
        response.setDto(UserRoleDTOConverter.convertDomainToDTO(this.userRole));
        response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      }
    }
    super.postExecute(isSuccessful);
  }

  public UserRoleRetrievalUnitOfWork(UserRoleRepository userRoleRepository, ObjectRetrievalRequest request,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.repository = userRoleRepository;
    this.request = request;
  }

  public ObjectRetrievalResponse<UserRoleDTO> getResponse() {
    return response;
  }



}
