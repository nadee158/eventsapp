package com.janaka.projects.entitymanagement.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum SMSType {

  NONE(0), RESET_PASSWORD(1), CHANGE_PASSWORD(2);

  private int code;

  private SMSType(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  private static final Map<Integer, SMSType> LOOKUP = new HashMap<Integer, SMSType>();

  static {
    for (SMSType emailType : EnumSet.allOf(SMSType.class)) {
      LOOKUP.put(emailType.getCode(), emailType);
    }
  }

  public static SMSType fromCode(int code) {
    return LOOKUP.get(code);
  }

}
