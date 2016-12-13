/**
 * 
 */
package com.janaka.projects.entitymanagement.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dhiripitiy001
 *
 */
public enum Prefix {
  MR(1, "Mr."), MRS(2, "Mrs."), MISS(3, "Miss"), REV(4, "Rev."), OTHER(5, "Other");

  private int code;

  private String name;

  private Prefix(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  private static final Map<Integer, Prefix> LOOKUP = new HashMap<Integer, Prefix>();

  static {
    for (Prefix prefix : EnumSet.allOf(Prefix.class)) {
      LOOKUP.put(prefix.getCode(), prefix);
    }
  }

  public static Prefix fromCode(int code) {
    return LOOKUP.get(code);
  }
}
