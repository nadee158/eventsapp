package com.janaka.projects.dtos.requests.usermanagement;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class UserRoleUpdateRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  private String userRoleName = StringUtils.EMPTY;


  private boolean isDeleted;

  public String getUserRoleName() {
    return userRoleName;
  }

  public void setUserRoleName(String userRoleName) {
    this.userRoleName = userRoleName;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }



}
