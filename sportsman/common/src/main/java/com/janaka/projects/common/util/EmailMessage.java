package com.janaka.projects.common.util;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.common.constant.ApplicationConstants;

public class EmailMessage {

  private static final String propertyFileName = "application";

  private String title;

  private String emailSubject;

  private NotificationRecipient recipient;

  private String bodyText;

  private String addressing;

  private String signature;

  private String footerNote;

  private String language;

  private String recieverName;


  public EmailMessage(String title, String emailSubject, NotificationRecipient recipient, String bodyText,
      String language) {
    super();
    this.title = title;
    this.emailSubject = emailSubject;
    this.recipient = recipient;
    this.bodyText = bodyText;
    this.language = language;

    if (StringUtils.equals(language, ApplicationConstants.SINHALA)) {
      this.signature = CommonUtil.getValueFromFile(propertyFileName, "mail.signature.si");
      this.footerNote = CommonUtil.getValueFromFile(propertyFileName, "mail.footernote.si");
      this.addressing = CommonUtil.getValueFromFile(propertyFileName, "mail.addressing.si");

    } else if (StringUtils.equals(language, ApplicationConstants.TAMIL)) {
      this.signature = CommonUtil.getValueFromFile(propertyFileName, "mail.signature.ta");
      this.footerNote = CommonUtil.getValueFromFile(propertyFileName, "mail.footernote.ta");
      this.addressing = CommonUtil.getValueFromFile(propertyFileName, "mail.addressing.ta");

    } else {
      this.signature = CommonUtil.getValueFromFile(propertyFileName, "mail.signature.en");
      this.footerNote = CommonUtil.getValueFromFile(propertyFileName, "mail.footernote.en");
      this.addressing = CommonUtil.getValueFromFile(propertyFileName, "mail.addressing.en");
    }
  }

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

  public String getSignature() {
    return signature;
  }

  public String getFooterNote() {
    return footerNote;
  }

  public String getLanguage() {
    return language;
  }

  public String getAddressing() {
    return addressing;
  }

  public String getRecieverName() {
    return recieverName;
  }

  public void setRecieverName(String recieverName) {
    this.recieverName = recieverName;
  }

  public NotificationRecipient getRecipient() {
    return recipient;
  }

  public void setRecipient(NotificationRecipient recipient) {
    this.recipient = recipient;
  }



}
