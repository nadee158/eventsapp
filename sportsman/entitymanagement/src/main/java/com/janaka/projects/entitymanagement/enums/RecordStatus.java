package com.janaka.projects.entitymanagement.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum RecordStatus {

  NONE(0, ""), ACTIVE(1, "A"), INACTIVE(2, "I");

  private int code;
  private String recordStatusCode;

  private RecordStatus(int code, String recordStatusCode) {
    this.code = code;
    this.recordStatusCode = recordStatusCode;
  }

  public int getCode() {
    return code;
  }

  private static final Map<Integer, RecordStatus> LOOKUP = new HashMap<Integer, RecordStatus>();

  private static final Map<String, RecordStatus> STRING_LOOKUP = new HashMap<String, RecordStatus>();

  static {
    for (RecordStatus gender : EnumSet.allOf(RecordStatus.class)) {
      LOOKUP.put(gender.getCode(), gender);
      STRING_LOOKUP.put(gender.getRecordStatusCode(), gender);
    }
  }

  public static RecordStatus fromRecordStatusCode(String code) {
    return STRING_LOOKUP.get(code);
  }

  public static RecordStatus fromCode(int code) {
    return LOOKUP.get(code);
  }

  public String getRecordStatusCode() {
    return recordStatusCode;
  }

}
