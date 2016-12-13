package com.janaka.projects.dtos.requests.usermanagement;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public class SecurityUserPermissionUpdateRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private long id;

  private List<Long> userRoles = null;

  private int versionNumber;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<Long> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<Long> userRoles) {
    this.userRoles = userRoles;
  }


  public int getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(int versionNumber) {
    this.versionNumber = versionNumber;
  }



}
