package com.janaka.projects.services.business.usermanagement;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.dtos.domain.usermanagement.UserRoleDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.usermanagement.UserRoleCreationRequest;
import com.janaka.projects.dtos.requests.usermanagement.UserRoleListRetrievalRequest;
import com.janaka.projects.dtos.requests.usermanagement.UserRoleUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.usermanagement.UserRoleCreationResponse;
import com.janaka.projects.dtos.responses.usermanagement.UserRoleUpdateResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.UserRoleRepository;
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.unitsofwork.usermanagement.UserRoleCreationUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.UserRoleDataTablesOutputUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.UserRoleDeletionUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.UserRoleListBySecurityUserIdUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.UserRoleListUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.UserRoleRetrievalUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.usermanagement.UserRoleUpdateUnitOfWork;
import com.janaka.projects.services.common.SecurityService;
import com.janaka.projects.services.usermanagement.UserRoleService;

@Service
@Transactional()
public class UserRoleServiceImpl extends BusinessService implements UserRoleService {

  @Autowired
  private UserRoleRepository userRoleRepository;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private SecurityUserRepository securityUserRepository;

  @Autowired
  private JmxNotificationPublisher jmxNotificationPublisher;

  @Override
  public UserRoleCreationResponse createUserRole(AuditContext auditContext, SecurityContext securityContext,
      UserRoleCreationRequest request) {
    UserRoleCreationUnitOfWork uow = new UserRoleCreationUnitOfWork(userRoleRepository, securityService, request,
        auditContext, securityContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public UserRoleUpdateResponse updateUserRole(AuditContext auditContext, SecurityContext securityContext,
      UserRoleUpdateRequest request) {
    UserRoleUpdateUnitOfWork uow = new UserRoleUpdateUnitOfWork(userRoleRepository, securityService, request,
        auditContext, securityContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public ObjectDeletionResponse deleteUserRole(AuditContext auditContext, SecurityContext securityContext,
      ObjectDeletionRequest request) {
    UserRoleDeletionUnitOfWork uow = new UserRoleDeletionUnitOfWork(userRoleRepository, securityService, request,
        auditContext, securityContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public ObjectListResponse<UserRoleDTO> getActiveUserRoles(AuditContext auditContext,
      SecurityContext securityContext) {
    UserRoleListUnitOfWork uow = new UserRoleListUnitOfWork(userRoleRepository, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public ObjectRetrievalResponse<UserRoleDTO> getUserRoleById(AuditContext auditContext,
      SecurityContext securityContext, ObjectRetrievalRequest request) {
    UserRoleRetrievalUnitOfWork uow =
        new UserRoleRetrievalUnitOfWork(userRoleRepository, request, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public TabularDataResponseModel<UserRoleDTO> getUserRoles(AuditContext auditContext, SecurityContext securityContext,
      TabularDataRequestModel request) {
    UserRoleDataTablesOutputUnitOfWork uow =
        new UserRoleDataTablesOutputUnitOfWork(userRoleRepository, request, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public ObjectListResponse<UserRoleDTO> getUserRolesBySecurityUserId(AuditContext auditContext,
      SecurityContext securityContext, UserRoleListRetrievalRequest request) {
    UserRoleListBySecurityUserIdUnitOfWork uow =
        new UserRoleListBySecurityUserIdUnitOfWork(securityUserRepository, request, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }



}
