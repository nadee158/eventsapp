package com.janaka.projects.dtos.requests.core;

import java.io.Serializable;

public class AgeGroupUpdateRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  private int fromAge;

  private int toAge;

  private String recordStatus;

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

  @Override
  public String toString() {
    return "AgeGroupUpdateRequest [id=" + id + ", fromAge=" + fromAge + ", toAge=" + toAge + ", recordStatus="
        + recordStatus + "]";
  }



}
