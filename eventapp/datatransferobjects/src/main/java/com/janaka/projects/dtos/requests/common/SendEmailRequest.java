package com.janaka.projects.dtos.requests.common;

import java.io.Serializable;
import java.util.List;

import com.janaka.projects.common.util.NotificationRecipient;

public class SendEmailRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String title;

  private String emailSubject;

  private String bodyText;

  private String language;

  private int emailType;

  private List<NotificationRecipient> recepients;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getEmailSubject() {
    return emailSubject;
  }

  public void setEmailSubject(String emailSubject) {
    this.emailSubject = emailSubject;
  }

  public String getBodyText() {
    return bodyText;
  }

  public void setBodyText(String bodyText) {
    this.bodyText = bodyText;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public int getEmailType() {
    return emailType;
  }

  public void setEmailType(int emailType) {
    this.emailType = emailType;
  }

  public List<NotificationRecipient> getRecepients() {
    return recepients;
  }

  public void setRecepients(List<NotificationRecipient> recepients) {
    this.recepients = recepients;
  }



}
