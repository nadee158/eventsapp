package com.janaka.projects.services.business.unitsofwork.usermanagement;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.dtos.requests.usermanagement.UserRoleUpdateRequest;
import com.janaka.projects.dtos.responses.usermanagement.UserRoleUpdateResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.UserRoleRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.UserRoleDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;

public class UserRoleUpdateUnitOfWork extends UnitOfWork {

  private UserRoleRepository userRoleRepository = null;

  private UserRoleUpdateRequest request = null;
  private UserRoleUpdateResponse response = null;
  private UserRole userRole = null;

  private AuditContext auditContext = null;
  private SecurityContext securityContext = null;

  private String message = StringUtils.EMPTY;

  private boolean isExists = false;

  @Override
  protected void preExecute() {
    if (!(request == null)) {
      // check if the application name and code already exists
      UserRole userRoleFromDb = userRoleRepository.findByUserRoleName(request.getUserRoleName());
      if (!(userRoleFromDb == null)) {
        if (!(userRoleFromDb.getId() == request.getId())) {
          isExists = true;
          message = "usrmgt.user_role.validation.message.USER_ROLE_CODE";
        }
      }

      if (!(isExists)) {
        this.userRole = userRoleRepository.findOne(request.getId());
      }

      if (!(userRole == null)) {
        userRole.setUserRoleName(request.getUserRoleName());
      }
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
    this.response = new UserRoleUpdateResponse();
    if (isExists) {
      this.response.setMessage(message);
      this.response.setStatus(ApplicationConstants.STATUS_CODE_CONFLICT);
    } else {
      if (isSuccessful) {
        this.response.setId(this.userRole.getId());
        this.response.setUserRoleDTO(UserRoleDTOConverter.convertDomainToDTO(this.userRole));
      }
    }
    super.postExecute(isSuccessful);
  }

  public UserRoleUpdateUnitOfWork(UserRoleRepository userRoleRepository, UserRoleUpdateRequest request,
      AuditContext auditContext, SecurityContext securityContext, JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.userRoleRepository = userRoleRepository;
    this.request = request;
    this.auditContext = auditContext;
    this.securityContext = securityContext;
  }

  public UserRoleUpdateResponse getResponse() {
    return response;
  }



}
