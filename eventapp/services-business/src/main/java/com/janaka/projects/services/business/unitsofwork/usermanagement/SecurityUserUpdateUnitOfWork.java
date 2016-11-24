package com.janaka.projects.services.business.unitsofwork.usermanagement;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserUpdateRequest;
import com.janaka.projects.dtos.responses.usermanagement.SecurityUserUpdateResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.SecurityUserDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;
import com.janaka.projects.services.common.SecurityService;

public class SecurityUserUpdateUnitOfWork extends UnitOfWork {

  private SecurityUserRepository securityUserRepository = null;

  private SecurityService securityService = null;

  private SecurityUserUpdateRequest request = null;
  private SecurityUserUpdateResponse response = null;

  private SecurityUser securityUser = null;

  private AuditContext auditContext = null;
  private SecurityContext securityContext = null;

  private String message = StringUtils.EMPTY;

  private boolean isExists = false;

  @Override
  protected void preExecute() {
    setAuditContext(auditContext);
    setSecurityContext(securityContext);

    if (!(request == null)) {
      // check if the application name and code already exists
      List<SecurityUser> securityUsersFromDb =
          securityUserRepository.findByUserNameOrPersonNic(request.getUserName(), request.getNic());

      if (!(securityUsersFromDb == null || securityUsersFromDb.isEmpty())) {
        for (SecurityUser securityUserFromDb : securityUsersFromDb) {
          if (!(securityUserFromDb.getId() == request.getId())) {
            isExists = true;
            message = "usrmgt.security_user.validation.message.USER_NAME_OR_NIC_ALREADY_EXISTS";
          }
        }
      }

      if (!(isExists)) {
        this.securityUser = securityUserRepository.findOne(request.getId());
      }

      if (!(securityUser == null)) {

        this.securityUser = SecurityUserDTOConverter.updateDomainFromRequest(this.request, securityUser);

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

  public SecurityUserUpdateUnitOfWork(SecurityUserRepository securityUserRepository, SecurityUserUpdateRequest request,
      AuditContext auditContext, SecurityContext securityContext, JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.securityUserRepository = securityUserRepository;
    this.request = request;
    this.auditContext = auditContext;
    this.securityContext = securityContext;
  }

  public SecurityUserUpdateResponse getResponse() {
    return response;
  }



}
