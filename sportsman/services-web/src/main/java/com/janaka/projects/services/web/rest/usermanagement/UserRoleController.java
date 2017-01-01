package com.janaka.projects.services.web.rest.usermanagement;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.SecurityHelper;
import com.janaka.projects.dtos.domain.core.ApiResponseObject;
import com.janaka.projects.dtos.domain.core.CustomResponseEntity;
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
import com.janaka.projects.services.usermanagement.UserRoleService;
import com.janaka.projects.services.web.rest.ServiceEndpoints;

@RestController
@RequestMapping(ServiceEndpoints.USER_ROLE)
public class UserRoleController {

  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private UserRoleService service = null;

  @RequestMapping(value = ServiceEndpoints.CREATE_USER_ROLE, method = RequestMethod.POST)
  public UserRoleCreationResponse createUserRole(@Valid @RequestBody UserRoleCreationRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.createUserRole(auditContext, securityContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.UPDATE_USER_ROLE, method = RequestMethod.POST)
  public UserRoleUpdateResponse updateUserRole(@Valid @RequestBody UserRoleUpdateRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.updateUserRole(auditContext, securityContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.DELETE_USER_ROLE, method = RequestMethod.POST)
  public ObjectDeletionResponse deleteUserRole(@RequestBody ObjectDeletionRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.deleteUserRole(auditContext, securityContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ACTIVE_USER_ROLES, method = RequestMethod.GET)
  public ObjectListResponse<UserRoleDTO> getActiveUserRoles() {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.getActiveUserRoles(auditContext, securityContext);
  }

  @RequestMapping(value = ServiceEndpoints.GET_USER_ROLE_BY_ID, method = RequestMethod.POST)
  public ObjectRetrievalResponse<UserRoleDTO> getActiveUserRoleById(@RequestBody ObjectRetrievalRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.getUserRoleById(auditContext, securityContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.GET_USER_ROLES, method = RequestMethod.POST)
  public TabularDataResponseModel<UserRoleDTO> getActiveUserRoles(@RequestBody TabularDataRequestModel request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.getUserRoles(auditContext, securityContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.GET_USER_ROLES_BY_SECURITY_USER_ID, method = RequestMethod.POST)
  public ObjectListResponse<UserRoleDTO> getUserRolesBySecurityUserId(
      @RequestBody UserRoleListRetrievalRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.getUserRolesBySecurityUserId(auditContext, securityContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.COUNT, method = RequestMethod.GET)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveCount() {
    long count = service.getActiveCount();
    return new CustomResponseEntity<ApiResponseObject<?>>(new ApiResponseObject<Long>(HttpStatus.OK, count),
        HttpStatus.OK);
  }

}
