package com.janaka.projects.entitymanagement.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum YesNoStatus {

  NO(0, "No"), YES(1, "Yes");

  private int code;

  private String name;

  private YesNoStatus(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }


  private static final Map<Integer, YesNoStatus> LOOKUP = new HashMap<Integer, YesNoStatus>();

  static {
    for (YesNoStatus yesNoStatus : EnumSet.allOf(YesNoStatus.class)) {
      LOOKUP.put(yesNoStatus.getCode(), yesNoStatus);
    }
  }

  public static YesNoStatus fromCode(int code) {
    return LOOKUP.get(code);
  }

}
