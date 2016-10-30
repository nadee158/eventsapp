package com.janaka.projects.dtos.responses.common;

import java.util.List;

public class ObjectListResponse<E> extends BaseResponse {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private List<E> dtoList = null;

  private int listSize = 0;


  public List<E> getDtoList() {
    return dtoList;
  }

  public void setDtoList(List<E> dtoList) {
    this.dtoList = dtoList;
  }

  public int getListSize() {
    return listSize;
  }

  public void setListSize(int listSize) {
    this.listSize = listSize;
  }



}
