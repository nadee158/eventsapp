package com.janaka.projects.dtos.domain.common;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String uuId = null;

  private LocalDateTime creationTime;

  private LocalDateTime modificationTime;

  private String createdByUser;

  private String modifiedByUser;

  private String recordStatus;


  public String getUuId() {
    return uuId;
  }

  public void setUuId(String uuId) {
    this.uuId = uuId;
  }


  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(LocalDateTime creationTime) {
    this.creationTime = creationTime;
  }

  public LocalDateTime getModificationTime() {
    return modificationTime;
  }

  public void setModificationTime(LocalDateTime modificationTime) {
    this.modificationTime = modificationTime;
  }

  public String getCreatedByUser() {
    return createdByUser;
  }

  public void setCreatedByUser(String createdByUser) {
    this.createdByUser = createdByUser;
  }

  public String getModifiedByUser() {
    return modifiedByUser;
  }

  public void setModifiedByUser(String modifiedByUser) {
    this.modifiedByUser = modifiedByUser;
  }

  public String getRecordStatus() {
    return recordStatus;
  }

  public void setRecordStatus(String recordStatus) {
    this.recordStatus = recordStatus;
  }



}
