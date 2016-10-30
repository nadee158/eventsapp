package com.janaka.projects.entitymanagement.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum EmailType {

  NONE(0), RESET_PASSWORD(1), CHANGE_PASSWORD(2);

  private int code;

  private EmailType(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  private static final Map<Integer, EmailType> LOOKUP = new HashMap<Integer, EmailType>();

  static {
    for (EmailType emailType : EnumSet.allOf(EmailType.class)) {
      LOOKUP.put(emailType.getCode(), emailType);
    }
  }

  public static EmailType fromCode(int code) {
    return LOOKUP.get(code);
  }

}
