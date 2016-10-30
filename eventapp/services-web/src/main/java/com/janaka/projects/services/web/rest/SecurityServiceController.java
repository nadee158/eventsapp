package com.janaka.projects.services.web.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.SecurityHelper;
import com.janaka.projects.dtos.requests.common.ChangePasswordRequest;
import com.janaka.projects.dtos.requests.common.ResetPasswordRequest;
import com.janaka.projects.dtos.requests.common.SignOutRequest;
import com.janaka.projects.dtos.requests.common.SignUpRequest;
import com.janaka.projects.dtos.responses.common.ChangePasswordReponse;
import com.janaka.projects.dtos.responses.common.ResetPasswordReponse;
import com.janaka.projects.dtos.responses.common.SignOutResponse;
import com.janaka.projects.dtos.responses.common.SignUpReponse;
import com.janaka.projects.services.common.SecurityService;

@RestController
@RequestMapping(ServiceEndpoints.SECURITY)
public class SecurityServiceController {

  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private SecurityService service = null;


  @RequestMapping(ServiceEndpoints.SIGN_OUT)
  public SignOutResponse signOut(@RequestBody SignOutRequest request) {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();
    return service.signOut(securityContext, auditContext, request);
  }


  @RequestMapping(ServiceEndpoints.SIGN_UP)
  public SignUpReponse signUp(@RequestBody SignUpRequest request) {
    SignUpReponse response = null;
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    response = this.service.signUp(securityContext, auditContext, request);
    return response;
  }

  @RequestMapping(ServiceEndpoints.RESET_PASSWORD)
  public ResetPasswordReponse resetPassword(@RequestBody ResetPasswordRequest request) {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    return this.service.resetPassword(securityContext, auditContext, request);
  }

  @RequestMapping(ServiceEndpoints.CHANGE_PASSWORD)
  public ChangePasswordReponse changePassword(@RequestBody ChangePasswordRequest request) {
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    return this.service.changePassword(securityContext, auditContext, request);
  }


}
