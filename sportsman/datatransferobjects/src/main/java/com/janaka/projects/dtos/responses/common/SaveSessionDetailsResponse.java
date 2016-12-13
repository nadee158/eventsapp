package com.janaka.projects.dtos.responses.common;

import java.io.Serializable;

import com.janaka.projects.common.security.User;



public class SaveSessionDetailsResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }



}
