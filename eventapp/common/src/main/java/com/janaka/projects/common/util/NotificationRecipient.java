package com.janaka.projects.common.util;

public class NotificationRecipient {

  private String recipientName;

  private String recipientAddress;

  private int recipientType;



  public NotificationRecipient(String recipientName, String recipientAddress, int recipientType) {
    super();
    this.recipientName = recipientName;
    this.recipientAddress = recipientAddress;
    this.recipientType = recipientType;
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

  public int getRecipientType() {
    return recipientType;
  }

  public void setRecipientType(int recipientType) {
    this.recipientType = recipientType;
  }



}
