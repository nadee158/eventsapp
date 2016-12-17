package com.janaka.projects.dtos.domain.core;

import java.io.Serializable;

public class AgeGroupDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  private int fromAge;

  private int toAge;

  private String recordStatus;

  private long version;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getFromAge() {
    return fromAge;
  }

  public void setFromAge(int fromAge) {
    this.fromAge = fromAge;
  }

  public int getToAge() {
    return toAge;
  }

  public void setToAge(int toAge) {
    this.toAge = toAge;
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
    return "AgeGroupDTO [id=" + id + ", fromAge=" + fromAge + ", toAge=" + toAge + ", recordStatus=" + recordStatus
        + ", version=" + version + "]";
  }



}
