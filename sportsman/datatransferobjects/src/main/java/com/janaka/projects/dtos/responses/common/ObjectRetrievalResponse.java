package com.janaka.projects.dtos.responses.common;

public class ObjectRetrievalResponse<E> extends BaseResponse {

  private static final long serialVersionUID = 1L;

  private E dto = null;

  private long id = 0;

  public E getDto() {
    return dto;
  }

  public void setDto(E dto) {
    this.dto = dto;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }



}
