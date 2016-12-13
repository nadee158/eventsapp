package com.janaka.projects.dtos.requests.usermanagement;

import java.io.Serializable;

public class UserRoleListRetrievalRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  private long securityUserId;


  public long getSecurityUserId() {
    return securityUserId;
  }


  public void setSecurityUserId(long securityUserId) {
    this.securityUserId = securityUserId;
  }



}
