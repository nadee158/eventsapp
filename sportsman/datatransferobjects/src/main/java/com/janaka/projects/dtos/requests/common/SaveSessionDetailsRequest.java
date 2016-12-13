package com.janaka.projects.dtos.requests.common;

import java.io.Serializable;

import com.janaka.projects.common.security.Session;

public class SaveSessionDetailsRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private Session session;

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }


}
