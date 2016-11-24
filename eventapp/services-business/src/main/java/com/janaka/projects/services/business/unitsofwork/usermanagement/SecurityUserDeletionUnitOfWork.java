package com.janaka.projects.services.business.unitsofwork.usermanagement;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;

public class SecurityUserDeletionUnitOfWork extends UnitOfWork {

  private SecurityUserRepository securityUserRepository = null;

  private ObjectDeletionRequest request = null;
  private ObjectDeletionResponse response = null;

  private AuditContext auditContext = null;
  private SecurityContext securityContext = null;

  private SecurityUser securityUser = null;

  private User userFromSession = null;

  @Override
  protected void preExecute() {
    setAuditContext(auditContext);
    setSecurityContext(securityContext);

    if (!(request == null)) {
      this.securityUser = securityUserRepository.findOne(request.getId());
      if (!(this.securityUser == null)) {
        this.securityUser.setAccountEnabled(false);
      }

    }
  }

  @Override
  protected void doWork() {
    if (!(this.securityUser == null)) {
      this.securityUser = securityUserRepository.save(this.securityUser);
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    response = new ObjectDeletionResponse();
    if (isSuccessful) {
      response.setId(this.securityUser.getId());
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
    }
    super.postExecute(isSuccessful);
  }


  public ObjectDeletionResponse getResponse() {
    return response;
  }

  public User getUserFromSession() {
    return userFromSession;
  }

  public SecurityUserDeletionUnitOfWork(SecurityUserRepository securityUserRepository, ObjectDeletionRequest request,
      AuditContext auditContext, SecurityContext securityContext, JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.securityUserRepository = securityUserRepository;
    this.request = request;
    this.auditContext = auditContext;
    this.securityContext = securityContext;
  }



}
