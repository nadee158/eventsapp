package com.janaka.projects.services.business.unitsofwork.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.User;
import com.janaka.projects.common.util.NotificationRecipient;
import com.janaka.projects.common.util.SMSUtil;
import com.janaka.projects.dtos.requests.common.GetSessionDetailsRequest;
import com.janaka.projects.dtos.requests.common.SendSMSRequest;
import com.janaka.projects.dtos.responses.common.GetSessionDetailsResponse;
import com.janaka.projects.dtos.responses.common.SendSMSResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.common.SMSNotificationRepository;
import com.janaka.projects.entitymanagement.domain.common.SMSNotification;
import com.janaka.projects.entitymanagement.enums.Language;
import com.janaka.projects.entitymanagement.enums.SMSType;
import com.janaka.projects.entitymanagement.enums.YesNoStatus;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.common.SecurityService;

public class SendSMSUnitOfWork extends UnitOfWork {

  private SendSMSRequest request;

  private SendSMSResponse response;

  private SMSUtil smsUtil;

  private SMSNotificationRepository smsNotificationRepository;

  private AuditContext auditContext;

  private SecurityContext securityContext;

  private SecurityService securityService = null;

  private User userFromSession = null;

  private List<SMSNotification> smsNotifications;

  private boolean hasSent = false;

  @Override
  protected void preExecute() {
    if (!(securityContext == null || securityContext.getToken() == null)) {
      GetSessionDetailsRequest getSessionDetailsRequest = new GetSessionDetailsRequest();
      getSessionDetailsRequest.setToken(securityContext.getToken());
      GetSessionDetailsResponse getSessionDetailsResponse = securityService.getSessionDetails(getSessionDetailsRequest);
      if (!(getSessionDetailsResponse == null)) {
        userFromSession = getSessionDetailsResponse.getUser();
      }
      super.preExecute();
    }

    if (!(request == null || request.getRecepients() == null || request.getRecepients().isEmpty())) {
      smsNotifications = new ArrayList<SMSNotification>();
      for (NotificationRecipient notificationRecipient : request.getRecepients()) {
        SMSNotification smsNotification = new SMSNotification();
        if (!(userFromSession == null)) {
          smsNotification.setCreatedByUser(userFromSession.getSecurityUserId().toString());
        }
        smsNotification.setDeleted(false);
        smsNotification.setLanguage(Language.fromLangCode(request.getLanguage()));
        smsNotification.setRecipientAddress(notificationRecipient.getRecipientAddress());
        smsNotification.setRecipientName(notificationRecipient.getRecipientName());
        smsNotification.setText(request.getSms());
        smsNotification.setSmsType(SMSType.fromCode(request.getSmsType()));
        smsNotifications.add(smsNotification);
      }
    }
  }

  @Override
  protected void doWork() {
    if (!(smsNotifications == null || smsNotifications.isEmpty())) {
      for (SMSNotification smsNotification : smsNotifications) {
        try {
          String status = smsUtil.sendSMS(smsNotification.getText(), smsNotification.getRecipientAddress());
          if (StringUtils.equals(status, ApplicationConstants.SUCCESS)) {
            smsNotification.setSentStatus(YesNoStatus.YES);
            hasSent = true;
          } else {
            smsNotification.setSentStatus(YesNoStatus.NO);
          }
        } catch (IOException e) {
          smsNotification.setException(e.getClass().getSimpleName());
          smsNotification.setExceptionMesssage(e.getMessage());
          e.printStackTrace();
        } catch (ParserConfigurationException e) {
          smsNotification.setException(e.getClass().getSimpleName());
          smsNotification.setExceptionMesssage(e.getMessage());
          e.printStackTrace();
        } catch (SAXException e) {
          smsNotification.setException(e.getClass().getSimpleName());
          smsNotification.setExceptionMesssage(e.getMessage());
          e.printStackTrace();
        } finally {
          smsNotificationRepository.save(smsNotification);
        }
      }
    }

  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    response = new SendSMSResponse();
    if (isSuccessful && hasSent) {
      response.setMessage(ApplicationConstants.SUBMITTED_FOR_DELIVERY);
      response.setStatus(YesNoStatus.YES.getCode());
    } else {
      response.setMessage(ApplicationConstants.ERROR);
      response.setStatus(YesNoStatus.NO.getCode());
    }
    super.postExecute(isSuccessful);
  }

  public SendSMSUnitOfWork(SendSMSRequest request, SMSUtil smsUtil, SMSNotificationRepository smsNotificationRepository,
      AuditContext auditContext, SecurityContext securityContext, SecurityService securityService,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.request = request;
    this.smsUtil = smsUtil;
    this.smsNotificationRepository = smsNotificationRepository;
    this.auditContext = auditContext;
    this.securityContext = securityContext;
    this.securityService = securityService;
  }

  public SendSMSResponse getResponse() {
    return response;
  }



}
