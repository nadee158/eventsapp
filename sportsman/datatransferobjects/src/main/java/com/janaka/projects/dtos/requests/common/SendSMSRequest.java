package com.janaka.projects.dtos.requests.common;

import java.io.Serializable;
import java.util.List;

import com.janaka.projects.common.util.NotificationRecipient;

public class SendSMSRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String sms;

  private List<NotificationRecipient> recepients;

  private int smsType;

  private String language;

  public String getSms() {
    return sms;
  }

  public void setSms(String sms) {
    this.sms = sms;
  }

  public int getSmsType() {
    return smsType;
  }

  public void setSmsType(int smsType) {
    this.smsType = smsType;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public List<NotificationRecipient> getRecepients() {
    return recepients;
  }

  public void setRecepients(List<NotificationRecipient> recepients) {
    this.recepients = recepients;
  }



}
