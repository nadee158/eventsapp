package com.janaka.projects.dtos.domain.usermanagement;

import java.io.Serializable;

public class PrefixDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private int code = 0;

  private String name = null;


  public PrefixDTO(int code, String name) {
    super();
    this.code = code;
    this.name = name;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }



}
