package com.janaka.projects.services.business.unitsofwork.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.User;
import com.janaka.projects.common.util.CommonUtil;
import com.janaka.projects.common.util.NotificationRecipient;
import com.janaka.projects.dtos.requests.common.ChangePasswordRequest;
import com.janaka.projects.dtos.requests.common.GetSessionDetailsRequest;
import com.janaka.projects.dtos.requests.common.SendEmailRequest;
import com.janaka.projects.dtos.requests.common.SendSMSRequest;
import com.janaka.projects.dtos.responses.common.ChangePasswordReponse;
import com.janaka.projects.dtos.responses.common.GetSessionDetailsResponse;
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
import com.janaka.projects.services.common.SecurityService;

public final class ChangePasswordUnitOfWork extends UnitOfWork {

  private SecurityUserRepository repository = null;

  private SecurityService securityService = null;

  private ChangePasswordRequest request = null;
  private ChangePasswordReponse response = null;

  private SecurityContext securityContext = null;
  private AuditContext auditContext = null;

  private SecurityUser securityUser = null;

  private boolean isSuccess = false;

  private NotificationService notificationService;

  private String filePath = "global_";

  private String language = Language.ENGLISH.getLangCode();

  private String email_title = "change.password.email.title";
  private String email_subject = "change.password.email.subject";
  private String email_body = "change.password.email.body";

  private String sms = "change.password.sms";

  private User userFromSession = null;

  private BCryptPasswordEncoder bCryptPasswordEncoder = null;


  protected void preExecute() {
    bCryptPasswordEncoder = new BCryptPasswordEncoder();
    System.out.println("preExecute :");
    response = new ChangePasswordReponse();

    if (!(securityContext == null || securityContext.getToken() == null)) {
      GetSessionDetailsRequest getSessionDetailsRequest = new GetSessionDetailsRequest();
      getSessionDetailsRequest.setToken(securityContext.getToken());
      GetSessionDetailsResponse getSessionDetailsResponse = securityService.getSessionDetails(getSessionDetailsRequest);
      if (!(getSessionDetailsResponse == null)) {
        userFromSession = getSessionDetailsResponse.getUser();
      }
      super.preExecute();
    }


    language = request.getLanguage();
    filePath = filePath + language;

    if (!(userFromSession == null)) {

      if (!(StringUtils.isBlank(request.getSecret()) || StringUtils.isBlank(request.getConfirmSecret())
          || StringUtils.isBlank(request.getNewSecret()))) {

        if (userFromSession.getId() == request.getUserId()) {

          securityUser = repository.findOne(userFromSession.getId());

          if (!(securityUser == null || securityUser.getPerson() == null)) {
            if (bCryptPasswordEncoder.matches(request.getSecret(), securityUser.getSecret())) {
              if (StringUtils.equals(request.getNewSecret(), request.getConfirmSecret())) {
                isSuccess = true;
              } else {
                response.setStatus(ApplicationConstants.STATUS_CODE_CONFLICT);
                response.setMessage("The password and confirm password does not match!");
              }
            } else {
              response.setStatus(ApplicationConstants.STATUS_CODE_CONFLICT);
              response.setMessage("Incorrect password, please insert the correct current password!");
            }
          } else {
            response.setStatus(ApplicationConstants.STATUS_CODE_NOT_FOUND);
            response.setMessage("Invalid User, please login again!");
          }

        } else {
          response.setStatus(ApplicationConstants.STATUS_CODE_UNAUTHORIZED);
          response.setMessage("Your session has expired!");
        }

      } else {
        response.setStatus(ApplicationConstants.STATUS_CODE_NO_CONTENT);
        response.setMessage("Please enter all required details!");
      }

    } else {
      response.setStatus(ApplicationConstants.STATUS_CODE_UNAUTHORIZED);
      response.setMessage("Your session has expired!");
    }


  }

  @Override
  @Transactional(readOnly = false)
  protected void doWork() {
    if (isSuccess) {
      isSuccess = false;
      String newPassword = request.getNewSecret();
      System.out.println("newPassword :" + newPassword);
      String encPassword = new BCryptPasswordEncoder().encode(newPassword);
      System.out.println("encPassword :" + encPassword);
      securityUser.setSecret(encPassword);
      SecurityUser updatedUser = repository.save(securityUser);
      if (bCryptPasswordEncoder.matches(request.getNewSecret(), updatedUser.getSecret())) {
        isSuccess = true;
        SendEmailResponse emailResponse = sendEmail(newPassword);
        if (emailResponse.getStatus() == ApplicationConstants.STATUS_CODE_OK) {
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

  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    System.out.println("postExecute :" + response);
    if (isSuccessful) {
      if (isSuccess) {
        response.setStatus(ApplicationConstants.STATUS_CODE_OK);
        response.setMessage("Password was changed successfully!");
      }
    } else {
      response.setStatus(ApplicationConstants.STATUS_CODE_INTERNAL_SERVER_ERROR);
      response.setMessage("Could not change the password!");
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
    sendSMSRequest.setSmsType(SMSType.CHANGE_PASSWORD.getCode());
    return notificationService.sendSMS(sendSMSRequest, auditContext, securityContext);
  }

  private SendEmailResponse sendEmail(String newRawPassword) {
    SendEmailRequest sendEmailRequest = new SendEmailRequest();
    String bodyText = CommonUtil.getValueFromFile(filePath, email_body);
    sendEmailRequest.setBodyText(bodyText);
    sendEmailRequest.setEmailSubject(CommonUtil.getValueFromFile(filePath, email_subject));
    sendEmailRequest.setEmailType(EmailType.CHANGE_PASSWORD.getCode());
    sendEmailRequest.setLanguage(request.getLanguage());
    List<NotificationRecipient> recepients = new ArrayList<NotificationRecipient>();
    NotificationRecipient recipient =
        new NotificationRecipient(securityUser.getPerson().getFullName(), securityUser.getPerson().getEmail(), 1);
    recepients.add(recipient);
    sendEmailRequest.setRecepients(recepients);
    sendEmailRequest.setTitle(CommonUtil.getValueFromFile(filePath, email_title));
    return notificationService.sendEmail(sendEmailRequest, auditContext, securityContext);
  }


  public ChangePasswordReponse getResponse() {
    return response;
  }

  public ChangePasswordUnitOfWork(NotificationService notificationService, SecurityUserRepository repository,
      ChangePasswordRequest request, SecurityContext securityContext, AuditContext auditContext,
      SecurityService securityService, JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.securityService = securityService;
    this.repository = repository;
    this.request = request;
    this.securityContext = securityContext;
    this.auditContext = auditContext;
    this.notificationService = notificationService;
  }



}
