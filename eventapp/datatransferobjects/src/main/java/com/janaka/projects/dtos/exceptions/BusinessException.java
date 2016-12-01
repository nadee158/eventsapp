package com.janaka.projects.dtos.exceptions;

public class BusinessException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String errorKey;

  public String getErrorKey() {
    return errorKey;
  }

  /**
   * @param errorKey
   * @param cause
   */
  public BusinessException(String errorKey, Throwable cause) {
    super(errorKey, cause);
    this.errorKey = errorKey;
  }

  /**
   * @param errorKey
   */
  public BusinessException(String errorKey) {
    super(errorKey);
    this.errorKey = errorKey;
  }

  /**
   * @param cause
   */
  public BusinessException(Throwable cause) {
    super(cause);
  }

  public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause);
  }

}
