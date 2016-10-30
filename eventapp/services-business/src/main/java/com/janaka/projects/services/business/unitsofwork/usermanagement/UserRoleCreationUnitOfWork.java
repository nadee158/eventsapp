package com.janaka.projects.services.business.unitsofwork.usermanagement;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.requests.common.GetSessionDetailsRequest;
import com.janaka.projects.dtos.requests.usermanagement.UserRoleCreationRequest;
import com.janaka.projects.dtos.responses.common.GetSessionDetailsResponse;
import com.janaka.projects.dtos.responses.usermanagement.UserRoleCreationResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.UserRoleRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.UserRoleDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;
import com.janaka.projects.services.common.SecurityService;

public class UserRoleCreationUnitOfWork extends UnitOfWork {

  private UserRoleRepository userRoleRepository = null;

  private SecurityService securityService = null;

  private UserRoleCreationRequest request = null;
  private UserRoleCreationResponse response = null;
  private UserRole userRole = null;

  private AuditContext auditContext = null;
  private SecurityContext securityContext = null;

  private User userFromSession = null;

  private String message = StringUtils.EMPTY;

  private boolean isExists = false;

  @Override
  protected void preExecute() {
    if (!(securityContext == null || securityContext.getToken() == null)) {
      GetSessionDetailsRequest getSessionDetailsRequest = new GetSessionDetailsRequest();
      getSessionDetailsRequest.setToken(securityContext.getToken());
      GetSessionDetailsResponse getSessionDetailsResponse = securityService.getSessionDetails(getSessionDetailsRequest);
      if (!(getSessionDetailsResponse == null)) {
        userFromSession = getSessionDetailsResponse.getUser();
      }
      super.preExecute();
    }


    if (!(request == null)) {
      UserRole userRoleFromDb = userRoleRepository.findByUserRoleName(request.getUserRoleName());
      if (!(userRoleFromDb == null)) {
        // already exists, can't create a new one
        isExists = true;
        message = "usrmgt.user_role.validation.message.USER_ROLE_CODE";
      } else {
        // not exists, safe to create a new one.
        this.userRole = UserRoleDTOConverter.convertRequestToDomain(this.request);
        if (!(userRole == null)) {
          userRole.setDeleted(false);
          if (!(userFromSession == null)) {
            this.userRole.setCreatedByUser(userFromSession.getSecurityUserId().toString());
          }
        }
      }

    }
  }

  @Override
  protected void doWork() {
    if (!(this.userRole == null || isExists)) {
      this.userRole = userRoleRepository.save(this.userRole);
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    this.response = new UserRoleCreationResponse();
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

  public UserRoleCreationUnitOfWork(UserRoleRepository userRoleRepository, SecurityService securityService,
      UserRoleCreationRequest request, AuditContext auditContext, SecurityContext securityContext,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.userRoleRepository = userRoleRepository;
    this.securityService = securityService;
    this.request = request;
    this.auditContext = auditContext;
    this.securityContext = securityContext;
  }

  public UserRoleCreationResponse getResponse() {
    return response;
  }



}
