package com.janaka.projects.services.business.unitsofwork.usermanagement;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.requests.common.GetSessionDetailsRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserCreationRequest;
import com.janaka.projects.dtos.responses.common.GetSessionDetailsResponse;
import com.janaka.projects.dtos.responses.usermanagement.SecurityUserCreationResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.UserRoleRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.SecurityUserDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;
import com.janaka.projects.services.common.SecurityService;

public class SecurityUserCreationUnitOfWork extends UnitOfWork {

  private SecurityUserRepository securityUserRepository = null;

  private UserRoleRepository userRoleRepository;

  private SecurityService securityService = null;

  private SecurityUserCreationRequest request = null;
  private SecurityUserCreationResponse response = null;

  private SecurityUser securityUser = null;

  private AuditContext auditContext = null;
  private SecurityContext securityContext = null;

  private User userFromSession = null;

  private String message = StringUtils.EMPTY;

  private boolean isExists = false;

  @Override
  protected void preExecute() {
    setAuditContext(auditContext);
    setSecurityContext(securityContext);
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
      // check if the application name and code already exists
      List<SecurityUser> securityUsersFromDb =
          securityUserRepository.findByUserNameOrPersonNic(request.getUserName(), request.getNic());

      if (!(securityUsersFromDb == null || securityUsersFromDb.isEmpty())) {
        isExists = true;
        message = "usrmgt.security_user.validation.message.USER_NAME_NIC_ALREADY_EXISTS";
      } else {
        // not exists, safe to create a new one.
        this.securityUser = SecurityUserDTOConverter.convertRequestToDomain(this.request, userRoleRepository);
        if (!(securityUser == null)) {
          securityUser.setDeleted(false);
          if (!(userFromSession == null)) {
            this.securityUser.setCreatedByUser(userFromSession.getSecurityUserId().toString());
          }
        }
      }

    }
  }

  @Override
  protected void doWork() {
    if (!(this.securityUser == null || isExists)) {
      this.securityUser = securityUserRepository.save(this.securityUser);
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    this.response = new SecurityUserCreationResponse();
    if (isExists) {
      this.response.setMessage(message);
      this.response.setStatus(ApplicationConstants.STATUS_CODE_CONFLICT);
    } else {
      if (isSuccessful) {
        this.response.setId(this.securityUser.getId());
        this.response.setSecurityUserDTO(SecurityUserDTOConverter.convertDomainToDTO(this.securityUser));
      }
    }
    super.postExecute(isSuccessful);
  }

  public SecurityUserCreationResponse getResponse() {
    return response;
  }

  public SecurityUserCreationUnitOfWork(SecurityUserRepository securityUserRepository,
      UserRoleRepository userRoleRepository, SecurityService securityService, SecurityUserCreationRequest request,
      AuditContext auditContext, SecurityContext securityContext, JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.securityUserRepository = securityUserRepository;
    this.userRoleRepository = userRoleRepository;
    this.securityService = securityService;
    this.request = request;
    this.auditContext = auditContext;
    this.securityContext = securityContext;
  }



}
