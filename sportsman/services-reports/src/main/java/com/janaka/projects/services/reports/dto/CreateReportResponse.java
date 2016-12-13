package com.janaka.projects.services.reports.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class CreateReportResponse implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String fileName = StringUtils.EMPTY;
  private Date reportDate = null;
  private int statusCode = 0;

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Date getReportDate() {
    return reportDate;
  }

  public void setReportDate(Date reportDate) {
    this.reportDate = reportDate;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  @Override
  public String toString() {
    return "CreateReportResponse [fileName=" + fileName + ", reportDate=" + reportDate + ", statusCode=" + statusCode
        + "]";
  }



}
