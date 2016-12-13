package com.janaka.projects.services.business.unitsofwork.usermanagement;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.dtos.domain.usermanagement.SecurityUserDTO;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.SecurityUserDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;

public class SecurityUserRetrievalUnitOfWork extends UnitOfWork {

  private SecurityUserRepository repository = null;


  private ObjectRetrievalRequest request = null;
  private ObjectRetrievalResponse<SecurityUserDTO> response = null;

  private SecurityUser securityUser = null;



  @Override
  protected void doWork() {
    if (!(this.request == null)) {
      this.securityUser = repository.findOne(request.getId());
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    response = new ObjectRetrievalResponse<SecurityUserDTO>();
    if (isSuccessful) {
      if (!(this.securityUser == null)) {
        response.setId(securityUser.getId());
        response.setDto(SecurityUserDTOConverter.convertDomainToDTO(this.securityUser));
        response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      }
    }
    super.postExecute(isSuccessful);
  }

  public SecurityUserRetrievalUnitOfWork(SecurityUserRepository securityUserRepository, ObjectRetrievalRequest request,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.repository = securityUserRepository;
    this.request = request;
  }

  public ObjectRetrievalResponse<SecurityUserDTO> getResponse() {
    return response;
  }



}
