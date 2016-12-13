package com.janaka.projects.dtos.requests.common;

import java.io.Serializable;

public class ObjectDeletionRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id;

  private int versionNumber;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(int versionNumber) {
    this.versionNumber = versionNumber;
  }



}
