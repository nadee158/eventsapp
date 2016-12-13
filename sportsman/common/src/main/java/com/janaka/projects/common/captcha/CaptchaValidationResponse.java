package com.janaka.projects.common.captcha;

public class CaptchaValidationResponse {

  public static final int VALID_CAPTCHA = 1;
  public static final int INVALID_CAPTCHA = 2;

  // 1-valid | 2-invalid
  private int validStatus;

  public int getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(int validStatus) {
    this.validStatus = validStatus;
  }

  @Override
  public String toString() {
    return "CaptchaValidationResponse [validStatus=" + validStatus + "]";
  }

  public CaptchaValidationResponse(int validStatus) {
    super();
    this.validStatus = validStatus;
  }



}
