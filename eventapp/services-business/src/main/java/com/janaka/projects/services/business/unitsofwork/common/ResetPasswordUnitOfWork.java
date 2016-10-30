package com.janaka.projects.services.business.unitsofwork.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.util.CommonUtil;
import com.janaka.projects.common.util.NotificationRecipient;
import com.janaka.projects.dtos.requests.common.ResetPasswordRequest;
import com.janaka.projects.dtos.requests.common.SendEmailRequest;
import com.janaka.projects.dtos.requests.common.SendSMSRequest;
import com.janaka.projects.dtos.responses.common.ResetPasswordReponse;
import com.janaka.projects.dtos.responses.common.SendEmailResponse;
import com.janaka.projects.dtos.responses.common.SendSMSResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.entitymanagement.enums.EmailType;
import com.janaka.projects.entitymanagement.enums.Language;
import com.janaka.projects.entitymanagement.enums.SMSType;
import com.janaka.projects.entitymanagement.enums.YesNoStatus;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.common.NotificationService;

public final class ResetPasswordUnitOfWork extends UnitOfWork {

  private SecurityUserRepository repository = null;

  private ResetPasswordRequest request = null;
  private ResetPasswordReponse response = null;

  private SecurityContext securityContext = null;
  private AuditContext auditContext = null;

  private SecurityUser securityUser = null;

  private boolean isSuccess = false;

  private NotificationService notificationService;

  private String filePath = "global_";

  private String language = Language.ENGLISH.getLangCode();

  private String email_title = "reset.password.email.title";
  private String email_subject = "reset.password.email.subject";
  private String email_body = "reset.password.email.body";

  private String sms = "reset.password.sms";


  protected void preExecute() {
    response = new ResetPasswordReponse();
    language = request.getLanguage();
    filePath = filePath + language;
    if (!(StringUtils.isBlank(request.getUserName()) || StringUtils.isBlank(request.getEmail()))) {
      securityUser = repository.findByUserName(this.request.getUserName());
      if (!(securityUser == null || securityUser.getPerson() == null)) {
        if (StringUtils.equals(securityUser.getPerson().getEmail(), request.getEmail())) {
          isSuccess = true;
        } else {
          response.setStatus(ApplicationConstants.STATUS_CODE_CONFLICT);
          response.setMessage("No user exists for the given username and email!");
        }
      } else {
        response.setStatus(ApplicationConstants.STATUS_CODE_NOT_FOUND);
        response.setMessage("No user exists for the given username and email!");
      }
    } else {
      response.setStatus(ApplicationConstants.STATUS_CODE_NO_CONTENT);
      response.setMessage("Please enter the user name and emil!");
    }
  }

  @Override
  @Transactional(readOnly = false)
  protected void doWork() {
    if (isSuccess) {
      isSuccess = false;
      String newPassword = RandomStringUtils.random(10, true, true);
      System.out.println("newPassword :" + newPassword);
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      String encPassword = bCryptPasswordEncoder.encode(newPassword);
      System.out.println("encPassword :" + encPassword);
      securityUser.setSecret(encPassword);

      SendEmailResponse emailResponse = sendEmail(newPassword);
      if (emailResponse.getStatus() == ApplicationConstants.STATUS_CODE_OK) {
        SecurityUser updatedUser = repository.save(securityUser);
        if (bCryptPasswordEncoder.matches(newPassword, updatedUser.getSecret())) {
          isSuccess = true;
        }
        response.setEmailSentStatus(YesNoStatus.YES.getCode());
      }

      if (StringUtils.isNotEmpty(securityUser.getPerson().getMobileNumber())) {
        SendSMSResponse sendSMSResponse = sendSMS();
        if (sendSMSResponse.getStatus() == ApplicationConstants.STATUS_CODE_OK) {
          response.setSmsSentStatus(YesNoStatus.YES.getCode());
        }
      }


    }

  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    if (isSuccessful) {
      if (isSuccess) {
        response.setStatus(ApplicationConstants.STATUS_CODE_OK);
        response.setMessage("Password was reset successfully!");
      }
    } else {
      response.setStatus(ApplicationConstants.STATUS_CODE_INTERNAL_SERVER_ERROR);
      response.setMessage("Could not reset the password!");
    }
  }

  private SendSMSResponse sendSMS() {
    List<NotificationRecipient> recepients = new ArrayList<NotificationRecipient>();
    NotificationRecipient recipient = new NotificationRecipient(securityUser.getPerson().getFullName(),
        securityUser.getPerson().getMobileNumber(), 1);
    recepients.add(recipient);
    SendSMSRequest sendSMSRequest = new SendSMSRequest();
    sendSMSRequest.setLanguage(language);
    sendSMSRequest.setRecepients(recepients);
    sendSMSRequest.setSms(CommonUtil.getValueFromFile(filePath, sms));
    sendSMSRequest.setSmsType(SMSType.RESET_PASSWORD.getCode());
    return notificationService.sendSMS(sendSMSRequest, auditContext, securityContext);
  }

  private SendEmailResponse sendEmail(String newRawPassword) {
    SendEmailRequest sendEmailRequest = new SendEmailRequest();
    String bodyText = CommonUtil.getValueFromFile(filePath, email_body);
    bodyText = bodyText + "<br /> <b>" + newRawPassword + "</b>";
    sendEmailRequest.setBodyText(bodyText);
    sendEmailRequest.setEmailSubject(CommonUtil.getValueFromFile(filePath, email_subject));
    sendEmailRequest.setEmailType(EmailType.RESET_PASSWORD.getCode());
    sendEmailRequest.setLanguage(request.getLanguage());
    List<NotificationRecipient> recepients = new ArrayList<NotificationRecipient>();
    NotificationRecipient recipient =
        new NotificationRecipient(securityUser.getPerson().getFullName(), securityUser.getPerson().getEmail(), 1);
    recepients.add(recipient);
    sendEmailRequest.setRecepients(recepients);
    sendEmailRequest.setTitle(CommonUtil.getValueFromFile(filePath, email_title));
    return notificationService.sendEmail(sendEmailRequest, auditContext, securityContext);
  }



  public ResetPasswordReponse getResponse() {
    return response;
  }

  public ResetPasswordUnitOfWork(NotificationService notificationService, SecurityUserRepository repository,
      ResetPasswordRequest request, SecurityContext securityContext, AuditContext auditContext,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.repository = repository;
    this.request = request;
    this.securityContext = securityContext;
    this.auditContext = auditContext;
    this.notificationService = notificationService;
  }



}
