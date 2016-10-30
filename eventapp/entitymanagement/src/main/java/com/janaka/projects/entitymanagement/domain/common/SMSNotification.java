package com.janaka.projects.entitymanagement.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.janaka.projects.entitymanagement.enums.Language;
import com.janaka.projects.entitymanagement.enums.SMSType;
import com.janaka.projects.entitymanagement.enums.YesNoStatus;

@Entity
@Table(name = "sms_notification")
public class SMSNotification extends BaseDomain {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id = 0;

  @Column(name = "text", length = 1000)
  private String text;

  @Enumerated(EnumType.STRING)
  @Column(name = "language")
  private Language language;

  @Enumerated(EnumType.STRING)
  @Column(name = "sms_type")
  private SMSType smsType;

  @Enumerated(EnumType.STRING)
  @Column(name = "sent_status")
  private YesNoStatus sentStatus;

  @Column(name = "retry_count")
  private int retryCount;

  @Column(name = "recipient_name")
  private String recipientName;

  @Column(name = "recipient_address")
  private String recipientAddress;

  @Version
  @Column(name = "version_id")
  private long versionId = 0;

  @Column(name = "exception")
  private String exception;

  @Column(name = "exception_messsage")
  private String exceptionMesssage;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public YesNoStatus getSentStatus() {
    return sentStatus;
  }

  public void setSentStatus(YesNoStatus sentStatus) {
    this.sentStatus = sentStatus;
  }

  public int getRetryCount() {
    return retryCount;
  }

  public void setRetryCount(int retryCount) {
    this.retryCount = retryCount;
  }

  public String getRecipientName() {
    return recipientName;
  }

  public void setRecipientName(String recipientName) {
    this.recipientName = recipientName;
  }

  public String getRecipientAddress() {
    return recipientAddress;
  }

  public void setRecipientAddress(String recipientAddress) {
    this.recipientAddress = recipientAddress;
  }

  public long getVersionId() {
    return versionId;
  }

  public void setVersionId(long versionId) {
    this.versionId = versionId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public SMSType getSmsType() {
    return smsType;
  }

  public void setSmsType(SMSType smsType) {
    this.smsType = smsType;
  }

  public String getException() {
    return exception;
  }

  public void setException(String exception) {
    this.exception = exception;
  }

  public String getExceptionMesssage() {
    return exceptionMesssage;
  }

  public void setExceptionMesssage(String exceptionMesssage) {
    this.exceptionMesssage = exceptionMesssage;
  }



}
