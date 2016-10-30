package com.janaka.projects.services.common;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.dtos.requests.common.SendEmailRequest;
import com.janaka.projects.dtos.requests.common.SendSMSRequest;
import com.janaka.projects.dtos.responses.common.SendEmailResponse;
import com.janaka.projects.dtos.responses.common.SendSMSResponse;

public interface NotificationService {


  public SendEmailResponse sendEmail(SendEmailRequest request, AuditContext auditContext,
      SecurityContext securityContext);

  public void reSendEmails();

  public SendSMSResponse sendSMS(SendSMSRequest request, AuditContext auditContext, SecurityContext securityContext);

  public void reSendSmses();

}
