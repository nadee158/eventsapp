package com.janaka.projects.dtos.domain.core;

import java.io.Serializable;

public class GradeBeltDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  private String gradeBeltName;

  private String recordStatus;

  private long version;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }



  public String getGradeBeltName() {
    return gradeBeltName;
  }

  public void setGradeBeltName(String gradeBeltName) {
    this.gradeBeltName = gradeBeltName;
  }

  public String getRecordStatus() {
    return recordStatus;
  }

  public void setRecordStatus(String recordStatus) {
    this.recordStatus = recordStatus;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "GradeBeltDTO [id=" + id + ", gradeBeltName=" + gradeBeltName + ", recordStatus=" + recordStatus
        + ", version=" + version + "]";
  }



}
