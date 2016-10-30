package com.janaka.projects.dtos.responses.common;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class SubmitFeedbackResponse implements Serializable {


  private static final long serialVersionUID = 1L;

  private String status = StringUtils.EMPTY;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }



}
