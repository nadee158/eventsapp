package com.janaka.projects.common.captcha;

public class CaptchaValidationRequest {

  private CaptchaData captchaData;


  public CaptchaData getCaptchaData() {
    return captchaData;
  }

  public void setCaptchaData(CaptchaData captchaData) {
    this.captchaData = captchaData;
  }

  @Override
  public String toString() {
    return "CaptchaValidationRequest [captchaData=" + captchaData + "]";
  }



}
