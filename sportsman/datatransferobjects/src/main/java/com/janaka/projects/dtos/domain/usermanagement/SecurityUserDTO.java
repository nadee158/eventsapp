package com.janaka.projects.dtos.domain.usermanagement;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.domain.common.BaseDTO;


public class SecurityUserDTO extends BaseDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id = 0;

  private String userName = StringUtils.EMPTY;

  private String secret = StringUtils.EMPTY;

  private PersonDTO person;

  private Set<UserRoleDTO> userRoles = null;


  @JsonIgnore
  private boolean accountExpired = false;

  @JsonIgnore
  private boolean accountLocked = false;

  @JsonIgnore
  private boolean credentialsExpired = false;

  @JsonIgnore
  private boolean accountEnabled = true;

  private long versionNumber = 0;


  public SecurityUserDTO() {
    super();
  }

  public SecurityUserDTO(User user) {
    if (!(user.getSecurityUserId() == null)) {
      this.setUuId(user.getSecurityUserId().toString());
    }
    this.userName = user.getUsername();
    if (this.getPerson() == null) {
      this.person = new PersonDTO();
    }
    this.getPerson().setFullName(user.getUserFullName());
    this.getPerson().setFullName(user.getUserFullName());
    this.getPerson().setDateOfBirth(user.getDateOfBirth());
    this.getPerson().setMobileNumber(user.getCotactNumber());
    this.getPerson().setEmail(user.getEmail());
    this.getPerson().setProfileImagePath(user.getProfileImagePath());
  }



  public Set<UserRoleDTO> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Set<UserRoleDTO> userRoles) {
    this.userRoles = userRoles;
  }



  @JsonIgnore
  public boolean isAccountExpired() {
    return accountExpired;
  }

  public void setAccountExpired(boolean accountExpired) {
    this.accountExpired = accountExpired;
  }

  @JsonIgnore
  public boolean isAccountLocked() {
    return accountLocked;
  }

  public void setAccountLocked(boolean accountLocked) {
    this.accountLocked = accountLocked;
  }

  @JsonIgnore
  public boolean isCredentialsExpired() {
    return credentialsExpired;
  }

  public void setCredentialsExpired(boolean credentialsExpired) {
    this.credentialsExpired = credentialsExpired;
  }

  @JsonIgnore
  public boolean isAccountEnabled() {
    return accountEnabled;
  }

  public void setAccountEnabled(boolean accountEnabled) {
    this.accountEnabled = accountEnabled;
  }

  public long getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(long versionNumber) {
    this.versionNumber = versionNumber;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public PersonDTO getPerson() {
    return person;
  }

  public void setPerson(PersonDTO person) {
    this.person = person;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }



}
