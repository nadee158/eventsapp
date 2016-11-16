package com.janaka.projects.services.business.usermanagement;

import java.util.List;

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
import com.janaka.projects.dtos.domain.usermanagement.SecurityUserDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserCreationRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserUpdateRequest;
import com.janaka.projects.dtos.requests.usermanagement.mock.MockObjectDeletionRequestUtil;
import com.janaka.projects.dtos.requests.usermanagement.mock.MockObjectRetrievalRequestUtil;
import com.janaka.projects.dtos.requests.usermanagement.mock.MockSecurityUserServiceUtil;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.usermanagement.SecurityUserCreationResponse;
import com.janaka.projects.dtos.responses.usermanagement.SecurityUserUpdateResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.services.test.Application;
import com.janaka.projects.services.test.RepositoryConfiguration;
import com.janaka.projects.services.usermanagement.SecurityUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, RepositoryConfiguration.class})
public class SecurityUserServiceTest {

  @Autowired
  private SecurityUserRepository securityUserRepository;

  @Autowired
  private SecurityUserService securityUserService;

  @Test
  public void testCreateSecurityUser() {
    SecurityUserCreationRequest request = MockSecurityUserServiceUtil.getSecurityUserCreationRequest();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    SecurityUserCreationResponse response =
        securityUserService.createSecurityUser(auditContext, securityContext, request);
    Assert.assertNotNull(response);
    System.out.println(response.getMessage());
    Assert.assertNotNull(response.getSecurityUserDTO());
    Assert.assertNotEquals(response.getId(), 0);
  }

  // @Test
  public void testUpdateSecurityUser() {
    SecurityUserUpdateRequest request = MockSecurityUserServiceUtil.getSecurityUserUpdateRequest();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    SecurityUserUpdateResponse response =
        securityUserService.updateSecurityUser(auditContext, securityContext, request);
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getSecurityUserDTO());
    Assert.assertNotEquals(response.getId(), 0);
  }

  // @Test
  public void testDeleteSecurityUser() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    ObjectDeletionRequest request = MockObjectDeletionRequestUtil.getObjectDeletionRequest();
    ObjectDeletionResponse response = securityUserService.deleteSecurityUser(auditContext, securityContext, request);
    Assert.assertNotNull(response);
    Assert.assertNotEquals(response.getId(), 0);
  }

  // @Test
  public void testGetActiveSecurityUsers() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    ObjectListResponse<SecurityUserDTO> response =
        securityUserService.getActiveSecurityUsers(auditContext, securityContext);
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getDtoList());
    Assert.assertNotEquals(response.getListSize(), 0);
  }

  // @Test
  public void testGetSecurityUserById() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    ObjectRetrievalRequest request = MockObjectRetrievalRequestUtil.getObjectRetrievalRequest();
    ObjectRetrievalResponse<SecurityUserDTO> response =
        securityUserService.getSecurityUserById(auditContext, securityContext, request);
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getDto());
    System.out.println("response.getApplicationDTO() " + response.getDto());
  }

  // @Test
  public void testGetSecurityUsers() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    TabularDataRequestModel request = new TabularDataRequestModel();
    request.setDraw(1);
    request.setLength(2);
    request.setSearch(new SearchParameter("Te", false));
    TabularDataResponseModel<SecurityUserDTO> response =
        securityUserService.getSecurityUsers(auditContext, securityContext, request);
    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getData());
    Assert.assertEquals(response.getDraw(), 1);
  }

  // @Test
  public void testRepo() {
    String userName = "";
    String nic = "865480432v";
    List<SecurityUser> list = securityUserRepository.findByUserNameOrPersonNic(userName, nic);
    for (SecurityUser securityUser : list) {
      System.out.println("securityUser.getPerson().getNic() : " + securityUser.getPerson().getNic());
      System.out.println("securityUser.getUserName() : " + securityUser.getUserName());
    }
    System.out.println("list : " + list);

  }


}
