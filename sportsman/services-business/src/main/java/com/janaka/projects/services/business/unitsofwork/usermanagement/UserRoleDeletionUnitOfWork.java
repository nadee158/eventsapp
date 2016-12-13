package com.janaka.projects.services.business.unitsofwork.usermanagement;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.UserRoleRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;

public class UserRoleDeletionUnitOfWork extends UnitOfWork {

  private UserRoleRepository userRoleRepository = null;

  private ObjectDeletionRequest request = null;
  private ObjectDeletionResponse response = null;

  private AuditContext auditContext = null;
  private SecurityContext securityContext = null;

  private UserRole userRole = null;

  @Override
  protected void preExecute() {
    setAuditContext(auditContext);
    setSecurityContext(securityContext);

    if (!(request == null)) {
      this.userRole = userRoleRepository.findOne(request.getId());
    }
  }

  @Override
  protected void doWork() {
    if (!(this.userRole == null)) {
      this.userRole = userRoleRepository.save(this.userRole);
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    response = new ObjectDeletionResponse();
    if (isSuccessful) {
      response.setId(this.userRole.getId());
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
    }
    super.postExecute(isSuccessful);
  }


  public ObjectDeletionResponse getResponse() {
    return response;
  }


  public UserRoleDeletionUnitOfWork(UserRoleRepository userRoleRepository, ObjectDeletionRequest request,
      AuditContext auditContext, SecurityContext securityContext, JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.userRoleRepository = userRoleRepository;
    this.request = request;
    this.auditContext = auditContext;
    this.securityContext = securityContext;
  }



}
