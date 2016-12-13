package com.janaka.projects.services.business.unitsofwork.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.util.MailUtil;
import com.janaka.projects.common.util.NotificationRecipient;
import com.janaka.projects.common.util.NotificationSentStatus;
import com.janaka.projects.dtos.requests.common.SendEmailRequest;
import com.janaka.projects.dtos.responses.common.SendEmailResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.common.EmailNotificationRepository;
import com.janaka.projects.entitymanagement.domain.common.EmailNotification;
import com.janaka.projects.entitymanagement.enums.EmailType;
import com.janaka.projects.entitymanagement.enums.Language;
import com.janaka.projects.entitymanagement.enums.YesNoStatus;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;

public class SendEmailUnitOfWork extends UnitOfWork {

  private SendEmailRequest emailRequest;

  private SendEmailResponse emailResponse;

  private MailUtil mailUtil;

  private EmailNotificationRepository emailNotificationRepository;

  private AuditContext auditContext;

  private SecurityContext securityContext;

  private List<EmailNotification> emailNotifications;

  @Override
  protected void preExecute() {

    if (!(emailRequest == null || emailRequest.getRecepients() == null || emailRequest.getRecepients().isEmpty())) {
      emailNotifications = new ArrayList<EmailNotification>();
      for (NotificationRecipient notificationRecipient : emailRequest.getRecepients()) {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setBodyText(emailRequest.getBodyText());
        emailNotification.setEmailSubject(emailRequest.getEmailSubject());
        emailNotification.setEmailType(EmailType.fromCode(emailRequest.getEmailType()));
        emailNotification.setLanguage(Language.fromLangCode(emailRequest.getLanguage()));
        emailNotification.setRecipientAddress(notificationRecipient.getRecipientAddress());
        emailNotification.setRecipientName(notificationRecipient.getRecipientName());
        emailNotification.setTitle(emailRequest.getTitle());
        emailNotifications.add(emailNotification);
      }
    }
    super.preExecute();
  }

  @Async
  @Override
  protected void doWork() {
    if (!(emailNotifications == null || emailNotifications.isEmpty())) {
      for (EmailNotification emailNotification : emailNotifications) {
        NotificationSentStatus status = mailUtil.sendEmail(emailNotification.constructEmailMessage());
        if (StringUtils.equals(status.getStatus(), ApplicationConstants.SUCCESS)) {
          emailNotification.setSentStatus(YesNoStatus.YES);
        } else {
          emailNotification.setSentStatus(YesNoStatus.NO);
          if (!(status.getException() == null)) {
            emailNotification.setException(status.getException().getClass().getSimpleName());
          }
          emailNotification.setExceptionMesssage(status.getExceptionMesssage());
        }
        emailNotificationRepository.save(emailNotification);
      }
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    emailResponse = new SendEmailResponse();
    if (isSuccessful) {
      emailResponse.setMessage(ApplicationConstants.SUBMITTED_FOR_DELIVERY);
      emailResponse.setStatus(ApplicationConstants.STATUS_CODE_OK);
    } else {
      emailResponse.setMessage(ApplicationConstants.ERROR);
      emailResponse.setStatus(ApplicationConstants.STATUS_CODE_INTERNAL_SERVER_ERROR);
    }
  }


  public SendEmailUnitOfWork(SendEmailRequest emailRequest, MailUtil mailUtil,
      EmailNotificationRepository emailNotificationRepository, AuditContext auditContext,
      SecurityContext securityContext, JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.emailRequest = emailRequest;
    this.mailUtil = mailUtil;
    this.emailNotificationRepository = emailNotificationRepository;
    this.auditContext = auditContext;
    this.securityContext = securityContext;
  }

  public SendEmailResponse getEmailResponse() {
    return emailResponse;
  }



}
