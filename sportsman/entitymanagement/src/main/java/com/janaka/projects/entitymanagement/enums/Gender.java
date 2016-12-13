package com.janaka.projects.entitymanagement.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Gender {

  NONE(0), MALE(1), FEMALE(2), UNKNOWN(3);

  private int code;

  private Gender(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  private static final Map<Integer, Gender> LOOKUP = new HashMap<Integer, Gender>();

  static {
    for (Gender gender : EnumSet.allOf(Gender.class)) {
      LOOKUP.put(gender.getCode(), gender);
    }
  }

  public static Gender fromCode(int code) {
    return LOOKUP.get(code);
  }

  public static Map<Integer, Gender> getLookup() {
    return LOOKUP;
  }



}
