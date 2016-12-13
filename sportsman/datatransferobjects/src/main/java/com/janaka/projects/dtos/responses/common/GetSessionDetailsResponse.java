package com.janaka.projects.dtos.responses.common;

import java.io.Serializable;

import com.janaka.projects.common.security.Session;
import com.janaka.projects.common.security.User;

public class GetSessionDetailsResponse implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Session session;

  private User user;

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }



}
