package com.janaka.projects.services.reports.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.janaka.projects.common.constant.ApplicationConstants;

public class CreateReportRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<Object> list = null;

  private HashMap<String, Object> params = null;

  private String fileType = ApplicationConstants.FILE_TYPE_PDF;

  private long reportId;

  private String generatedReportFileName;

  private HashMap<Long, String> subreportList;

  public List<Object> getList() {
    return list;
  }

  public void setList(List<Object> list) {
    this.list = list;
  }

  public HashMap<String, Object> getParams() {
    return params;
  }

  public void setParams(HashMap<String, Object> params) {
    this.params = params;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public long getReportId() {
    return reportId;
  }

  public void setReportId(long reportId) {
    this.reportId = reportId;
  }

  public String getGeneratedReportFileName() {
    return generatedReportFileName;
  }

  public void setGeneratedReportFileName(String generatedReportFileName) {
    this.generatedReportFileName = generatedReportFileName;
  }

  public HashMap<Long, String> getSubreportList() {
    return subreportList;
  }

  public void setSubreportList(HashMap<Long, String> subreportList) {
    this.subreportList = subreportList;
  }

  @Override
  public String toString() {
    return "CreateReportRequest [list=" + list + ", params=" + params + ", fileType=" + fileType + ", reportId="
        + reportId + ", generatedReportFileName=" + generatedReportFileName + ", subreportList=" + subreportList + "]";
  }



}
