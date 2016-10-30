package com.janaka.projects.services.business.usermanagement;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.dtos.domain.usermanagement.MaritalStatusDTO;
import com.janaka.projects.dtos.domain.usermanagement.PrefixDTO;
import com.janaka.projects.dtos.domain.usermanagement.SecurityUserDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserCreationRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserPermissionUpdateRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserProfileUpdateRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.usermanagement.SecurityUserCreationResponse;
import com.janaka.projects.dtos.responses.usermanagement.SecurityUserUpdateResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.UserRoleRepository;
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.unitsofwork.usermanagement.MaritalStatusRetrievalUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.PrefixRetrievalUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.SecurityUserCreationUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.SecurityUserDataTablesOutputUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.SecurityUserDeletionUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.SecurityUserListUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.SecurityUserProfileUpdateUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.SecurityUserRetrievalUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.SecurityUserUpdateUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.UserRolesOfSecurityUserUpdateunitOfWork;
import com.janaka.projects.services.common.SecurityService;
import com.janaka.projects.services.usermanagement.SecurityUserService;

@Service("userService")
@Transactional
public class SecurityUserServiceImpl extends BusinessService implements SecurityUserService {

  @Autowired
  private SecurityUserRepository securityUserRepository;

  @Autowired
  private UserRoleRepository userRoleRepository;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private JmxNotificationPublisher jmxNotificationPublisher;

  @Override
  public SecurityUserCreationResponse createSecurityUser(AuditContext auditContext, SecurityContext securityContext,
      SecurityUserCreationRequest request) {
    SecurityUserCreationUnitOfWork uow = new SecurityUserCreationUnitOfWork(securityUserRepository, userRoleRepository,
        securityService, request, auditContext, securityContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public SecurityUserUpdateResponse updateSecurityUser(AuditContext auditContext, SecurityContext securityContext,
      SecurityUserUpdateRequest request) {
    SecurityUserUpdateUnitOfWork uow = new SecurityUserUpdateUnitOfWork(securityUserRepository, securityService,
        request, auditContext, securityContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public ObjectDeletionResponse deleteSecurityUser(AuditContext auditContext, SecurityContext securityContext,
      ObjectDeletionRequest request) {
    SecurityUserDeletionUnitOfWork uow = new SecurityUserDeletionUnitOfWork(securityUserRepository, securityService,
        request, auditContext, securityContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public ObjectListResponse<SecurityUserDTO> getActiveSecurityUsers(AuditContext auditContext,
      SecurityContext securityContext) {
    SecurityUserListUnitOfWork uow = new SecurityUserListUnitOfWork(securityUserRepository, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public ObjectRetrievalResponse<SecurityUserDTO> getSecurityUserById(AuditContext auditContext,
      SecurityContext securityContext, ObjectRetrievalRequest request) {
    SecurityUserRetrievalUnitOfWork uow =
        new SecurityUserRetrievalUnitOfWork(securityUserRepository, request, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public TabularDataResponseModel<SecurityUserDTO> getSecurityUsers(AuditContext auditContext,
      SecurityContext securityContext, TabularDataRequestModel request) {
    SecurityUserDataTablesOutputUnitOfWork uow = new SecurityUserDataTablesOutputUnitOfWork(securityUserRepository,
        request, securityService, auditContext, securityContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }



  @Override
  public SecurityUserUpdateResponse updateUserRolesOfSecurityUser(AuditContext auditContext,
      SecurityContext securityContext, SecurityUserPermissionUpdateRequest request) {
    UserRolesOfSecurityUserUpdateunitOfWork uow = new UserRolesOfSecurityUserUpdateunitOfWork(securityUserRepository,
        userRoleRepository, securityService, request, auditContext, securityContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }



  @Override
  public SecurityUserUpdateResponse updateSecurityUserProfile(AuditContext auditContext,
      SecurityContext securityContext, SecurityUserProfileUpdateRequest request) {
    SecurityUserProfileUpdateUnitOfWork uow = new SecurityUserProfileUpdateUnitOfWork(securityUserRepository,
        securityService, request, auditContext, securityContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public ObjectListResponse<PrefixDTO> getPrefixes(AuditContext auditContext, SecurityContext securityContext) {
    PrefixRetrievalUnitOfWork uow = new PrefixRetrievalUnitOfWork(jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public ObjectListResponse<MaritalStatusDTO> getMaritalStatus(AuditContext auditContext,
      SecurityContext securityContext) {
    MaritalStatusRetrievalUnitOfWork uow = new MaritalStatusRetrievalUnitOfWork(jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }



}
