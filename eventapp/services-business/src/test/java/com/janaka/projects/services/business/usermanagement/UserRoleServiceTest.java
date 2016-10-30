package com.janaka.projects.services.business.usermanagement;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.jpa.datatables.parameter.SearchParameter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.SecurityHelper;
import com.janaka.projects.dtos.domain.usermanagement.UserRoleDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.usermanagement.UserRoleCreationRequest;
import com.janaka.projects.dtos.requests.usermanagement.UserRoleListRetrievalRequest;
import com.janaka.projects.dtos.requests.usermanagement.UserRoleUpdateRequest;
import com.janaka.projects.dtos.requests.usermanagement.mock.MockObjectDeletionRequestUtil;
import com.janaka.projects.dtos.requests.usermanagement.mock.MockObjectRetrievalRequestUtil;
import com.janaka.projects.dtos.requests.usermanagement.mock.MockUserRoleUtil;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.usermanagement.UserRoleCreationResponse;
import com.janaka.projects.dtos.responses.usermanagement.UserRoleUpdateResponse;
import com.janaka.projects.services.test.Application;
import com.janaka.projects.services.test.RepositoryConfiguration;
import com.janaka.projects.services.usermanagement.UserRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, RepositoryConfiguration.class})
public class UserRoleServiceTest {

  @Autowired
  private UserRoleService userRoleService;

  // @Test
  public void testCreateUserRole() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    UserRoleCreationRequest request = MockUserRoleUtil.getCreateUserRoleRequest();
    UserRoleCreationResponse response = userRoleService.createUserRole(auditContext, securityContext, request);
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getUserRoleDTO());
    Assert.assertNotEquals(response.getId(), 0);
  }

  // @Test
  public void testUpdateUserRole() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    UserRoleUpdateRequest request = MockUserRoleUtil.getUserRoleUpdateRequest();
    UserRoleUpdateResponse response = userRoleService.updateUserRole(auditContext, securityContext, request);
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getUserRoleDTO());
    Assert.assertNotEquals(response.getId(), 0);
  }

  // @Test
  public void testDeleteUserRole() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    ObjectDeletionRequest request = MockObjectDeletionRequestUtil.getObjectDeletionRequest();
    ObjectDeletionResponse response = userRoleService.deleteUserRole(auditContext, securityContext, request);
    Assert.assertNotNull(response);
    Assert.assertNotEquals(response.getId(), 0);
  }

  // @Test
  public void testListAllActiveUserRoles() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    ObjectListResponse<UserRoleDTO> response = userRoleService.getActiveUserRoles(auditContext, securityContext);
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getDtoList());
    Assert.assertNotEquals(response.getDtoList().size(), 0);
  }

  // @Test
  public void testGetUserRoleById() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    ObjectRetrievalRequest request = MockObjectRetrievalRequestUtil.getObjectRetrievalRequest();
    ObjectRetrievalResponse<UserRoleDTO> response =
        userRoleService.getUserRoleById(auditContext, securityContext, request);
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getDto());
    System.out.println("response.getApplicationDTO() " + response.getDto());
  }

  // @Test
  public void testGetUserRoles() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    TabularDataRequestModel request = new TabularDataRequestModel();
    request.setDraw(1);
    request.setLength(2);
    request.setSearch(new SearchParameter("Te", false));
    TabularDataResponseModel<UserRoleDTO> response =
        userRoleService.getUserRoles(auditContext, securityContext, request);
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getData());
    Assert.assertEquals(response.getDraw(), 1);
  }

  @Test
  public void testGetUserRolesBySecurityUserId() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    UserRoleListRetrievalRequest request = new UserRoleListRetrievalRequest();
    request.setSecurityUserId(1);
    ObjectListResponse<UserRoleDTO> response =
        userRoleService.getUserRolesBySecurityUserId(auditContext, securityContext, request);
    System.out.println("response :" + response);
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getDtoList());
    Assert.assertNotEquals(response.getDtoList().size(), 0);
  }

}
