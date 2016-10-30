package com.janaka.projects.dtos.requests.common;

import java.io.Serializable;

public class GetSessionDetailsRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }



}
