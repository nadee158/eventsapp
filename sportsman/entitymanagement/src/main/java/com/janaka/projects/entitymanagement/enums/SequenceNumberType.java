package com.janaka.projects.entitymanagement.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum SequenceNumberType {

  NONE(0), PLAYER_NUMBER(1);

  private int code;

  private SequenceNumberType(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  private static final Map<Integer, SequenceNumberType> LOOKUP = new HashMap<Integer, SequenceNumberType>();

  static {
    for (SequenceNumberType gender : EnumSet.allOf(SequenceNumberType.class)) {
      LOOKUP.put(gender.getCode(), gender);
    }
  }

  public static SequenceNumberType fromCode(int code) {
    return LOOKUP.get(code);
  }

  public static Map<Integer, SequenceNumberType> getLookup() {
    return LOOKUP;
  }



}
