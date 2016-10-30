package com.janaka.projects.services.reports.dto;

import com.janaka.projects.services.reports.util.GeneralEnumConstants.ReportType;

public class ReportTemplateResponse {

  private long id = 0;

  private String reportName;

  private String uploadedFileName;

  private String originalFileName;

  private String reportDescription;

  private ReportType reportType;

  private int version;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getReportName() {
    return reportName;
  }

  public void setReportName(String reportName) {
    this.reportName = reportName;
  }

  public String getUploadedFileName() {
    return uploadedFileName;
  }

  public void setUploadedFileName(String uploadedFileName) {
    this.uploadedFileName = uploadedFileName;
  }

  public String getOriginalFileName() {
    return originalFileName;
  }

  public void setOriginalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
  }

  public String getReportDescription() {
    return reportDescription;
  }

  public void setReportDescription(String reportDescription) {
    this.reportDescription = reportDescription;
  }

  public ReportType getReportType() {
    return reportType;
  }

  public void setReportType(ReportType reportType) {
    this.reportType = reportType;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return "ReportTemplateResponse [id=" + id + ", reportName=" + reportName + ", uploadedFileName=" + uploadedFileName
        + ", originalFileName=" + originalFileName + ", reportDescription=" + reportDescription + ", reportType="
        + reportType + ", version=" + version + "]";
  }

}
