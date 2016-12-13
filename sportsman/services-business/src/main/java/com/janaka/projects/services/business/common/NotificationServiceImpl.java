package com.janaka.projects.services.business.common;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.util.MailUtil;
import com.janaka.projects.common.util.SMSUtil;
import com.janaka.projects.dtos.requests.common.SendEmailRequest;
import com.janaka.projects.dtos.requests.common.SendSMSRequest;
import com.janaka.projects.dtos.responses.common.SendEmailResponse;
import com.janaka.projects.dtos.responses.common.SendSMSResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.common.EmailNotificationRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.common.SMSNotificationRepository;
import com.janaka.projects.services.business.unitsofwork.common.ReSendEmailUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.common.ReSendSMSUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.common.SendEmailUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.common.SendSMSUnitOfWork;
import com.janaka.projects.services.common.NotificationService;

@Service(value = "notificationService")
@Transactional()
public class NotificationServiceImpl extends BusinessService implements NotificationService {

  @Autowired
  private MailUtil mailUtil;

  @Autowired
  private EmailNotificationRepository emailNotificationRepository;

  @Autowired
  private SMSUtil smsUtil;

  @Autowired
  private SMSNotificationRepository smsNotificationRepository;

  @Autowired
  private JmxNotificationPublisher jmxNotificationPublisher;

  @Override
  public SendEmailResponse sendEmail(SendEmailRequest request, AuditContext auditContext,
      SecurityContext securityContext) {
    SendEmailUnitOfWork uow = new SendEmailUnitOfWork(request, mailUtil, emailNotificationRepository, auditContext,
        securityContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getEmailResponse();
  }

  @Scheduled(cron = "0 0 * * * *")
  @Override
  public void reSendEmails() {
    ReSendEmailUnitOfWork uow =
        new ReSendEmailUnitOfWork(mailUtil, emailNotificationRepository, jmxNotificationPublisher);
    this.doWork(uow);
  }

  @Override
  public SendSMSResponse sendSMS(SendSMSRequest request, AuditContext auditContext, SecurityContext securityContext) {
    SendSMSUnitOfWork uow = new SendSMSUnitOfWork(request, smsUtil, smsNotificationRepository, auditContext,
        securityContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }


  @Scheduled(cron = "1 0 * * * *")
  @Override
  public void reSendSmses() {
    ReSendSMSUnitOfWork uow = new ReSendSMSUnitOfWork(smsUtil, smsNotificationRepository, jmxNotificationPublisher);
    this.doWork(uow);

  }



}
