package com.janaka.projects.services.web.rest.usermanagement;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.SecurityHelper;
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
import com.janaka.projects.services.usermanagement.SecurityUserService;
import com.janaka.projects.services.web.rest.ServiceEndpoints;

@RestController
@RequestMapping(ServiceEndpoints.SECURITY_USER)
public class SecurityUserController {

  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private SecurityUserService service = null;

  @RequestMapping(value = ServiceEndpoints.CREATE_SECURITY_USER, method = RequestMethod.POST)
  public SecurityUserCreationResponse createSecurityUser(@Valid @RequestBody SecurityUserCreationRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.createSecurityUser(auditContext, securityContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.UPDATE_USER_ROLES_OF_SECURITY_USER, method = RequestMethod.POST)
  public SecurityUserUpdateResponse updateUserRolesOfSecurityUser(
      @Valid @RequestBody SecurityUserPermissionUpdateRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.updateUserRolesOfSecurityUser(auditContext, securityContext, request);
  }



  @RequestMapping(value = ServiceEndpoints.UPDATE_SECURITY_USER, method = RequestMethod.POST)
  public SecurityUserUpdateResponse updateSecurityUser(@Valid @RequestBody SecurityUserUpdateRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.updateSecurityUser(auditContext, securityContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.UPDATE_SECURITY_USER_PROFILE, method = RequestMethod.POST)
  public SecurityUserUpdateResponse updateSecurityUserProfile(
      @Valid @RequestBody SecurityUserProfileUpdateRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.updateSecurityUserProfile(auditContext, securityContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.DELETE_SECURITY_USER, method = RequestMethod.POST)
  public ObjectDeletionResponse deleteSecurityUser(@RequestBody ObjectDeletionRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.deleteSecurityUser(auditContext, securityContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ACTIVE_SECURITY_USERS, method = RequestMethod.GET)
  public ObjectListResponse<SecurityUserDTO> getActiveSecurityUsers() {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.getActiveSecurityUsers(auditContext, securityContext);
  }

  @RequestMapping(value = ServiceEndpoints.GET_SECURITY_USER_BY_ID, method = RequestMethod.POST)
  public ObjectRetrievalResponse<SecurityUserDTO> getActiveSecurityUserById(
      @RequestBody ObjectRetrievalRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.getSecurityUserById(auditContext, securityContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.GET_SECURITY_USERS, method = RequestMethod.POST)
  public TabularDataResponseModel<SecurityUserDTO> getActiveSecurityUsers(
      @RequestBody TabularDataRequestModel request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.getSecurityUsers(auditContext, securityContext, request);
  }


  @RequestMapping(value = ServiceEndpoints.GET_PREFIXES, method = RequestMethod.GET)
  public ObjectListResponse<PrefixDTO> getPrefixes() {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.getPrefixes(auditContext, securityContext);
  }


  @RequestMapping(value = ServiceEndpoints.GET_MARITAL_STATUS, method = RequestMethod.GET)
  public ObjectListResponse<MaritalStatusDTO> getMaritalStatus() {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.getMaritalStatus(auditContext, securityContext);
  }
}
