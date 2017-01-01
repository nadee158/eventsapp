package com.janaka.projects.services.usermanagement;

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

public interface SecurityUserService {

  public SecurityUserCreationResponse createSecurityUser(AuditContext auditContext, SecurityContext securityContext,
      SecurityUserCreationRequest request);

  public SecurityUserUpdateResponse updateSecurityUser(AuditContext auditContext, SecurityContext securityContext,
      SecurityUserUpdateRequest request);

  public ObjectDeletionResponse deleteSecurityUser(AuditContext auditContext, SecurityContext securityContext,
      ObjectDeletionRequest request);

  public ObjectListResponse<SecurityUserDTO> getActiveSecurityUsers(AuditContext auditContext,
      SecurityContext securityContext);

  public ObjectRetrievalResponse<SecurityUserDTO> getSecurityUserById(AuditContext auditContext,
      SecurityContext securityContext, ObjectRetrievalRequest request);

  public TabularDataResponseModel<SecurityUserDTO> getSecurityUsers(AuditContext auditContext,
      SecurityContext securityContext, TabularDataRequestModel request);

  public ObjectListResponse<MaritalStatusDTO> getMaritalStatus(AuditContext auditContext,
      SecurityContext securityContext);

  public ObjectListResponse<PrefixDTO> getPrefixes(AuditContext auditContext, SecurityContext securityContext);

  public SecurityUserUpdateResponse updateUserRolesOfSecurityUser(AuditContext auditContext,
      SecurityContext securityContext, SecurityUserPermissionUpdateRequest request);


  public SecurityUserUpdateResponse updateSecurityUserProfile(AuditContext auditContext,
      SecurityContext securityContext, SecurityUserProfileUpdateRequest request);

  public long getActiveCount();


}
