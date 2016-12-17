package com.janaka.projects.dtos.requests.core;

import java.io.Serializable;

public class AgeGroupCreationRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private int fromAge;

  private int toAge;

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

  @Override
  public String toString() {
    return "AgeGroupCreationRequest [fromAge=" + fromAge + ", toAge=" + toAge + "]";
  }

}
