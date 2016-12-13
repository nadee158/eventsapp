package com.janaka.projects.common.util;

public class NotificationSentStatus {

  private String status;

  private Exception exception;

  private String exceptionMesssage;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Exception getException() {
    return exception;
  }

  public void setException(Exception exception) {
    this.exception = exception;
  }

  public String getExceptionMesssage() {
    return exceptionMesssage;
  }

  public void setExceptionMesssage(String exceptionMesssage) {
    this.exceptionMesssage = exceptionMesssage;
  }


}
