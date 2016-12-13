package com.janaka.projects.entitymanagement.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum MaritalStatus {

  NONE(0, "NONE"), SINGLE(1, "SINGLE"), MARRIED(2, "MARRIED"), DIVORCED(3, "DIVORCED"), COMPLICATED(4, "COMPLICATED");

  private int code;
  private String name;

  private MaritalStatus(int code, String name) {
    this.code = code;
    this.name = name;

  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  private static final Map<Integer, MaritalStatus> LOOKUP = new HashMap<Integer, MaritalStatus>();

  static {
    for (MaritalStatus maritalStatus : EnumSet.allOf(MaritalStatus.class)) {
      LOOKUP.put(maritalStatus.getCode(), maritalStatus);
    }
  }

  public static MaritalStatus fromCode(int code) {
    return LOOKUP.get(code);
  }

}
