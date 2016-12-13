package com.janaka.projects.services.business.unitsofwork.common;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.util.SMSUtil;
import com.janaka.projects.entitymanagement.dataaccessobjects.common.SMSNotificationRepository;
import com.janaka.projects.entitymanagement.domain.common.SMSNotification;
import com.janaka.projects.entitymanagement.enums.YesNoStatus;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;

public class ReSendSMSUnitOfWork extends UnitOfWork {

  private SMSUtil smsUtil;

  private SMSNotificationRepository smsNotificationRepository;

  private List<SMSNotification> smsNotifications;

  @Override
  protected void preExecute() {
    this.smsNotifications = smsNotificationRepository.findBySentStatusAndRetryCountLessThanEqual(YesNoStatus.NO, 3);
  }

  @Override
  protected void doWork() {
    if (!(smsNotifications == null || smsNotifications.isEmpty())) {
      for (SMSNotification smsNotification : smsNotifications) {
        try {
          String status = smsUtil.sendSMS(smsNotification.getText(), smsNotification.getRecipientAddress());
          if (StringUtils.equals(status, ApplicationConstants.SUCCESS)) {
            smsNotification.setSentStatus(YesNoStatus.YES);
          } else {
            smsNotification.setRetryCount(smsNotification.getRetryCount() + 1);
            smsNotification.setSentStatus(YesNoStatus.NO);
          }
        } catch (IOException e) {
          smsNotification.setException(e.getClass().getSimpleName());
          smsNotification.setExceptionMesssage(e.getMessage());
          smsNotification.setRetryCount(smsNotification.getRetryCount() + 1);
          smsNotification.setSentStatus(YesNoStatus.NO);
          e.printStackTrace();
        } catch (ParserConfigurationException e) {
          smsNotification.setException(e.getClass().getSimpleName());
          smsNotification.setExceptionMesssage(e.getMessage());
          smsNotification.setRetryCount(smsNotification.getRetryCount() + 1);
          smsNotification.setSentStatus(YesNoStatus.NO);
          e.printStackTrace();
        } catch (SAXException e) {
          smsNotification.setException(e.getClass().getSimpleName());
          smsNotification.setExceptionMesssage(e.getMessage());
          smsNotification.setRetryCount(smsNotification.getRetryCount() + 1);
          smsNotification.setSentStatus(YesNoStatus.NO);
          e.printStackTrace();
        } finally {
          smsNotificationRepository.save(smsNotification);
        }
      }
    }

  }


  public ReSendSMSUnitOfWork(SMSUtil smsUtil, SMSNotificationRepository smsNotificationRepository,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.smsUtil = smsUtil;
    this.smsNotificationRepository = smsNotificationRepository;
  }



}
