package com.janaka.projects.services.usermanagement;

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

public interface UserRoleService {

  public UserRoleCreationResponse createUserRole(AuditContext auditContext, SecurityContext securityContext,
      UserRoleCreationRequest request);

  public UserRoleUpdateResponse updateUserRole(AuditContext auditContext, SecurityContext securityContext,
      UserRoleUpdateRequest request);

  public ObjectDeletionResponse deleteUserRole(AuditContext auditContext, SecurityContext securityContext,
      ObjectDeletionRequest request);

  public ObjectListResponse<UserRoleDTO> getActiveUserRoles(AuditContext auditContext, SecurityContext securityContext);

  public ObjectRetrievalResponse<UserRoleDTO> getUserRoleById(AuditContext auditContext,
      SecurityContext securityContext, ObjectRetrievalRequest request);

  public TabularDataResponseModel<UserRoleDTO> getUserRoles(AuditContext auditContext, SecurityContext securityContext,
      TabularDataRequestModel request);

  public ObjectListResponse<UserRoleDTO> getUserRolesBySecurityUserId(AuditContext auditContext,
      SecurityContext securityContext, UserRoleListRetrievalRequest request);

  public long getActiveCount();

}
