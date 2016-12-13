package com.janaka.projects.entitymanagement.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Nationality {

  SRILANKAN(1, "Sri Lankan"), NONSRILANKAN(2, "Non Sri Lankan");

  private int code;

  private String name;

  private Nationality(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  private static final Map<Integer, Nationality> LOOKUP = new HashMap<Integer, Nationality>();

  static {
    for (Nationality nationalityType : EnumSet.allOf(Nationality.class)) {
      LOOKUP.put(nationalityType.getCode(), nationalityType);
    }
  }

  public static Nationality fromCode(int code) {
    return LOOKUP.get(code);
  }


}
