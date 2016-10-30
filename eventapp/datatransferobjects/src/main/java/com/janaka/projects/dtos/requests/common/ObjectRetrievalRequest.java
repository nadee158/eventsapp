package com.janaka.projects.dtos.requests.common;

import java.io.Serializable;

public class ObjectRetrievalRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }



}
