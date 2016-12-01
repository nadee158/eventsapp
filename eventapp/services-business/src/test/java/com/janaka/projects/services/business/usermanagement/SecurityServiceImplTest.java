package com.janaka.projects.services.business.usermanagement;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.SecurityHelper;
import com.janaka.projects.dtos.requests.common.ChangePasswordRequest;
import com.janaka.projects.dtos.requests.common.ResetPasswordRequest;
import com.janaka.projects.dtos.responses.common.ChangePasswordReponse;
import com.janaka.projects.dtos.responses.common.ResetPasswordReponse;
import com.janaka.projects.entitymanagement.enums.Language;
import com.janaka.projects.services.common.SecurityService;
import com.janaka.projects.services.test.Application;
import com.janaka.projects.services.test.RepositoryConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class, RepositoryConfiguration.class})
public class SecurityServiceImplTest {

  @Autowired
  private SecurityService securityService;


  // @Test
  public void testSignIn() {
    fail("Not yet implemented");
  }

  // @Test
  public void testSignOut() {
    fail("Not yet implemented");
  }

  // @Test
  public void testSignUp() {
    fail("Not yet implemented");
  }

  // @Test
  public void testResetPassword() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    ResetPasswordRequest request = new ResetPasswordRequest();
    request.setEmail("nadee158@gmail.com");
    request.setLanguage("si");
    request.setUserName("Nadee1582");
    ResetPasswordReponse response = securityService.resetPassword(securityContext, auditContext, request);
    System.out.println("response.getMessage(): " + response.getMessage());
    Assert.assertEquals(ApplicationConstants.STATUS_CODE_OK, response.getStatus());
  }

  // @Test
  public void testUpdateProfile() {
    fail("Not yet implemented");
  }

  @Test
  public void testChangePassword() {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    ChangePasswordRequest request = new ChangePasswordRequest();
    request.setConfirmSecret("abc@1231");
    request.setSecret("umgtadmin@123");
    request.setNewSecret("abc@1231");
    request.setLanguage(Language.ENGLISH.getLangCode());
    request.setUserId(5);
    ChangePasswordReponse response = securityService.changePassword(securityContext, auditContext, request);
    System.out.println("response.getMessage(): " + response.getMessage());
    Assert.assertEquals(ApplicationConstants.STATUS_CODE_OK, response.getStatus());

  }



}
