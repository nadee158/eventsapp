package com.janaka.projects.services.reports.util;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class GeneralEnumConstants {

  public enum ReportType {
    CUSTOM, OTHER;
  }

  public enum GenderStatus {
    MALE(0), FEMAIL(1), OTHER(2);

    private int code;

    private GenderStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    private static final Map<Integer, GenderStatus> LOOKUP = new HashMap<Integer, GenderStatus>();

    static {
      for (GenderStatus genderStatus : EnumSet.allOf(GenderStatus.class)) {
        LOOKUP.put(genderStatus.getCode(), genderStatus);
      }
    }

    public static GenderStatus fromCode(int code) {
      return LOOKUP.get(code);
    }
  }

  public enum MarrageStatus {
    NONE(0), SINGLE(1), MARRIED(2), DIVORCE(3), COMPLICATED(4);

    private int code;

    private MarrageStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    private static final Map<Integer, MarrageStatus> LOOKUP = new HashMap<Integer, MarrageStatus>();

    static {
      for (MarrageStatus marrageStatus : EnumSet.allOf(MarrageStatus.class)) {
        LOOKUP.put(marrageStatus.getCode(), marrageStatus);
      }
    }

    public static MarrageStatus fromCode(int code) {
      return LOOKUP.get(code);
    }
  }

  public enum YesNoStatus {
    YES(1), NO(0);

    private int code;

    private YesNoStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
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


  public enum AttachmentType {
    ACCESS_ROOT(1), OTHER_INFOR(2), MODIFICATION(3), OTHER(4);

    private int code;

    private AttachmentType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    private static final Map<Integer, AttachmentType> LOOKUP = new HashMap<Integer, AttachmentType>();

    static {
      for (AttachmentType attachmentType : EnumSet.allOf(AttachmentType.class)) {
        LOOKUP.put(attachmentType.getCode(), attachmentType);
      }
    }

    public static AttachmentType fromCode(int code) {
      return LOOKUP.get(code);
    }
  }


  public enum UserRoleType {
    ROLE_USER(1), ROLE_ADMIN(2), ROLE_GUEST(3);

    private int code;

    private UserRoleType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    private static final Map<Integer, UserRoleType> LOOKUP = new HashMap<Integer, UserRoleType>();

    static {
      for (UserRoleType roleType : EnumSet.allOf(UserRoleType.class)) {
        LOOKUP.put(roleType.getCode(), roleType);
      }
    }

    public static UserRoleType fromCode(int code) {
      return LOOKUP.get(code);
    }
  }

  public enum ActiveStatus {
    PENDING(0), ACTIVE(1), INACTIVE(2);

    private int code;

    private ActiveStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    private static final Map<Integer, ActiveStatus> LOOKUP = new HashMap<Integer, ActiveStatus>();

    static {
      for (ActiveStatus activeStatus : EnumSet.allOf(ActiveStatus.class)) {
        LOOKUP.put(activeStatus.getCode(), activeStatus);
      }
    }

    public static ActiveStatus fromCode(int code) {
      return LOOKUP.get(code);
    }
  }


}
