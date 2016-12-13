package com.janaka.projects.dtos.domain.core;

import java.io.Serializable;

public class CategorySetupItemDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String itemName;

  private String text1;

  private String text2;

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public String getText1() {
    return text1;
  }

  public void setText1(String text1) {
    this.text1 = text1;
  }

  public String getText2() {
    return text2;
  }

  public void setText2(String text2) {
    this.text2 = text2;
  }

  @Override
  public String toString() {
    return "CategorySetupItemDTO [itemName=" + itemName + ", text1=" + text1 + ", text2=" + text2 + "]";
  }



}
