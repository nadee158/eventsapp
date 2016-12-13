package com.janaka.projects.dtos.domain.usermanagement;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.dtos.domain.common.BaseDTO;

public class UserRoleDTO extends BaseDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id = 0;

  private String userRoleName = StringUtils.EMPTY;

  private long versionNumber = 0;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserRoleName() {
    return userRoleName;
  }

  public void setUserRoleName(String userRoleName) {
    this.userRoleName = userRoleName;
  }

  public long getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(long versionNumber) {
    this.versionNumber = versionNumber;
  }



}
