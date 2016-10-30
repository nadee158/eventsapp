package com.janaka.projects.dtos.requests.common;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class SignUpRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String firstName = StringUtils.EMPTY;

  private String lastName = StringUtils.EMPTY;

  private String userName = StringUtils.EMPTY;

  private String email = StringUtils.EMPTY;

  private String secret = StringUtils.EMPTY;

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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }



}
