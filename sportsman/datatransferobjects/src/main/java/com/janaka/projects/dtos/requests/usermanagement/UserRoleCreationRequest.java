package com.janaka.projects.dtos.requests.usermanagement;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class UserRoleCreationRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String userRoleName = StringUtils.EMPTY;

  public String getUserRoleName() {
    return userRoleName;
  }

  public void setUserRoleName(String userRoleName) {
    this.userRoleName = userRoleName;
  }



}
