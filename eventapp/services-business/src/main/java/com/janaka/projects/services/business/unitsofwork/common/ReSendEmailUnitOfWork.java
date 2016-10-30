package com.janaka.projects.services.business.unitsofwork.common;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.util.MailUtil;
import com.janaka.projects.common.util.NotificationSentStatus;
import com.janaka.projects.entitymanagement.dataaccessobjects.common.EmailNotificationRepository;
import com.janaka.projects.entitymanagement.domain.common.EmailNotification;
import com.janaka.projects.entitymanagement.enums.YesNoStatus;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;

public class ReSendEmailUnitOfWork extends UnitOfWork {

  private MailUtil mailUtil;

  private EmailNotificationRepository emailNotificationRepository;

  private List<EmailNotification> emailNotifications;

  @Override
  protected void preExecute() {
    emailNotifications = emailNotificationRepository.findBySentStatusAndRetryCountLessThanEqual(YesNoStatus.NO, 3);
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
          emailNotification.setRetryCount(emailNotification.getRetryCount() + 1);
        }
        emailNotificationRepository.save(emailNotification);
      }
    }
  }



  public ReSendEmailUnitOfWork(MailUtil mailUtil, EmailNotificationRepository emailNotificationRepository,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.mailUtil = mailUtil;
    this.emailNotificationRepository = emailNotificationRepository;
  }



}
