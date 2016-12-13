package com.janaka.projects.dtos.responses.common;

import java.io.Serializable;

public class ErrorDetailReponse implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int status;
  private String errorCode;
  private String message;
  private String developerMessage;
  private String moreInfo;


  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDeveloperMessage() {
    return developerMessage;
  }

  public void setDeveloperMessage(String developerMessage) {
    this.developerMessage = developerMessage;
  }

  public String getMoreInfo() {
    return moreInfo;
  }

  public void setMoreInfo(String moreInfo) {
    this.moreInfo = moreInfo;
  }


}
