package com.janaka.projects.services.common;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.Session;
import com.janaka.projects.dtos.requests.common.ChangePasswordRequest;
import com.janaka.projects.dtos.requests.common.GetSessionDetailsRequest;
import com.janaka.projects.dtos.requests.common.ResetPasswordRequest;
import com.janaka.projects.dtos.requests.common.SaveSessionDetailsRequest;
import com.janaka.projects.dtos.requests.common.SignOutRequest;
import com.janaka.projects.dtos.requests.common.SignUpRequest;
import com.janaka.projects.dtos.responses.common.ChangePasswordReponse;
import com.janaka.projects.dtos.responses.common.GetSessionDetailsResponse;
import com.janaka.projects.dtos.responses.common.ResetPasswordReponse;
import com.janaka.projects.dtos.responses.common.SaveSessionDetailsResponse;
import com.janaka.projects.dtos.responses.common.SignOutResponse;
import com.janaka.projects.dtos.responses.common.SignUpReponse;

public interface SecurityService extends UserDetailsService {

  SignOutResponse signOut(SecurityContext securityContext, AuditContext auditContext, SignOutRequest request);

  SignUpReponse signUp(SecurityContext securityContext, AuditContext auditContext, SignUpRequest request);

  ResetPasswordReponse resetPassword(SecurityContext securityContext, AuditContext auditContext,
      ResetPasswordRequest request);

  Session ensureSessionValidity(SecurityContext securityContext, AuditContext auditContext);

  SaveSessionDetailsResponse saveSessionDetails(SaveSessionDetailsRequest saveSessionDetailsRequest);

  GetSessionDetailsResponse getSessionDetails(GetSessionDetailsRequest getSessionDetailsRequest);

  ChangePasswordReponse changePassword(SecurityContext securityContext, AuditContext auditContext,
      ChangePasswordRequest request);

}
