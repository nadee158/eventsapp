package com.janaka.projects.entitymanagement.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Language {

  NONE(0, "no"), ENGLISH(1, "en"), SINHALA(2, "si"), TAMIL(3, "ta");

  private int code;
  private String langCode;

  private Language(int code, String langCode) {
    this.code = code;
    this.langCode = langCode;
  }

  public int getCode() {
    return code;
  }

  private static final Map<Integer, Language> LOOKUP = new HashMap<Integer, Language>();

  private static final Map<String, Language> STRING_LOOKUP = new HashMap<String, Language>();

  static {
    for (Language gender : EnumSet.allOf(Language.class)) {
      LOOKUP.put(gender.getCode(), gender);
      STRING_LOOKUP.put(gender.getLangCode(), gender);
    }
  }

  public static Language fromLangCode(String code) {
    return STRING_LOOKUP.get(code);
  }

  public static Language fromCode(int code) {
    return LOOKUP.get(code);
  }

  public String getLangCode() {
    return langCode;
  }

}
