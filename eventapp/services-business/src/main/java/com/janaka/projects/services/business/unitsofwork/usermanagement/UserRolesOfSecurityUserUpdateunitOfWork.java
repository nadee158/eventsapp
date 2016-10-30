package com.janaka.projects.services.business.unitsofwork.usermanagement;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.requests.common.GetSessionDetailsRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserPermissionUpdateRequest;
import com.janaka.projects.dtos.responses.common.GetSessionDetailsResponse;
import com.janaka.projects.dtos.responses.usermanagement.SecurityUserUpdateResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.UserRoleRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.SecurityUserDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;
import com.janaka.projects.services.common.SecurityService;

public class UserRolesOfSecurityUserUpdateunitOfWork extends UnitOfWork {

  private SecurityUserRepository securityUserRepository = null;

  private UserRoleRepository userRoleRepository = null;

  private SecurityService securityService = null;

  private SecurityUserPermissionUpdateRequest request = null;
  private SecurityUserUpdateResponse response = null;

  private SecurityUser securityUser = null;

  private AuditContext auditContext = null;
  private SecurityContext securityContext = null;

  private User userFromSession = null;

  private String message = StringUtils.EMPTY;


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

      this.securityUser = securityUserRepository.findOne(request.getId());

      if (!(securityUser == null)) {

        this.securityUser =
            SecurityUserDTOConverter.updateDomainFromRequest(this.request, securityUser, userRoleRepository);

        if (!(userFromSession == null)) {
          this.securityUser.setModifiedByUser(userFromSession.getSecurityUserId().toString());
        }
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
    this.response = new SecurityUserUpdateResponse();
    if (isSuccessful) {
      this.response.setId(this.securityUser.getId());
      this.response.setSecurityUserDTO(SecurityUserDTOConverter.convertDomainToDTO(this.securityUser));
    }
    super.postExecute(isSuccessful);
  }



  public UserRolesOfSecurityUserUpdateunitOfWork(SecurityUserRepository securityUserRepository,
      UserRoleRepository userRoleRepository, SecurityService securityService,
      SecurityUserPermissionUpdateRequest request, AuditContext auditContext, SecurityContext securityContext,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.securityUserRepository = securityUserRepository;
    this.userRoleRepository = userRoleRepository;
    this.securityService = securityService;
    this.request = request;
    this.auditContext = auditContext;
    this.securityContext = securityContext;
  }

  public SecurityUserUpdateResponse getResponse() {
    return response;
  }



}
